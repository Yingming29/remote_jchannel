package cn.yingming.grpc1;

import com.google.protobuf.ByteString;
import io.grpc.jchannelRpc.*;
import org.apache.commons.collections.ListUtils;
import org.jgroups.*;
import org.jgroups.util.*;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class NodeJChannel implements Receiver{
    public JChannel channel;
    private String cluster_name;
    private ReentrantLock lock;
    private NodeServer.JChannelsServiceImpl service;
    private String grpcAddress;
    public final ConcurrentHashMap<Address, String> nodesMap;
    public ConcurrentHashMap<String, ClientInfo> serviceMap;
    public final LinkedList<String> state;

    NodeJChannel(String cluster_name, String grpcAddress) throws Exception {
        this.channel = new JChannel("grpc/protocols/udp.xml");
        this.cluster_name = cluster_name;
        this.grpcAddress = grpcAddress;
        this.nodesMap = new ConcurrentHashMap<>();
        this.lock = new ReentrantLock();
        this.service = null;
        this.state = new LinkedList<>();
        this.serviceMap = new ConcurrentHashMap<>();
    }


    public void start() throws Exception {
        System.out.println("---JChannel Starts.---");
        this.serviceMap.put("ClientCluster", new ClientInfo());
        this.channel.setReceiver(this).connect(cluster_name);
        this.nodesMap.put(this.channel.getAddress(), this.grpcAddress);
        System.out.println("[JChannel-Server] The current nodes in node cluster: " + this.nodesMap);
        this.updateMethod();
        // ?
        this.channel.getState(null, 2000);
    }

    @Override
    public void receive(Message msg) {
        /** two conditions for receiving Message,
         * 1.receive the Message from other real JChannel. They do not send any Protobuf message type.
         * 2.receive the Message from other JChannel-server. They can send message, which contains Protobuf message object.
         *
         */
        if(msg.getType() == 3){
            if (msg.getObject() instanceof ChannelMsg){
                receiveChannelMsg(msg);
            } else if (msg.getObject() instanceof Request){
                receiveConDiscon(msg);
            } else if (msg.getObject() instanceof MessageReqRep){
                receiveMessageReqRep(msg.getObject());
            } else {
                // receive common Message from other real common JChannels
                System.out.println("[JChannel-Server] Receive a message from a real common JChannel: " + msg);
                System.out.println("Message type num:" + msg.getType() + ", " + msg.getClass());
                byte[] b = null;
                try {
                    Message new_msg = msg.copy(true, false);
                    ByteArrayDataOutputStream out = new ByteArrayDataOutputStream();
                    new_msg.writeTo(out);
                    b = out.buffer();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                MessageReqRep msgRep = MessageReqRep.newBuilder().setType(msg.getType()).setMessageObj(ByteString.copyFrom(b)).build();
                Response rep = Response.newBuilder().setMessageReqRep(msgRep).build();
                service.broadcastResponse(rep);
                // add a broadcast to python clients
                service.broadcastResponsePy(msg.getSrc().toString(), msg.getObject().toString());
                String line = generateLine(msg);
                synchronized (state){
                    state.add(line);
                }
            }
        } else if (msg.getType() !=3 && 0 <= msg.getType() && msg.getType() < 6){
            // receive common Message from other real common JChannels
            System.out.println("[JChannel-Server] Receive a message from a common JChannel: " + msg);
            System.out.println("Message type num:" + msg.getType() + ", " + msg.getClass());
            byte[] b = null;
            try {
                if (msg.getType() == 4){
                    long num = ((LongMessage) msg).getValue();
                    Message new_long = new LongMessage(msg.getDest(), num);
                    System.out.println("old:" + msg + ",  " + msg.getFlags() + ", " + msg.getHeaders());
                    new_long.setSrc(msg.getSrc());
                    System.out.println("new:" + new_long + ",  " + new_long.getFlags() + ", " + msg.getHeaders());
                    b = Util.objectToByteBuffer(new_long);
                } else {
                    Message new_msg = msg.copy(true, false);
                    ByteArrayDataOutputStream out = new ByteArrayDataOutputStream();
                    new_msg.writeTo(out);
                    b = out.buffer();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            MessageReqRep msgRep = MessageReqRep.newBuilder().setType(msg.getType()).setMessageObj(ByteString.copyFrom(b)).build();
            Response rep = Response.newBuilder().setMessageReqRep(msgRep).build();
            service.broadcastResponse(rep);
            // copy from Receiver of JChannelClient and JChannelStarter
            String line = generateLine(msg);
            System.out.println(line);
            service.broadcastResponsePy(msg.getSrc().toString(), msg.toString());
            synchronized (state){
                state.add(line);
            }
            System.out.println("-------------");
        } else {
            System.out.println("[JChannel-Server] Receive an invalid Message type.");
        }

    }
    private String generateLine(Message msg){

        System.out.println("-------------");
        String line = null;
        if (msg instanceof EmptyMessage){
            line = msg.getSrc() + ": " + "EmptyMessage";
        } else if (msg instanceof CompositeMessage){
            CompositeMessage compMsg = (CompositeMessage) msg;
            LinkedList<String> compMsgList = new LinkedList<>();
            compMsg.forEach(eachOne -> compMsgList.add(eachOne.getObject()));
            line = msg.getSrc() + " (CompositeMessage): " + compMsgList;
            System.out.println(msg);
        } else if (msg instanceof BytesMessage){
            line = msg.getSrc() + " (BytesMessage): " + msg.getPayload();
            String result = new String((byte[]) msg.getPayload());
            System.out.println("verify bytes: " + result);
        } else if (msg instanceof NioMessage){
            NioMessage nioMsg = (NioMessage) msg;
            line = msg.getSrc() + "(NioMessage): " + msg.getPayload();
            CharBuffer charBuffer = null;
            ByteBuffer buffer = nioMsg.getPayload();
            String result_bb = null;
            try{
                Charset charSet = StandardCharsets.UTF_8;
                CharsetDecoder decoder = charSet.newDecoder();
                charBuffer = decoder.decode(buffer);
                buffer.flip();
                result_bb = charBuffer.toString();
            } catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("verify ByteBuffer: " + result_bb);
        } else if (msg instanceof LongMessage){
            line = msg.getSrc() + "(LongMessage): " + msg.getObject();
        } else {
            line = msg.getSrc() + "(ObjectMessage): " + msg.getPayload();
        }
        return line;
    }

    private void receiveMessageReqRep(MessageReqRep msg){
        Message dese_msg = UtilsRJ.convertMessage(msg);
        System.out.println("[JChannel-Server] Receive a MessageReqRep, the dest is " + dese_msg.getDest());
        if (dese_msg.getDest() == null){
            // System.out.println("null here ");
            System.out.println("[JChannel-Server] Receive a message from a JChannel-Server for broadcast: " + dese_msg);
            Response rep = Response.newBuilder().setMessageReqRep(msg).build();
            service.broadcastResponse(rep);
            // python broadcast
            String contentPy = null;
            if (dese_msg.getType() == 3){
                contentPy =  dese_msg.getObject().toString();
            } else if (dese_msg.getType() == 2){
                contentPy = "EmptyMessage";
            } else {
                contentPy = dese_msg.toString();
            }
            service.broadcastResponsePy(dese_msg.getSrc().toString(), contentPy);
            synchronized (state){
                //String line = msg_test.getSrc() + ": " + msg_test.getPayload();
                String line = generateLine(dese_msg);
                state.add(line);
            }
        } else if (dese_msg.getDest().equals(this.channel.getAddress())){
            System.out.println("[JChannel-Server] Receive a message from a JChannel-Server for unicast to this JChannel-Server (contain its all clients): " + dese_msg);
            Response rep = Response.newBuilder().setMessageReqRep(msg).build();
            service.broadcastResponse(rep);
            // python broadcast
            if (dese_msg.getType() == 3){
                service.broadcastResponsePy(dese_msg.getSrc().toString(), dese_msg.getObject().toString());
            } else{
                service.broadcastResponsePy(dese_msg.getSrc().toString(), dese_msg.toString());
            }
            synchronized (state){
                String line = generateLine(dese_msg);
                state.add(line);
            }
        } else{
            System.out.println("[JChannel] Receive a message from other JChannel-Server for unicast to a JChannel-Client:" + dese_msg);
            this.service.unicast(msg);
            this.service.unicastPy(dese_msg.getSrc().toString(), dese_msg.getObject().toString(), dese_msg.getDest());
        }

        //System.out.println("Test3, the current NodeMap:" + nodesMap);
    }

    public void checkNodes(){
        synchronized (nodesMap){
            for (Address each: nodesMap.keySet()){
                if (nodesMap.get(each) == null || nodesMap.get(each).equals("")){
                   System.out.println("[JChannel-Server] Found null in the node map and delete it.");
                   nodesMap.remove(each);
                } else if (!nodesMap.get(each).startsWith("127.0.0.1")){
                    System.out.println("[JChannel-Server] Found invalid grpc address and delete it.");
                    nodesMap.remove(each);
                }
                if (!this.channel.getView().containsMember(each)){
                    System.out.println("[JChannel-Server] Found invalid JChannel.");
                    //System.out.println("Test4: " + nodesMap);
                }
            }
        }
    }

    private void receiveConDiscon(Message msg){
        if (msg.getObject() instanceof Request && ((Request) msg.getObject()).hasConnectRequest()){
            ConnectReq conReq = ((Request) msg.getObject()).getConnectRequest();
            System.out.println("[JChannel] Receive a shared connect() request for updating ClientCluster.");
            // change: After the server receive the connect() result, it generate a
            lock.lock();
            try{
                UUID u = new UUID();
                ByteArrayDataInputStream in = new ByteArrayDataInputStream(conReq.getJchannAddressByte().toByteArray());
                u.readFrom(in);
                NameCache.add(u, conReq.getLogicalName());
                connectCluster("ClientCluster", u, conReq.getLogicalName());
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        } else if (msg.getObject() instanceof Request && ((Request) msg.getObject()).hasDisconnectRequest()){
            DisconnectReq disReq = ((Request) msg.getObject()).getDisconnectRequest();
            if (!disReq.getStr().equals("")){
                System.out.println("[JChannel] Receive a shared disconnect() request for updating ClientCluster.");
                for (Address add:NameCache.getContents().keySet()){
                    if (NameCache.getContents().get(add).equals(disReq.getStr())){
                        disconnectCluster("ClientCluster", add);
                    }
                }

            } else{
                System.out.println("[JChannel] Receive a shared disconnect() request for updating ClientCluster.");
                ByteArrayDataInputStream in = new ByteArrayDataInputStream(disReq.getJchannelAddress().toByteArray());
                UUID u = new UUID();
                try{
                    u.readFrom(in);
                } catch (Exception e){
                    e.printStackTrace();
                }
                disconnectCluster("ClientCluster", u);
            }
        }
    }


    @Override
    public void getState(OutputStream output) throws Exception {
         synchronized (state){
             Util.objectToStream(state, new DataOutputStream(output));
         }
    }

    public void setState(InputStream input) throws Exception{
        System.out.println("[JChannel-Server] setState() of Receiver.");
        ReentrantLock lock = new ReentrantLock();
        List<String> list;
        list = (List<String>) Util.objectFromStream(new DataInputStream(input));
        synchronized (state){
            state.clear();
            state.addAll(list);
            System.out.println(list.size() + " messages in chat history.");
            list.forEach(System.out::println);

            if (this.service != null) {
                System.out.println("JChannel setState(), broadcast the state to its all JChannel-Clients.");
                byte[] state_byte = Util.objectToByteBuffer(state);
                StateRep stateRep = StateRep.newBuilder().setState(ByteString.copyFrom(state_byte)).build();
                Response rep = Response.newBuilder()
                        .setStateRep(stateRep)
                        .build();
                // send to this client
                System.out.println(rep);
                service.broadcastResponse(rep);
            }
        }
    }


    public void receiveChannelMsg(Message msg){
        ChannelMsg cmsg = msg.getObject();
        if (cmsg.hasExchangeMsg()){
            ExchangeMsg exMsg = cmsg.getExchangeMsg();
            if (exMsg.getType().equals("[DisconnectNotGrace]")){
                UUID u = new UUID();
                ByteArrayDataInputStream in = new ByteArrayDataInputStream(exMsg.getContentByt().toByteArray());
                ReentrantLock sublock = new ReentrantLock();
                sublock.lock();
                try {
                    System.out.println("[JChannel-Server] Receive a message from other JChannel-Server for not graceful disconnect of a client.");
                    u.readFrom(in);
                    disconnectClusterNoGraceful(u);
                } catch (Exception e){
                    e.printStackTrace();
                } finally {
                    sublock.unlock();
                }
            } else if (exMsg.getType().equals("grpcAddress")){
                synchronized (this.nodesMap){
                    // update the grpc addresses of other JChannel-Server
                    String new_add = exMsg.getContentStr();
                    boolean same = false;
                    for (Address add : this.nodesMap.keySet()) {
                        if (add.equals(msg.getSrc()) && this.nodesMap.get(add).equals(new_add)){
                            same = true;
                        }
                    }
                    // condition 1.1, no change
                    if (same){
                        System.out.println("[JChannel-Server] Receive a grpc address from other JChannel-Server, " +
                                "but it is existing in the server-address Map, no change.");
                    } else{
                        // condition 1.2 changed server list, update list and broadcast update servers
                        this.nodesMap.put(msg.getSrc(), new_add);
                        if (nodesMap.size() >=2 ){
                            //System.out.println("notify");
                            nodesMap.notify();
                        }
                        System.out.println("[JChannel-Server] Receive a grpc address from other JChannel-Server, update server-address Map.");
                        System.out.println("[JChannel-Server] After updating: " + this.nodesMap);
                        UpdateRep updateMsg = UpdateRep.newBuilder()
                                .setAddresses(generateAddMsg())
                                .build();
                        Response broMsg = Response.newBuilder()
                                .setUpdateResponse(updateMsg)
                                .build();
                        this.service.broadcastResponse(broMsg);
                    }
                }
            } else {
                System.out.println("Invalid type of Exchange message");
            }
        } else if(cmsg.hasUpdateReqBetweenNodes()){
            // update client information request
            // check the Address of requester
            System.out.println("[JChannel-Server] Receive a update request from other new JChannel-Server, " + msg.getSrc());
            UpdateReqBetweenNodes req = cmsg.getUpdateReqBetweenNodes();
            ByteArrayDataInputStream in = new ByteArrayDataInputStream(req.getAddress().toByteArray());
            UUID u = new UUID();
            lock.lock();
            try{
                u.readFrom(in);
                Address address = (Address) u;
                if (nodesMap.keySet().contains(u)){
                    System.out.println("[JChannel-Server] Confirm the requester is a JChannel-Server");
                    // generate message for the requester, NameCacheRep, ViewRep of clients,
                    UpdateNameCacheRep nameCacheRep = this.service.generateNameCacheMsg();
                    ClientInfo clusterInf = this.serviceMap.get("ClientCluster");
                    ViewRep viewRep = null;
                    UpdateRepBetweenNodes rep;
                    if (clusterInf.getCreator() != null){
                        viewRep = clusterInf.generateView();
                    }
                    if (clusterInf.getCreator() != null) {
                        rep = UpdateRepBetweenNodes.newBuilder().setClientView(viewRep)
                                .setNameCache(nameCacheRep).build();
                        //System.out.println("Client Cluster != null");
                    } else{
                        //System.out.println("Client Cluster == null");
                        rep = UpdateRepBetweenNodes.newBuilder().setNameCache(nameCacheRep).build();
                    }
                    ChannelMsg cmsgRep = ChannelMsg.newBuilder().setUpdateRepBetweenNodes(rep).build();
                    Message msgRep = new ObjectMessage(msg.getSrc(), cmsgRep);
                    this.channel.send(msgRep);
                    System.out.println("UpdateRepBetweenNodes: " + rep);
                    System.out.println("[JChannel-Server] The JChannel-server provides updating date for the new JChannel-server.");
                } else{
                    System.out.println("The requester does not exist in the NodeMap or is not a JChannel-Server.");
                }
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        } else if (cmsg.hasUpdateRepBetweenNodes()) {
            System.out.println("[JChannel-Server] Receive update response from other JChannel-Server, " + msg.getSrc());
            UpdateRepBetweenNodes rep = cmsg.getUpdateRepBetweenNodes();
            //System.out.println(rep.hasClientView());
            ViewRep view_rep = rep.getClientView();
            UpdateNameCacheRep nameCacheRep = rep.getNameCache();
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
                lock.lock();
                try {
                    NameCache.add(u, nameList.get(i));
                } finally {
                    lock.unlock();
                }
            }
            System.out.println("[JChannel-Server] Update the NameCache of JChannel-Client.");
            // 2.update client view
            ClientInfo clusterInf;
            if (this.serviceMap.size() == 0){
                clusterInf = new ClientInfo();
                this.serviceMap.put("ClientCluster", clusterInf);
            } else{
                clusterInf = this.serviceMap.get("ClientCluster");
            }
            View v = new View();
            ByteArrayDataInputStream in = new ByteArrayDataInputStream(view_rep.getView().toByteArray());
            try {
                v.readFrom(in);
                clusterInf.setFromView(v);
            } catch (Exception e){
                e.printStackTrace();
            }
            //System.out.println(v);
        }
    }

    public String generateAddMsg(){
        StringBuilder sb = new StringBuilder();
        synchronized (this.nodesMap){
            for (Address each:this.nodesMap.keySet()) {
                sb.append(this.nodesMap.get(each)).append(" ");
            }
            String str = sb.toString().trim();
            return str;
        }
    }

    public void setService(NodeServer.JChannelsServiceImpl gRPCservice){
        this.service = gRPCservice;
    }

    private void updateMethod(){
        if (channel.getAddress().equals(channel.getView().getCoord())){
            return;
        }
        // change?
        if (nodesMap.size() <= 1){
            synchronized (nodesMap){
                try{
                    //System.out.println("wait");
                    nodesMap.wait(3000);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        if (!this.channel.getAddress().equals(this.channel.getView().getCoord())){
            ReentrantLock lock = new ReentrantLock();
            lock.lock();
            try {
                for (Address address: this.channel.getView().getMembers()) {
                    // System.out.println(address);
                    if (this.nodesMap.containsKey(address) && !address.equals(this.channel.getAddress())){
                        UUID u = (UUID) this.channel.getAddress();
                        ByteArrayDataOutputStream out = new ByteArrayDataOutputStream();
                        u.writeTo(out);
                        byte[] b = out.buffer();
                        UpdateReqBetweenNodes req = UpdateReqBetweenNodes.newBuilder().setAddress(ByteString.copyFrom(b)).build();
                        ChannelMsg cmsg = ChannelMsg.newBuilder().setUpdateReqBetweenNodes(req).build();
                        System.out.println("[JChannel-Server] Send a request to a JChannel-Server for update date.");
                        this.channel.send(address, cmsg);
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
        System.out.println("** JChannel view: " + new_view);
       //  System.out.println(Thread.currentThread() + "viewaccepted");
        /* When the view is changed by any action, it will send its address to other jchannels
        and update its nodesList.
         */
        // compare keySet of nodesList with view list.
        List<Address> currentView = new_view.getMembers();
        List<Address> currentNodesList = new ArrayList<>(this.nodesMap.keySet());
        compareNodes(currentView, currentNodesList);
        // checkClusterMap(new_view);

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
        }
    }

    public void compareNodes(List<Address> currentView, List<Address> currentNodesList){
        // add node
        synchronized (this.nodesMap) {
            if (currentView.size() > currentNodesList.size()) {
                // System.out.println("[JChannel-Server] Store new node inf.");
                System.out.println("[JChannel-Server] The current nodes in node cluster: " + this.nodesMap);
                sendMyself();
            } else{
                System.out.println("[JChannel-Server] Found JChannel's leaving. Start comparing.");
                List<Address> compare = ListUtils.subtract(currentNodesList, currentView);
                for (Address each:compare) {
                    this.nodesMap.remove(each);
                }
                System.out.println("[JChannel-Server] The current nodes in node cluster: " + this.nodesMap);
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

    public void sendMyself(){
        // send the address of its gRPC server address
        ExchangeMsg exMsg = ExchangeMsg.newBuilder().setType("grpcAddress").setContentStr(this.grpcAddress).build();
        ChannelMsg cmsg = ChannelMsg.newBuilder().setExchangeMsg(exMsg).build();
        Message msg = new ObjectMessage(null, cmsg);
        // send messages exclude itself.
        msg.setFlagIfAbsent(Message.TransientFlag.DONT_LOOPBACK);
        try{
            this.channel.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("[JChannel-Server] Send the grpc address(" + this.grpcAddress + ") of this JChannel-Server to cluster .");
    }
    public void connectClusterPy(String cluster, Address address, String name){
        lock.lock();
        try{
            // create the cluster or connect an existing cluster.
            System.out.println("[gRPC-Server] " + address + "(" +  name + ") connects to client cluster: " + cluster);
            // create new cluster object and set it as the creator
            ClientInfo clusterObj = this.serviceMap.get("ClientCluster");
            clusterObj.getMap().put(address, name);
            clusterObj.addMember(address);
            clusterObj.addViewNum();
            NameCache.add(address, name);
            UpdateNameCacheRep updateName = this.service.generateNameCacheMsg();
            Response rep = Response.newBuilder().setUpdateNameCache(updateName).build();
            // here, the two broadcast()'s target is different, broadcastView() has a specific client-client.
            this.service.broadcastResponse(rep);
            System.out.println("[gRPC-Server] Return a message for updating NameCache.");
            ViewRep viewRep= clusterObj.generateView();
            System.out.println("[gRPC-Server] Return a message for Client view.");
            this.service.broadcastView(viewRep);
            // python client part

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    // add view action and forward
    public void connectCluster(String cluster, Address address, String name){
        lock.lock();
        try{
            // create the cluster or connect an existing cluster.
            System.out.println("[gRPC-Server] " + address + "(" +  name + ") connects to client cluster: " + cluster);
            // create new cluster object and set it as the creator
            ClientInfo clusterObj = this.serviceMap.get("ClientCluster");
            clusterObj.getMap().put(address, name);
            clusterObj.addMember(address);
            clusterObj.addViewNum();
            NameCache.add(address, name);
            UpdateNameCacheRep updateName = this.service.generateNameCacheMsg();
            Response rep = Response.newBuilder().setUpdateNameCache(updateName).build();
            // here, the two broadcast()'s target is different, broadcastView() has a specific client-client.
            this.service.broadcastResponse(rep);
            System.out.println("[gRPC-Server] Return a message for updating NameCache.");
            ViewRep viewRep= clusterObj.generateView();
            System.out.println("[gRPC-Server] Return a message for Client view.");
            this.service.broadcastView(viewRep);

            View v = clusterObj.getView();
            this.service.broadcastViewPy(v);
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
            ClientInfo clusterObj = serviceMap.get(cluster);
            clusterObj.removeClient(address);
            clusterObj.getMap().remove(address);
            clusterObj.addViewNum();
            System.out.println(address + " quits " + cluster);
            if (clusterObj.getList().size()>0){
                ViewRep viewRep= clusterObj.generateView();
                this.service.broadcastView(viewRep);
                this.service.broadcastViewPy(clusterObj.getView());
            }
        } finally {
            lock.unlock();
        }
    }

    public void disconnectClusterNoGraceful(Address uuid){
        //System.out.println("disconnect 2");
        this.lock.lock();
        try{
            String clusterName = "ClientCluster";
            ClientInfo clientInfo = serviceMap.get(clusterName);
            for (Object eachUuid: clientInfo.getMap().keySet()) {
                Address add_each = (Address) eachUuid;
                if (uuid.equals(add_each)){
                    System.out.println("Remove the JChannel-client from the client cluster.");
                    clientInfo.removeClient(uuid);
                    clientInfo.getMap().remove(uuid);
                    clientInfo.addViewNum();
                    if (clientInfo.getCreator() != null){
                        ViewRep viewRep= clientInfo.generateView();
                        this.service.broadcastView(viewRep);
                        this.service.broadcastViewPy(clientInfo.getView());
                    }
                }
            }
        } finally {
            this.lock.unlock();
        }

    }

}
