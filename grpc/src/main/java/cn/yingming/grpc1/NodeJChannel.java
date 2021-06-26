package cn.yingming.grpc1;

import com.google.protobuf.ByteString;
import io.grpc.jchannelRpc.*;
import org.apache.commons.collections.ListUtils;
import org.jgroups.*;
import org.jgroups.util.ByteArrayDataInputStream;
import org.jgroups.util.ByteArrayDataOutputStream;
import org.jgroups.util.NameCache;
import org.jgroups.util.UUID;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class NodeJChannel implements Receiver{
    JChannel channel;
    String user_name;
    String node_name;
    String cluster_name;
    ReentrantLock lock;
    NodeServer.JChannelsServiceImpl service;
    String grpcAddress;
    ConcurrentHashMap nodesMap;
    ConcurrentHashMap serviceMap;

    NodeJChannel(String node_name, String cluster_name, String grpcAddress) throws Exception {
        this.channel = new JChannel("grpc/protocols/udp.xml");
        this.user_name = System.getProperty("user.name", "n/a");
        this.node_name = node_name;
        this.cluster_name = cluster_name;
        this.grpcAddress = grpcAddress;
        this.nodesMap = new ConcurrentHashMap<>();
        this.channel.setReceiver(this).connect(cluster_name);
        this.lock = new ReentrantLock();
        this.service = null;
        this.serviceMap = new ConcurrentHashMap<String, ClusterMap>();
        // put itself into available nodes list
        this.nodesMap.put(this.channel.getAddress(), this.grpcAddress);

        System.out.println("[JChannel] The current nodes in node cluster: " + this.nodesMap);
    }


    @Override
    public void receive(Message msg) {
        /** two conditions for receiving Message,
         * 1.receive the Message from other real JChannel. They do not send any Protobuf message type.
         * 2.receive the Message from other JChannel-server. They can send message, which contains Protobuf message object.
         *
         */
        if (msg.getObject() instanceof UpdateReqBetweenNodes){
            System.out.println("Receive the request from other JChannel-server for updating the previous information of clients.");
            receiveProtobufMsg(msg);
        } else if (msg.getObject() instanceof UpdateRepBetweenNodes) {
            System.out.println("Receive the response from the first JChannel-server for updating the previous information of clients.");
            receiveProtobufMsg(msg);
        } if (msg.getObject() instanceof String){
            // System.out.println("call receiveString");
            receiveString(msg);
        } else {
            // System.out.println("call receiveByte");
            receiveByte(msg);
        }
    }

    private void receiveProtobufMsg(Message msg){
        // update client information request
        if (msg.getObject() instanceof UpdateReqBetweenNodes){
            // check the Address of requester
            UpdateReqBetweenNodes req = msg.getObject();
            ByteArrayDataInputStream in = new ByteArrayDataInputStream(req.getAddress().toByteArray());
            UUID u = new UUID();
            Address address = (Address) u;
            lock.lock();
            try{
                u.readFrom(in);
                if (NameCache.getContents().containsKey(address)){
                    System.out.println("Confirm that the JChannel' Address in the existing NameCache.");
                    // generate message for the requester, NameCacheRep, ViewRep of clients,
                    UpdateNameCacheRep nameCacheRep = this.service.generateNameCacheMsg();
                    ClusterMap clusterInf = (ClusterMap) this.serviceMap.get("ClientCluster");
                    ViewRep viewRep = clusterInf.generateView();
                    // StateRep stateRep = clusterInf.generateState(); change
                    UpdateRepBetweenNodes rep = UpdateRepBetweenNodes.newBuilder().setClientView(viewRep)
                            .setNameCache(nameCacheRep).build();
                    Message msgRep = new ObjectMessage(msg.getSrc(), rep);
                    this.channel.send(msgRep);
                    System.out.println("UpdateRepBetweenNodes: " + rep);
                    System.out.println("The JChannel-server provides updating information for other new JChannel-server.");
                } else{
                    throw new IllegalArgumentException("The JChannel does not exist in the NameCache.");
                }
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        } else if (msg.getObject() instanceof UpdateRepBetweenNodes) {
            System.out.println("??????????????");
            UpdateRepBetweenNodes rep = msg.getObject();
            ViewRep view_rep = rep.getClientView();
            UpdateNameCacheRep nameCacheRep = rep.getNameCache();
            StateRep stateRep = rep.getClientState();
            // 1. update NameCache
            List<ByteString> addressList = nameCacheRep.getAddressList();
            List<String> nameList = nameCacheRep.getLogicalNameList();
            for (int i = 0; i < addressList.size(); i++) {
                ByteString bs = addressList.get(i);
                byte[] byte_address = bs.toByteArray();
                UUID u = new UUID();
                ByteArrayDataInputStream in = new ByteArrayDataInputStream(byte_address);
                try {
                    u.readFrom(in);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("A pair of Address and logical name: " + u + ", " + nameList.get(i));
                lock.lock();
                try {
                    NameCache.add(u, nameList.get(i));
                } finally {
                    lock.unlock();
                }
                System.out.println("Test: " + NameCache.getContents());
                System.out.println("Test: " + rep);
            }
            // 2.update client view
            ClusterMap clusterInf = null;
            if (this.serviceMap.size() == 0){
                try{
                    // the same client cluster
                    clusterInf = new ClusterMap();
                    this.serviceMap.put("ClientCluster", clusterInf);
                } catch (Exception e){
                    e.printStackTrace();
                }
            } else{
                clusterInf = (ClusterMap) this.serviceMap.get("ClientCluster");
            }
            View v = new View();
            ByteArrayDataInputStream in = new ByteArrayDataInputStream(view_rep.getView().toByteArray());
            try {
                v.readFrom(in);
            } catch (Exception e){
                e.printStackTrace();
            }
            clusterInf.setFromView(v);
            // 3. update state of client
            // clusterInf.history = stateRep; change

        }
    }

    public void receiveByte(Message msg){
        Object obj =  UtilsRJ.unserializeObj(msg.getPayload());

        if (obj instanceof Map){
            System.out.println("Receive the cluster information from node(coordinator) " + msg.getSrc());
            ConcurrentHashMap m = (ConcurrentHashMap) obj;
            lock.lock();
            try{
                this.serviceMap = m;
            } finally {
                lock.unlock();
            }
        } else if (obj == null){
            Request req = null;
            try{
                req = Request.parseFrom((byte[]) msg.getPayload());
            } catch (Exception e){
                e.printStackTrace();
            }
            if (req.hasConnectRequest()){
                ConnectReq conReq = req.getConnectRequest();
                System.out.println("[JChannel] Receive a shared connect() request for updating th cluster information.");
                System.out.println("here:" + conReq);
                // change: After the server receive the connect() result, it generate a
                connectCluster(conReq.getCluster(), UUID.fromString(conReq.getJchannelAddress()), conReq.getLogicalName());
            } else if (req.hasDisconnectRequest()){
                DisconnectReq disReq = req.getDisconnectRequest();
                System.out.println("[JChannel] Receive a shared disconnect() request for updating th cluster information.");
                disconnectCluster(disReq.getCluster(), UUID.fromString(disReq.getJchannelAddress()));
            } else if (req.hasMessageRequest()){
                MessageReq msgReq = req.getMessageRequest();
                if (msgReq.getDestination().equals("")){
                    System.out.println("[JChannel] Receive a shared send() request for broadcast to JChannl-Clients.");
                    lock.lock();
                    try{
                        ClusterMap cm = (ClusterMap) serviceMap.get(msgReq.getCluster());
                        cm.addHistory(msgReq);
                        this.service.broadcast(msgReq);
                    } finally {
                        lock.unlock();
                    }
                } else {
                    System.out.println("[JChannel] Receive a shared send() request for unicast to a JChannl-Client.");
                    this.service.unicast(msgReq);
                }
            } else if (req.hasStateMsg1()){
                System.out.println("[JChannel] Receive a shared getState(Target) request for unicast to a JChannl-Client.");
                this.service.unicast_stateMsg1(req.getStateMsg1());
            } else if (req.hasStateMsg2()) {
                System.out.println("[JChannel] Receive a shared getState(Target) result for unicast to a JChannl-Client.");
                this.service.unicast_stateMsg2(req.getStateMsg2());
            }
        }
    }

    public void receiveString(Message msg){
        String msgStr = msg.getObject();
        String newMsg = null;
        // some types of broadcast messages, update address or broadcast the common message
        synchronized (this.nodesMap){
            // condition 1, update server messgage
            if (msgStr.startsWith("grpcAddress:")){
                String[] strs = msgStr.split(":", 2);
                boolean same = false;
                for (Object add : this.nodesMap.keySet()) {
                    if (add.toString().equals(msg.getSrc().toString())&&this.nodesMap.get(add).equals(strs[1])){
                        same = true;
                    }
                }
                // condition 1.1, no change
                if (same){
                    System.out.println("[JChannel] Receive a confirmation from a node, but no change.");
                } else{
                    // condition 1.2 changed server list, update list and broadcast update servers
                    this.nodesMap.put(msg.getSrc(), strs[1]);
                    System.out.println("[JChannel] Receive a confirmation from a node, update server map.");
                    System.out.println("[JChannel] After updating: " + this.nodesMap);
                    UpdateRep updateMsg = UpdateRep.newBuilder()
                            .setAddresses(generateAddMsg())
                            .build();
                    Response broMsg = Response.newBuilder()
                            .setUpdateResponse(updateMsg)
                            .build();
                    this.service.broadcastResponse(broMsg);
                }
                // condition 2, connect() request
            } else if (msgStr.equals("ClusterInformation")){
                // send the current
                System.out.println("Receive a request for the current " +
                        "JChannel-client cluster information from a new node member: " + msg.getSrc());
                lock.lock();
                try{
                    byte[] b = UtilsRJ.serializeObj(this.serviceMap);
                    System.out.println(b);
                    Message msg2 = new ObjectMessage(msg.getSrc(), b);
                    this.channel.send(msg2);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    this.lock.unlock();
                }
            } else if (msgStr.startsWith("[DisconnectNotGrace]")){
                String[] strs = msgStr.split(" ");
                System.out.println("[JChannel] Receive a shared not graceful disconnect() request for updating th cluster information.");
                disconnectClusterNoGraceful(UUID.fromString(strs[1]));
            }
        }
    }

    public String generateAddMsg(){
        StringBuilder sb = new StringBuilder();
        synchronized (this.nodesMap){
            for (Object s:this.nodesMap.keySet()) {
                sb.append(this.nodesMap.get(s)).append(" ");
            }
            String str = sb.toString().trim();
            return str;
        }
    }

    public void setService(NodeServer.JChannelsServiceImpl gRPCservice){
        this.service = gRPCservice;
    }


    // update the view of nodes
    @Override
    public void viewAccepted(View new_view) {
        System.out.println("** JChannel Node view: " + new_view);
        /* When the view is changed by any action, it will send its address to other jchannels
        and update its nodesList.
         */
        // compare keySet of nodesList with view list.
        List currentView = new_view.getMembers();
        List currentNodesList = new ArrayList<>(this.nodesMap.keySet());
        compareNodes(currentView, currentNodesList);
        checkClusterMap(new_view);

        if (this.service != null){
            UpdateNameCacheRep nameCacheRep = this.service.generateNameCacheMsg();
            Response rep2 = Response.newBuilder().setUpdateNameCache(nameCacheRep).build();
            service.broadcastResponse(rep2);

            // ViewResponse_servers
            View view = channel.getView();
            ByteArrayDataOutputStream vOutStream = new ByteArrayDataOutputStream();
            try {
                view.writeTo(vOutStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[] v_byte = vOutStream.buffer();

            ViewRep_server view_rep = ViewRep_server.newBuilder().setSender(this.channel.address().toString()).setViewByte(ByteString.copyFrom(v_byte)).build();
            Response rep = Response.newBuilder().setViewRepServer(view_rep).build();
            service.broadcastResponse(rep);
            System.out.println(rep);

        }
    }

    public void checkClusterMap(View view){
        // this first startup
        // whether is the coordinator
        if (this.serviceMap == null){
            if (view.getMembers().get(0).toString().equals(this.channel.getAddress().toString())){
                System.out.println("[JChannel] This is the coordinator of the node cluster.");
            } else {
                /*
                String msg = "ClusterInformation";
                try{
                    // send the request to get the current inf of client-jchannel cluster
                    this.channel.send(view.getMembers().get(0), msg);
                } catch (Exception e){
                    e.printStackTrace();
                }

                 */
                try {
                    UUID u = (UUID) this.channel.getAddress();
                    ByteArrayDataOutputStream out = new ByteArrayDataOutputStream();
                    u.writeTo(out);
                    byte[] b = out.buffer();
                    UpdateReqBetweenNodes req = UpdateReqBetweenNodes.newBuilder().setAddress(ByteString.copyFrom(b)).build();
                    this.channel.send(view.getCoord(), req);
                } catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
    }

    public void compareNodes(List currentView, List currentNodesList){
        // add node
        synchronized (this.nodesMap) {
            if (currentView.size() > currentNodesList.size()) {
                System.out.println("[JChannel] Store new node inf.");
                List compare = ListUtils.subtract(currentView, currentNodesList);
                for (int i = 0; i < compare.size(); i++) {
                    this.nodesMap.put(compare.get(i), "unknown");
                }
                System.out.println("[JChannel] The current nodes in node cluster: " + this.nodesMap);
                sendMyself();
            } else if (currentView.size() < currentNodesList.size()) {
                System.out.println("[JChannel] Remove cancelled node inf.");
                List compare = ListUtils.subtract(currentNodesList, currentView);
                for (int i = 0; i < compare.size(); i++) {
                    this.nodesMap.remove(compare.get(i));
                }
                System.out.println("[JChannel] The current nodes in node cluster: " + this.nodesMap);
                UpdateRep updateMsg = UpdateRep.newBuilder()
                        .setAddresses(generateAddMsg())
                        .build();
                Response broMsg = Response.newBuilder()
                        .setUpdateResponse(updateMsg)
                        .build();
                this.service.broadcastResponse(broMsg);
            } else {
                System.out.println("[JChannel] The current nodes does not change.");
            }
        }
    }

    public void sendMyself(){
        // send the address of its gRPC server address/port
        Message msg = new ObjectMessage(null, "grpcAddress:" + this.grpcAddress);
        // send messages exclude itself.
        msg.setFlagIfAbsent(Message.TransientFlag.DONT_LOOPBACK);
        try{
            System.out.println("[JChannel] Send the grpc address of my self.");
            this.channel.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // add view action and forward
    public void connectCluster(String cluster, Address address, String name){
        lock.lock();
        try{
            // create the cluster or connect an existing cluster.
            if (serviceMap.containsKey(cluster)){
                System.out.println(address + "(" +  name + ") connects to the existing cluster: " + cluster);
                ClusterMap clusterObj = (ClusterMap) serviceMap.get(cluster);
                clusterObj.getMap().put(address, name);
                clusterObj.addMember(address);
            } else{
                System.out.println(address + "(" +  name + ") connects to a new cluster: " + cluster);
                // create new cluster object and set it as the creator
                ClusterMap clusterObj = new ClusterMap(address);
                clusterObj.getMap().put(address, name);
                clusterObj.addMember(address);
                // put into serviceMap
                serviceMap.put(cluster, clusterObj);
            }
            ClusterMap clusterObj = (ClusterMap) serviceMap.get(cluster);
            ViewRep viewRep= clusterObj.generateView();
            this.service.broadcastView(viewRep, cluster);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    // add view action and forward
    public void disconnectCluster(String cluster, Address address){
        lock.lock();
        try{
            ClusterMap m = (ClusterMap) serviceMap.get(cluster);
            if (m.getMap().size() == 1 && m.getMap().get(address) != null){
                serviceMap.remove(cluster);
                System.out.println("The last JChannel-Client quits and deletes the cluster, " + cluster);
            } else if (m.getMap().size() > 1){
                m.removeClient(address);
                // change?
                m.getMap().remove(address);
                System.out.println(address + " quits " + cluster);
                ClusterMap clusterObj = (ClusterMap) serviceMap.get(cluster);
                ViewRep viewRep= clusterObj.generateView();
                this.service.broadcastView(viewRep, cluster);
            }

        } finally {
            lock.unlock();
        }
    }

    public void disconnectClusterNoGraceful(Address uuid){
        this.lock.lock();
        try{
            for (Object cluster: serviceMap.keySet()) {
                String clusterName = cluster.toString();
                ClusterMap clusterMap = (ClusterMap) serviceMap.get(clusterName);
                for (Object eachUuid:clusterMap.getMap().keySet()) {
                    if (uuid.equals(eachUuid.toString())){
                        System.out.println("No grace, Remove the JChannel-client from its cluster.");
                        clusterMap.removeClient(uuid);
                        clusterMap.getMap().remove(uuid);

                        ClusterMap clusterObj = (ClusterMap) serviceMap.get(clusterName);
                        if (clusterObj.getMap().size() == 0){
                            serviceMap.remove(cluster);
                            System.out.println("The last JChannel-Client quits and deletes the cluster, " + cluster);
                        } else{
                            ViewRep viewRep= clusterObj.generateView();
                            this.service.broadcastView(viewRep, clusterName);
                        }
                    }

                }
            }
        } finally {
            this.lock.unlock();
        }

    }
}
