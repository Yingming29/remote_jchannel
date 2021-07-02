package cn.yingming.grpc1;

import com.google.protobuf.ByteString;
import com.sun.jdi.event.ExceptionEvent;
import io.grpc.jchannelRpc.*;
import org.apache.commons.collections.ListUtils;
import org.jgroups.*;
import org.jgroups.util.*;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
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
    ConcurrentHashMap<Address, String> nodesMap;
    ConcurrentHashMap<String, ClusterMap> serviceMap;
    LinkedList<String> state;
    ArrayList<Address> savedServerList;
    Object wait_obj;

    NodeJChannel(String node_name, String cluster_name, String grpcAddress) throws Exception {
        this.channel = new JChannel("grpc/protocols/udp.xml");
        this.user_name = System.getProperty("user.name", "n/a");
        this.node_name = node_name;
        this.cluster_name = cluster_name;
        this.grpcAddress = grpcAddress;
        this.nodesMap = new ConcurrentHashMap<>();
        this.lock = new ReentrantLock();
        this.service = null;
        this.wait_obj = new Object();
        this.serviceMap = new ConcurrentHashMap<String, ClusterMap>();
        this.serviceMap.put("ClientCluster", new ClusterMap());
        this.channel.setReceiver(this).connect(cluster_name);
        System.out.println(Thread.currentThread().toString());
        this.nodesMap.put(this.channel.getAddress(), this.grpcAddress);
        this.state = new LinkedList<>();
        this.savedServerList = new ArrayList<>();
        System.out.println("[JChannel] The current nodes in node cluster: " + this.nodesMap);
        // this.test_sleep();
        this.testMethod();
        this.channel.getState(null, 2000);
    }

    public void test_sleep(){
        try{
            Thread.sleep(1000);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void receive(Message msg) {
        System.out.println("first" + msg);
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
        } else if (msg.getObject() instanceof MessageReqRep){
            System.out.println("Receive a MessageReqRep");
            receiveMessageReqRep(msg.getObject());
        } else{
            // receive common Message from other real common JChannels
            byte[] b = null;
            /*
            try {
                ByteArrayDataOutputStream out = new ByteArrayDataOutputStream();
                msg.writeTo(out);
                out.buffer();
            } catch (IOException e) {
                e.printStackTrace();
            }

             */

            try {
                Message new_msg = msg.copy(true, false);
                //b = Util.objectToByteBuffer(new_msg);
                ByteArrayDataOutputStream out = new ByteArrayDataOutputStream();
                new_msg.writeTo(out);
                b = out.buffer();
                System.out.println("new " + new_msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("[JChannel] Receive a message from real common JChannel: " + b);
            MessageReqRep msgRep = MessageReqRep.newBuilder().setType(UtilsRJ.getMsgType(msg)).setMessageObj(ByteString.copyFrom(b)).build();
            Response rep = Response.newBuilder().setMessageReqRep(msgRep).build();
            service.broadcastResponse(rep);
            String line = msg.getSrc() + ": " + msg.getPayload();
            synchronized (state){
                state.add(line);
            }
        }


        // define receive string and byte for other
    }

    private void receiveMessageReqRep(MessageReqRep msg){
        Message msg_test = UtilsRJ.convertMessage(msg);
        if (msg_test.getDest() == this.channel.getAddress() || msg_test.getDest() == null){
            System.out.println("receiveMessageReqRep( broadcast): " + msg_test);
            Response rep = Response.newBuilder().setMessageReqRep(msg).build();
            service.broadcastResponse(rep);
            synchronized (state){
                String line = msg_test.getSrc() + ": " + msg_test.getPayload();
                state.add(line);
            }
        } else{
            System.out.println("[JChannel] Receive a message for unicast to a JChannel-Client.");
            this.service.unicast(msg);
        }
    }

    private void receiveProtobufMsg(Message msg){
        // update client information request
        if (msg.getObject() instanceof UpdateReqBetweenNodes){
            // check the Address of requester
            UpdateReqBetweenNodes req = msg.getObject();
            ByteArrayDataInputStream in = new ByteArrayDataInputStream(req.getAddress().toByteArray());
            UUID u = new UUID();
            lock.lock();
            try{
                u.readFrom(in);
                if (NameCache.getContents().containsKey(u)){
                    System.out.println("Confirm that the JChannel' Address in the existing NameCache.");
                    // generate message for the requester, NameCacheRep, ViewRep of clients,
                    UpdateNameCacheRep nameCacheRep = this.service.generateNameCacheMsg();
                    ClusterMap clusterInf = this.serviceMap.get("ClientCluster");
                    ViewRep viewRep = null;
                    UpdateRepBetweenNodes rep;
                    if (clusterInf.getCreator() != null){
                        viewRep = clusterInf.generateView();
                    }
                    if (clusterInf.getCreator() != null) {
                        rep = UpdateRepBetweenNodes.newBuilder().setClientView(viewRep)
                                .setNameCache(nameCacheRep).build();
                        System.out.println("Client Cluster != null");
                    } else{
                        System.out.println("Client Cluster == null");
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
            System.out.println("UpdateRepBetweenNodes Test: " + rep.getClientView());
            System.out.println("000000000000000");
            if (rep.hasClientView()){
                System.out.println(true);
            } else {
                System.out.println(false);
            }
            System.out.println("000000000000000");
            ViewRep view_rep = rep.getClientView();
            UpdateNameCacheRep nameCacheRep = rep.getNameCache();
            // StateRep stateRep = rep.getClientState();
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
                clusterInf = this.serviceMap.get("ClientCluster");
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
            ByteArrayDataInputStream in = new ByteArrayDataInputStream(disReq.getJchannelAddress().toByteArray());
            UUID u = new UUID();
            try{
                u.readFrom(in);
            } catch (Exception e){
                e.printStackTrace();
            }
            disconnectCluster(disReq.getCluster(), u);
        }
    }


    @Override
    public void getState(OutputStream output) throws Exception {
         synchronized (state){
             Util.objectToStream(state, new DataOutputStream(output));
         }
    }

    public void setState(InputStream input) throws Exception{

        ReentrantLock lock = new ReentrantLock();
        List<String> list;
        list = (List<String>) Util.objectFromStream(new DataInputStream(input));
        lock.lock();
        try{
            state.clear();
            state.addAll(list);
            System.out.println(list.size() + " messages in chat history.");
            list.forEach(System.out::println);

            System.out.println("JChannel setState(), broadcast the state to its all JChannel-Clients.");
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            this.getState(out);
            StateRep stateRep = StateRep.newBuilder().setState(ByteString.copyFrom(out.toByteArray())).build();
            Response rep = Response.newBuilder()
                    .setStateRep(stateRep)
                    .build();
            // send to this client
            service.broadcastResponse(rep);

        } finally {
            lock.unlock();
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
                System.out.println("disconnect 1");
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
                for (Address add : this.nodesMap.keySet()) {
                    if (add.equals(msg.getSrc()) && this.nodesMap.get(add).equals(new_add)){
                        same = true;
                    }
                }
                // condition 1.1, no change
                if (same){
                    System.out.println("[JChannel] Receive a confirmation from a node, but no change.");
                } else{
                    // condition 1.2 changed server list, update list and broadcast update servers
                    this.nodesMap.put(msg.getSrc(), new_add);
                    if (nodesMap.size() >=2 ){
                        System.out.println("notify");
                        nodesMap.notify();
                    }
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

    private void testMethod(){
        if (channel.getAddress().equals(channel.getView().getCoord())){
            return;
        }
        System.out.println(" nodemap:" + nodesMap);
        if (nodesMap.size() <= 1){
            System.out.println("<= 1" + nodesMap);
            synchronized (nodesMap){
                try{
                    System.out.println("wait");
                    nodesMap.wait(2000);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            /*
            if (nodesMap.size() <= 1){
                throw new IllegalArgumentException("Do not have enough JChannel-Server.");
            }

             */
        }
        if (!this.channel.getAddress().equals(this.channel.getView().getCoord())){
            ReentrantLock lock = new ReentrantLock();
            lock.lock();
            try {
                for (Address address: this.channel.getView().getMembers()) {
                    // && !this.nodesMap.get(address).equals("unknown")
                    System.out.println(address);
                    if (this.nodesMap.containsKey(address) && !address.equals(this.channel.getAddress())){
                        UUID u = (UUID) this.channel.getAddress();
                        ByteArrayDataOutputStream out = new ByteArrayDataOutputStream();
                        u.writeTo(out);
                        byte[] b = out.buffer();
                        UpdateReqBetweenNodes req = UpdateReqBetweenNodes.newBuilder().setAddress(ByteString.copyFrom(b)).build();
                        System.out.println("send a request to a JChannel-Server");
                        this.channel.send(address, req);
                        break;
                    }
                }
            } catch (Exception e){
                e.printStackTrace();
            }
            finally {
                lock.unlock();
            }
        }

    }


    // update the view of nodes
    @Override
    public void viewAccepted(View new_view) {
        System.out.println("** JChannel Node view: " + new_view);
        System.out.println(Thread.currentThread().toString() + "viewaccepted");
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
        if (view.getMembers().get(0).equals(this.channel.getAddress())){
            System.out.println("[JChannel] This is the coordinator of the node cluster.");
        } else {
            // send a UpdateRequest to
            System.out.println("Not coordinator of cluster.");
        }
    }

    public void compareNodes(List currentView, List currentNodesList){
        // add node
        synchronized (this.nodesMap) {
            if (currentView.size() > currentNodesList.size()) {
                System.out.println("[JChannel] Store new node inf.");
                List compare = ListUtils.subtract(currentView, currentNodesList);
                /*
                for (int i = 0; i < compare.size(); i++) {
                    this.nodesMap.put((Address) compare.get(i), "unknown");
                }

                 */
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
            clusterObj.addViewNum();
            // change:   update namecache to its clients
            NameCache.add(address, name);
            UpdateNameCacheRep updateName = this.service.generateNameCacheMsg();
            Response rep = Response.newBuilder().setUpdateNameCache(updateName).build();
            // here, the two broadcast()'s target is different, broadcastView() has a specific client-client.
            this.service.broadcastResponse(rep);
            ViewRep viewRep= clusterObj.generateView();
            System.out.println("connectCluster corrdinator:" + clusterObj.getCreator());
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
            m.removeClient(address);
            m.getMap().remove(address);
            System.out.println(address + " quits " + cluster);
            ClusterMap clusterObj = (ClusterMap) serviceMap.get(cluster);
            clusterObj.addViewNum();
            ViewRep viewRep= clusterObj.generateView();
            this.service.broadcastView(viewRep, cluster);
        } finally {
            lock.unlock();
        }
    }

    public void disconnectClusterNoGraceful(Address uuid){
        System.out.println("disconnect 2");
        this.lock.lock();
        try{
            String clusterName = "ClientCluster";
            ClusterMap clusterMap = (ClusterMap) serviceMap.get(clusterName);
            for (Object eachUuid:clusterMap.getMap().keySet()) {
                Address add_each = (Address) eachUuid;
                if (uuid.equals(add_each)){
                    System.out.println("Remove the JChannel-client from the client cluster.");
                    clusterMap.removeClient(uuid);
                    clusterMap.getMap().remove(uuid);
                    clusterMap.addViewNum();
                    if (clusterMap.getCreator() != null){
                        ViewRep viewRep= clusterMap.generateView();
                        this.service.broadcastView(viewRep, clusterName);
                    }
                }
            }
        } finally {
            this.lock.unlock();
        }

    }

}
