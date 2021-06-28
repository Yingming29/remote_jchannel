package cn.yingming.grpc1;

import com.google.protobuf.ByteString;
import com.sun.jdi.event.ExceptionEvent;
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
        this.serviceMap.put("ClientCluster", new ClusterMap());
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
        } else if (msg.getObject() instanceof ChannelMsg){
            receiveChannelMsg(msg);
        } else if (msg.getObject() instanceof Request){
            receiveProtobufMsg(msg);
        }


        // define receive string and byte for other
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
                    // changed: not null , here
                    ClusterMap clusterInf = (ClusterMap) this.serviceMap.get("ClientCluster");
                    ViewRep viewRep = null;
                    UpdateRepBetweenNodes rep;
                    if (clusterInf.getCreator() != null){
                        viewRep = clusterInf.generateView();
                    }
                    // StateRep stateRep = clusterInf.generateState(); change
                    if (clusterInf.getCreator() != null) {
                        rep = UpdateRepBetweenNodes.newBuilder().setClientView(viewRep)
                                .setNameCache(nameCacheRep).build();
                        System.out.println("!=null");
                    } else{
                        System.out.println("= null");
                        rep = UpdateRepBetweenNodes.newBuilder().setNameCache(nameCacheRep).build();
                    }
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
            }
            // 2.update client view
            ClusterMap clusterInf = null;
            if (this.serviceMap.size() == 0){
                clusterInf = new ClusterMap();
                this.serviceMap.put("ClientCluster", clusterInf);
                System.out.println(1);
            } else{
                clusterInf = (ClusterMap) this.serviceMap.get("ClientCluster");
                System.out.println(2);
            }
            View v = new View();
            ByteArrayDataInputStream in = new ByteArrayDataInputStream(view_rep.getView().toByteArray());
            try {
                v.readFrom(in);
                clusterInf.setFromView(v);
            } catch (Exception e){
                e.printStackTrace();
            }
            System.out.println(v);
            // 3. update state of client
            // clusterInf.history = stateRep; change

        } else if (msg.getObject() instanceof Request && ((Request) msg.getObject()).hasConnectRequest()){
            ConnectReq conReq = ((Request) msg.getObject()).getConnectRequest();
            System.out.println("[JChannel] Receive a shared connect() request for updating th cluster information.");
            System.out.println("here:" + conReq);
            // change: After the server receive the connect() result, it generate a
            lock.lock();
            try{
                UUID u = new UUID();
                ByteArrayDataInputStream in = new ByteArrayDataInputStream(conReq.getJchannAddressByte().toByteArray());
                u.readFrom(in);
                NameCache.add(u, conReq.getLogicalName());
                connectCluster(conReq.getCluster(), u, conReq.getLogicalName());
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        } else if (msg.getObject() instanceof Request && ((Request) msg.getObject()).hasDisconnectRequest()){
            DisconnectReq disReq = ((Request) msg.getObject()).getDisconnectRequest();
            System.out.println("[JChannel] Receive a shared disconnect() request for updating th cluster information.");
            disconnectCluster(disReq.getCluster(), UUID.fromString(disReq.getJchannelAddress()));
        } else if (msg.getObject() instanceof Request && ((Request) msg.getObject()).hasMessageReqRep()){
            MessageReqRep msgReq = ((Request) msg.getObject()).getMessageReqRep();
            Message msgObj = UtilsRJ.convertMessage(msgReq);
            if (msgObj.getDest() == null){
                System.out.println("[JChannel] Receive a shared send() request for broadcast to JChannl-Clients.");
                lock.lock();
                try{
                    ClusterMap cm = (ClusterMap) serviceMap.get("ClientCluster");
                    cm.addHistory(msgReq);
                    this.service.broadcast(msgReq);
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("[JChannel] Receive a shared send() request for unicast to a JChannl-Client.");
                this.service.unicast(msgReq);
            }
        } else if (msg.getObject() instanceof Request && ((Request) msg.getObject()).hasStateMsg1()){
            System.out.println("[JChannel] Receive a shared getState(Target) request for unicast to a JChannl-Client.");
            this.service.unicast_stateMsg1(((Request) msg.getObject()).getStateMsg1());
        } else if (msg.getObject() instanceof Request && ((Request) msg.getObject()).hasStateMsg2()) {
            System.out.println("[JChannel] Receive a shared getState(Target) result for unicast to a JChannl-Client.");
            this.service.unicast_stateMsg2(((Request) msg.getObject()).getStateMsg2());
        }
    }

    public void receiveChannelMsg(Message msg){
        ChannelMsg cmsg = msg.getObject();
        if (cmsg.getType().equals("[DisconnectNotGrace]")){
            UUID u = new UUID();
            ByteArrayDataInputStream in = new ByteArrayDataInputStream(cmsg.getContentByt().toByteArray());
            ReentrantLock sublock = new ReentrantLock();
            sublock.lock();
            try {
                u.readFrom(in);
                disconnectClusterNoGraceful(u);
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                sublock.unlock();
            }
            System.out.println("[JChannel] Receive a shared not graceful disconnect() request for updating th cluster information.");
        } if (cmsg.getType().equals("grpcAddress")){
            synchronized (this.nodesMap){
                // condition 1, update server messgage

                String new_add = cmsg.getContentStr();
                boolean same = false;
                for (Object add : this.nodesMap.keySet()) {
                    if (add.toString().equals(msg.getSrc().toString())&&this.nodesMap.get(add).equals(new_add)){
                        same = true;
                    }
                }
                // condition 1.1, no change
                if (same){
                    System.out.println("[JChannel] Receive a confirmation from a node, but no change.");
                } else{
                    // condition 1.2 changed server list, update list and broadcast update servers
                    this.nodesMap.put(msg.getSrc(), new_add);
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
                ReentrantLock lock = new ReentrantLock();
                lock.lock();
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
                finally {
                    lock.unlock();
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
        ChannelMsg cmsg = ChannelMsg.newBuilder().setType("grpcAddress").setContentStr(this.grpcAddress).build();
        Message msg = new ObjectMessage(null, cmsg);
        // send messages exclude itself.
        // maybe change because the stats and discardOwnMessage
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
            System.out.println(address + "(" +  name + ") connects to client cluster: " + cluster);
            // create new cluster object and set it as the creator
            ClusterMap clusterObj = (ClusterMap) this.serviceMap.get("ClientCluster");
            clusterObj.getMap().put(address, name);
            clusterObj.addMember(address);
            // change:   update namecache to its clients
            NameCache.add(address, name);
            UpdateNameCacheRep updateName = this.service.generateNameCacheMsg();
            Response rep = Response.newBuilder().setUpdateNameCache(updateName).build();
            // here, the two broadcast()'s target is different, broadcastView() has a specific client-client.
            this.service.broadcastResponse(rep);
            ViewRep viewRep= clusterObj.generateView();
            System.out.println("connectCluster corrdinator" + clusterObj.getCreator());
            // changed : generate new namecache and broadcast
            this.service.broadcastView(viewRep, cluster);
        } catch (Exception e){
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
            System.out.println("disconnectClusterNoGraceful?");
            System.out.println(serviceMap.containsKey("ClientCluster"));
            System.out.println(serviceMap.get("ClientCluster"));
            for (Object cluster: serviceMap.keySet()) {
                String clusterName = cluster.toString();
                ClusterMap clusterMap = (ClusterMap) serviceMap.get(clusterName);
                System.out.println("1" + clusterName);
                for (Object eachUuid:clusterMap.getMap().keySet()) {
                    System.out.println("222");
                    System.out.println(uuid);
                    System.out.println(eachUuid);
                    Address add_each = (Address) eachUuid;
                    if (uuid.equals(add_each)){
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
