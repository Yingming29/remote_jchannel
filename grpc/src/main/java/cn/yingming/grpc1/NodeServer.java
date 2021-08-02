package cn.yingming.grpc1;

import com.google.protobuf.ByteString;
import io.grpc.*;
import io.grpc.jchannelRpc.*;
import io.grpc.stub.StreamObserver;
import org.jgroups.*;
import org.jgroups.util.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

// The node is given with a gRPC server and a JChannel.
public class NodeServer {
    //  Port and server object of grpc server
    private int port;
    private Server server;
    // <no, ip>, it stores all ip address for clients, who are connecting to this server, not useful.
    private ConcurrentHashMap<Integer, String> ips;
    //  cluster name of JChannel-Server
    private NodeJChannel jchannel;
    private JChannelsServiceImpl gRPCservice;

    public NodeServer(int port, String cluster_name) throws Exception {
        // port, cluster name of the server
        this.port = port;
        // not useful, store clients address.
        this.ips = new ConcurrentHashMap<>();
        // create JChannel given node name and cluster name
        this.jchannel = new NodeJChannel(cluster_name, "127.0.0.1:" + port);
        // create grpc server, and its service is given the jchannel for calling send() method on jchannel.
        this.gRPCservice = new JChannelsServiceImpl(this.jchannel);
        this.server = ServerBuilder.forPort(port)
                .addService(this.gRPCservice)  // service for bidirectional streaming
                .intercept(new ClientAddInterceptor())
                .build();
    }

    // Start grpc
    private void start() throws Exception {
        // start the JChannel and join to a cluster
        this.jchannel.start();
        // Give the entry of gRPC for calling broadcast method of grpc server.
        this.giveEntry(this.gRPCservice);
        this.server.start();
        System.out.println("---gRPC Server Starts.---");
        // The method will run before closing the server.
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.err.println("---shutting down gRPC server since JVM is shutting down---");
                NodeServer.this.stop();
                System.err.println("---server shut down---");
            }
        });
    }

    // Stop gRPC server
    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }
    // Server blocks until it closes.
    private void blockUntilShutdown() throws InterruptedException {
        if (server!=null){
            server.awaitTermination();
        }
    }

    // After creation of gRPC, give the service object to Jchannel for calling broadcast().
    public void giveEntry(JChannelsServiceImpl gRPCservice){
        // set service method of JChannel.
        this.jchannel.setService(gRPCservice);
    }
    // gRPC service
    class JChannelsServiceImpl extends JChannelsServiceGrpc.JChannelsServiceImplBase {
        // HashMap for storing the clients, includes uuid and StreamObserver.
        private final ConcurrentHashMap<Address, StreamObserver<Response>> clients;
        private final ConcurrentHashMap<Address, StreamObserver<Response>> py_clients;
        protected final ReentrantLock lock;
        NodeJChannel jchannel;

        // Constructor with JChannel for calling send() method.
        private JChannelsServiceImpl(NodeJChannel jchannel) throws Exception {
            this.jchannel = jchannel;
            this.lock = new ReentrantLock();
            this.clients = new ConcurrentHashMap<>();
            this.py_clients = new ConcurrentHashMap<>();
        }
        // service 1, bi-directional streaming rpc
        public StreamObserver<Request> connect(StreamObserver<Response> responseObserver){
            return new StreamObserver<Request>() {
                @Override
                public void onNext(Request req) {
                    for (Address add:jchannel.nodesMap.keySet()){
                        String each = jchannel.nodesMap.get(add);
                        if (each.equals("unknown") || each.equals("")){
                            jchannel.nodesMap.remove(add);
                            System.out.println("[gRPC-Server] Remove a node in the NodeMap, which does not contain gRPC address.");
                        }
                    }
                    /* Condition1
                       Receive the connect() request.
                     */
                    if (req.hasConnectRequest()){
                        System.out.println("[gRPC-Server] Receive the connect() request from a client.");
                        System.out.println("[gRPC-Server] A JChannel-client connects to this JChannel-node (JChannel-server)");
                        // Description: connect(String cluster) request:
                        Address generated_address = null;
                        String generated_name = null;
                        if (!req.getConnectRequest().getReconnect()){
                            // 1.The server generates the Address and the logical name
                            // for the requester with the first connect to JChannel-node.
                            generated_address = UUID.randomUUID();
                            generated_name = Util.generateLocalName();
                            System.out.println("[gRPC-Server] New connected JChannel-Client, the server generates an Address for it: " +
                                    generated_address + ", and logical name : " + generated_name);

                        } else{
                            // 1. if it is not first connect, it will contain a JChannelAddress property.
                            UUID u = new UUID();
                            ByteArrayDataInputStream in = new ByteArrayDataInputStream(req.getConnectRequest().getJchannAddressByte().toByteArray());
                            try {
                                u.readFrom(in);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            generated_address = u;
                            generated_name = req.getConnectRequest().getLogicalName();
                            System.out.println("[gRPC-Server] A reconnecting JChannel-Client, Address: " +
                                    generated_address + ", and logical name : " + generated_name);
                        }
                        // 2. Store the responseObserver of the client. Map<Address, streamObserver>
                        join(responseObserver, generated_address, generated_name);
                        // 3. forward the connect request with generated Address and generated logical name to other nodes for storing
                        ByteArrayDataOutputStream out = new ByteArrayDataOutputStream();
                        UUID u = (UUID) generated_address;
                        try {
                            u.writeTo(out);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        ConnectReq conReqWithAddress = ConnectReq.newBuilder()
                                .setJchannAddressByte(ByteString.copyFrom(out.buffer()))
                                .setLogicalName(generated_name)
                                .build();
                        Request reqForward = Request.newBuilder().setConnectRequest(conReqWithAddress).build();
                        forwardMsg(reqForward);
                        // 4. store the generated Address and logcial to this node's NameCache
                        NameCache.add(generated_address, generated_name);
                    /*  Condition2
                        Receive the disconnect() request.
                     */
                    } else if (req.hasDisconnectRequest()){
                        System.out.println("[gRPC-Server] Receive the disconnect() request from a client.");
                        System.out.println("[gRPC-Server] " + req.getDisconnectRequest().getJchannelAddress() + " sends a disconnect request for grpc channel.");
                        ByteArrayDataInputStream in = new ByteArrayDataInputStream(req.getDisconnectRequest().getJchannelAddress().toByteArray());
                        UUID u = new UUID();
                        try{
                            u.readFrom(in);
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                        // remove responseObserver for the disconnect()
                        lock.lock();
                        try {
                            // 1. remove the client responseObserver
                            for (Address add : clients.keySet()) {
                                if (add.equals(u)){
                                    DisconnectRep disRep = DisconnectRep.newBuilder().setResult(true).build();
                                    Response rep = Response.newBuilder().setDisconnectResponse(disRep).build();
                                    responseObserver.onNext(rep);
                                    clients.remove(add);
                                }
                            }
                            // 2. remove the client from its cluster information
                            jchannel.disconnectCluster("ClientCluster", u);
                        } finally {
                            lock.unlock();
                        }
                        // also notify other nodes to delete it
                        forwardMsg(req);
                        onCompleted();
                    } else if (req.hasStateReq()){
                        StateReq stateReq = req.getStateReq();
                        UUID source = null;
                        UUID target = null;
                        try {
                            source = Util.objectFromByteBuffer(stateReq.getJchannelAddress().toByteArray());
                            if (stateReq.getTarget().isEmpty()){
                                target = null;
                                System.out.println("[gRPC] target is null");
                            } else {
                                target = Util.objectFromByteBuffer(stateReq.getTarget().toByteArray());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        System.out.println("[gRPC-Server] Receive the getState() request for message history from a client.");
                        System.out.println("[gRPC-Server] " + source + "(JChannel-Client) calls getState(), target is " + target + ", tiemout is "
                                + stateReq.getTimeout());
                        lock.lock();
                        try {
                            // generate the history from real JChannel
                            if (target == null && !jchannel.channel.getAddress().equals(jchannel.channel.getView().getCoord())){
                                if (stateReq.getTimeout() != 0) {
                                    System.out.println("[JChannel-Server] JChannel invokes getState(null, Timeout)");
                                    jchannel.channel.getState(null, stateReq.getTimeout());
                                } else{
                                    System.out.println("[JChannel-Server] JChannel invokes getState(null, Timeout(default 2000))");
                                    jchannel.channel.getState(null, 2000);
                                }
                            } else if (target == null && jchannel.channel.getAddress().equals(jchannel.channel.getView().getCoord())){
                                ByteArrayOutputStream out = new ByteArrayOutputStream();
                                System.out.println("[JChannel-Server] JChannel invokes getState(null, Timeout)");
                                jchannel.getState(out);
                                StateRep stateRep = StateRep.newBuilder().setState(ByteString.copyFrom(out.toByteArray())).build();
                                Response rep = Response.newBuilder()
                                        .setStateRep(stateRep)
                                        .build();
                                unicastRep(rep, source);
                            } else if (target.equals(jchannel.channel.getAddress())){
                                ByteArrayOutputStream out = new ByteArrayOutputStream();
                                jchannel.getState(out);
                                StateRep stateRep = StateRep.newBuilder().setState(ByteString.copyFrom(out.toByteArray())).build();
                                Response rep = Response.newBuilder()
                                        .setStateRep(stateRep)
                                        .build();
                                // unicast the state to a JChannel-Client
                                unicastRep(rep, source);
                            } else if (jchannel.channel.getView().containsMember(target) && !target.equals(jchannel.channel.getAddress())){
                                // Invoke the real getState() of JChannel to other JChannel.
                                if (stateReq.getTimeout() != 0) {
                                    System.out.println("[JChannel-Server] JChannel invokes getState(Target, Timeout)");
                                    jchannel.channel.getState(target, stateReq.getTimeout());
                                } else{
                                    System.out.println("[JChannel-Server] JChannel invokes getState(Target, Timeout(default 2000))");
                                    jchannel.channel.getState(target, 2000);
                                }
                            } else {
                                System.out.println("[gRPC-Server] Invalid target Address of getState()");
                            }
                        } catch (Exception e){
                            e.printStackTrace();
                        } finally {
                            lock.unlock();
                        }

                    }  else if (req.hasGetAddressReq()){
                        /*
                        Receive the getAddress() request for getting the real JChannel-Server's Address
                        If the real JChannel of node does not work, return a response with isWork "false".
                        */
                        GetAddressReq getAddressReq = req.getGetAddressReq();
                        System.out.println("[gRPC-Server] Receive the getAddress() request for the JChannel-server Address from JChannel-Client:"
                                + getAddressReq.getJchannelAddress());
                        GetAddressRep getAddressRep;
                        if (jchannel.channel.getAddress() != null){
                            // convert the Address of real JChannel to bytes and put in the message
                            ByteArrayDataOutputStream outputStream = new ByteArrayDataOutputStream();
                            try {
                                jchannel.channel.getAddress().writeTo(outputStream);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            byte[] add_byte = outputStream.buffer();
                            getAddressRep = GetAddressRep.newBuilder()
                                    .setAddress(ByteString.copyFrom(add_byte))
                                    .setIsWork(true)
                                    .build();
                        } else{
                            getAddressRep = GetAddressRep.newBuilder()
                                    .setIsWork(false)
                                    .build();
                        }
                        Response rep = Response.newBuilder().setGetAddressRep(getAddressRep).build();
                        System.out.println(rep);
                        responseObserver.onNext(rep);
                    } else if(req.hasGetNameReq()){
                        // getName() request
                        GetNameReq getNameReq = req.getGetNameReq();
                        System.out.println("[gRPC-Server] Receive the getName() request for the JChannel-server DumpStats from JChannel-Client:"
                                + getNameReq.getJchannelAddress());
                        GetNameRep getNameRep;
                        if (jchannel.channel.getName() != null){
                            getNameRep = GetNameRep.newBuilder().setName(jchannel.channel.getName()).build();
                        } else{
                            getNameRep = GetNameRep.newBuilder().build();
                        }
                        Response rep = Response.newBuilder().setGetNameRep(getNameRep).build();
                        System.out.println(rep);
                        responseObserver.onNext(rep);

                    } else if(req.hasDumpStatsReq()){
                        // getName() request
                        DumpStatsReq dumpReq = req.getDumpStatsReq();
                        System.out.println("[gRPC-Server] Receive the dumpStats() request for the JChannel-server DumpStats from JChannel-Client:"
                                + dumpReq.getJchannelAddress());
                        Map m = null;
                        if (dumpReq.getAttrsList().size() == 0 && dumpReq.getProtocolName().equals("")){
                            m = jchannel.channel.dumpStats();
                        } else if (dumpReq.getAttrsList().size() == 0 && !dumpReq.getProtocolName().equals("")){
                            m = jchannel.channel.dumpStats(dumpReq.getProtocolName());
                        } else if (dumpReq.getAttrsList().size() > 0 && !dumpReq.getProtocolName().equals("")){
                            List protocol_list = dumpReq.getAttrsList();
                            m = jchannel.channel.dumpStats(dumpReq.getProtocolName(), protocol_list);
                        } else {
                            System.out.println("[gRPC-Server] error dumpStats() with not correct data.");
                            return;
                        }
                        byte[] b = UtilsRJ.serializeObj(m);
                        DumpStatsRep dumpRep = DumpStatsRep.newBuilder().setSerializeMap(ByteString.copyFrom(b)).build();
                        Response rep = Response.newBuilder().setDumpStatsRep(dumpRep).build();
                        System.out.println(rep);
                        responseObserver.onNext(rep);

                    } else if (req.hasGetStatReq()){
                        GetStatsReq getStatsReq = req.getGetStatReq();
                        System.out.println("[gRPC-Server] Receive the getStats() request for the JChannel-server stats from JChannel-Client:"
                                + getStatsReq.getJchannelAddress());
                        GetStatsRep getStatsRep = GetStatsRep.newBuilder().setStats(jchannel.channel.getStats()).build();
                        Response rep = Response.newBuilder().setGetStatsRep(getStatsRep).build();
                        System.out.println(rep);
                        responseObserver.onNext(rep);
                    } else if (req.hasSetStatsReq()){
                        SetStatsReq setStatsReq = req.getSetStatsReq();
                        System.out.println("[gRPC-Server] Receive the setStats() request for the JChannel-server stats from JChannel-Client:"
                                + setStatsReq.getJchannelAddress());
                        jchannel.channel.setStats(setStatsReq.getStats());
                        SetStatsRep setStatsRep = SetStatsRep.newBuilder().setStats(jchannel.channel.stats()).build();
                        Response rep = Response.newBuilder().setSetStatsRep(setStatsRep).build();
                        System.out.println(rep);
                        responseObserver.onNext(rep);
                    } else if (req.hasGetDiscardOwnMsgReq()){
                        GetDiscardOwnMsgReq getDiscardOwnMsgReq = req.getGetDiscardOwnMsgReq();
                        System.out.println("[gRPC-Server] Receive the getDiscardOwnMessage() request for the JChannel-server DiscardOwnMessage from JChannel-Client:"
                                + getDiscardOwnMsgReq.getJchannelAddress());
                        GetDiscardOwnMsgRep discardRep = GetDiscardOwnMsgRep.newBuilder()
                                .setDiscard(jchannel.channel.getDiscardOwnMessages()).build();
                        Response rep = Response.newBuilder().setGetDiscardOwnRep(discardRep).build();
                        System.out.println(rep);
                        responseObserver.onNext(rep);
                    } else if (req.hasSetDiscardOwnMsgReq()){
                        SetDiscardOwnMsgReq setDiscardOwnMsgReq = req.getSetDiscardOwnMsgReq();
                        System.out.println("[gRPC-Server] Receive the setDiscardOwnMessage() request for the JChannel-server DiscardOwnMessage from JChannel-Client:"
                                + setDiscardOwnMsgReq.getJchannalAddress());
                        jchannel.channel.setDiscardOwnMessages(setDiscardOwnMsgReq.getDiscard());
                        SetDiscardOwnMsgRep discardRep = SetDiscardOwnMsgRep.newBuilder()
                                .setDiscard(jchannel.channel.getDiscardOwnMessages()).build();
                        Response rep = Response.newBuilder().setSetDiscardOwnRep(discardRep).build();
                        System.out.println(rep);
                        responseObserver.onNext(rep);
                    } else if (req.hasGetStateReq()){
                        GetStateReq getStateReq = req.getGetStateReq();
                        System.out.println("[gRPC-Server] Receive the getState() request for the JChannel-server state from JChannel-Client:"
                                + getStateReq.getJchannelAddress());

                        GetStateRep getStateRep = GetStateRep.newBuilder().setState(jchannel.channel.getState()).build();
                        Response rep = Response.newBuilder().setGetStateRep(getStateRep).build();
                        System.out.println(rep);
                        responseObserver.onNext(rep);
                    } else if (req.hasIsStateReq()){
                        IsStateReq isReq = req.getIsStateReq();
                        System.out.println("[gRPC-Server] Receive the isState() " + isReq.getType() + " request for the JChannel-server state from JChannel-Client:"
                                + isReq.getJchannelAddress());
                        String type = isReq.getType();
                        boolean result = false;
                        if (type.equals("isOpen")){
                            result = jchannel.channel.isOpen();
                        } else if (type.equals("isConnecting")){
                            result = jchannel.channel.isConnecting();
                        } else if (type.equals("isConnected")){
                            result = jchannel.channel.isConnected();
                        } else {
                            result = jchannel.channel.isClosed();
                        }
                        IsStateRep isRep = IsStateRep.newBuilder().setResult(result).build();
                        Response rep = Response.newBuilder().setIsStateRep(isRep).build();
                        System.out.println(rep);
                        responseObserver.onNext(rep);
                    } else if(req.hasGetPropertyReq()){
                        // getName() request
                        GetPropertyReq getPropertyReq = req.getGetPropertyReq();
                        System.out.println("[gRPC-Server] Receive the getProperties() request for the JChannel-server property from JChannel-Client:"
                                + getPropertyReq.getJchannelAddress());
                        GetPropertyRep getPropertyRep;
                        Response rep;
                        String property = jchannel.channel.getProperties();
                        if (property != null){
                            getPropertyRep = GetPropertyRep.newBuilder().setProperties(property).build();
                        } else{
                            getPropertyRep = GetPropertyRep.newBuilder().build();
                        }
                        rep = Response.newBuilder().setGetPropertyRep(getPropertyRep).build();
                        System.out.println(rep);
                        responseObserver.onNext(rep);

                    } else if(req.hasGetClusterNameReq()){
                        // getClusterName() request
                        GetClusterNameReq clusterReq = req.getGetClusterNameReq();
                        System.out.println("[gRPC-Server] Receive the getClusterName() request for the JChannel-server cluster name from JChannel-Client:"
                                + clusterReq.getJchannelAddress());
                        GetClusterNameRep getClusterNameRep;
                        if (jchannel.channel.getClusterName() != null){
                            getClusterNameRep = GetClusterNameRep.newBuilder().setClusterName(jchannel.channel.getClusterName()).build();
                        } else{
                            getClusterNameRep = GetClusterNameRep.newBuilder().build();
                        }
                        Response rep = Response.newBuilder().setGetClusterNameRep(getClusterNameRep).build();
                        System.out.println(rep);
                        responseObserver.onNext(rep);

                    } else if(req.hasPrintProtoReq()){
                        // getClusterName() request
                        PrintProtocolSpecReq printProtoReq = req.getPrintProtoReq();
                        System.out.println("[gRPC-Server] Receive the printProtocolSpec() request for the JChannel-server ProtocolSpec from JChannel-Client:"
                                + printProtoReq.getJchannelAddress());
                        PrintProtocolSpecRep printProtoRep = PrintProtocolSpecRep.newBuilder()
                                .setProtocolStackSpec(jchannel.channel.printProtocolSpec(printProtoReq.getIncludeProps())).build();
                        Response rep = Response.newBuilder().setPrintProtoRep(printProtoRep).build();
                        System.out.println(rep);
                        responseObserver.onNext(rep);

                    } else if (req.hasMessageReqRep()){
                        /**
                         * MessageReq, receive the send() request
                         */
                        MessageReqRep msgReq = req.getMessageReqRep();
                        Message msgObj = UtilsRJ.convertMessage(msgReq);
                        System.out.println("[gRPC-Server] Receive a Message from JChannel-client: " + msgObj);
                        Address dest = msgObj.getDest();
                        // dest == all JChannel-Servers and common JChannels
                        if (dest == null) {
                            ReentrantLock lock = new ReentrantLock();
                            lock.lock();
                            try{
                                System.out.println("[gRPC-Server] The Message will be broadcast.");
                                forwadMsgToServer(msgReq);
                                forwardMsgToJChannel(msgObj);
                            } finally {
                                lock.unlock();
                            }

                            // dest == this JChannel-Server
                        } else if (dest.equals(jchannel.channel.getAddress())){
                            System.out.println("[gRPC-Server] The Message will be broadcast to all clients connecting to this server.");
                            // change
                            try {
                                jchannel.channel.send(jchannel.channel.getAddress(), msgReq);
                            } catch (Exception e){
                                e.printStackTrace();
                            }
                            // dest == a JChannel-Client, which is connected to this JChannel-Server
                        } else if (clients.containsKey(dest)){
                            System.out.println("[gRPC-Server] The Message will be unicast to a client connecting to this server.");
                            unicast(msgReq);
                            // dest == a JChannel-Server having a gRPC address
                        } else if (jchannel.nodesMap.containsKey(dest)){
                            System.out.println("[gRPC-Server] The Message will be unicast to a JChannel-Server.");
                            try {
                                jchannel.channel.send(dest, msgReq);
                            } catch (Exception e){
                                e.printStackTrace();
                            }
                            // dest is a common JChannel, without having a gRPC address
                        } else if (!(jchannel.nodesMap.containsKey(dest)) && jchannel.channel.getView().containsMember(dest)){
                            System.out.println("[gRPC-Server] The Message will be unicast to a common JChannel.");
                            try {
                                msgObj.setSrc(jchannel.channel.getAddress());
                                jchannel.channel.send(msgObj);
                            } catch (Exception e){
                                e.printStackTrace();
                            }
                            // dest == a JChannel-Client, which is connected to other JChannel-Server
                        } else if (!(clients.containsKey(dest) && !(jchannel.channel.getView().containsMember(dest)))){
                            System.out.println("[gRPC-Server] The Message will be unicast to a client connecting to other server.");
                            forwadMsgToServer(req.getMessageReqRep());
                        } else if (py_clients.containsKey(dest)) {
                            if (msgObj.getType() == 3 && msgObj.getObject() instanceof String){
                                System.out.println("1");
                                unicastPy(msgObj.getSrc().toString(), msgObj.getObject().toString(), msgObj.getDest());
                            } else if (msgObj.getType() == 3 && !(msgObj.getObject() instanceof String)){
                                System.out.println("2");
                                unicastPy(msgObj.getSrc().toString(), msgObj.getObject().toString(), msgObj.getDest());
                            } else {
                                System.out.println("3");
                                unicastPy(msgObj.getSrc().toString(), msgObj.getObject().toString(), msgObj.getDest());
                            }
                            System.out.println("[gRPC-Server] The Message will be unicast to a Python Client.");
                        } else {
                            System.out.println("[gRPC-Server] Receive invalid message.");
                        }
                    }
                    /**
                     * The else if is used for the python client
                     */
                    else if (req.hasPyReqMsg()){
                        System.out.println(req);
                        ReqMsgForPyClient pyReq = req.getPyReqMsg();
                        if (pyReq.hasConReqPy()){
                            System.out.println("[gRPC-Server] Receive the connect() request from a Python client.");
                            System.out.println("[gRPC-Server] A Python JChannel-client connects to this JChannel-node (JChannel-server)");
                            // Description: connect(String cluster) request:
                            Address generated_address = null;
                            String generated_name = null;
                            if (!req.getPyReqMsg().getConReqPy().getReconnect()){
                               // the first connection of this py_client
                                generated_address = UUID.randomUUID();
                                generated_name = Util.generateLocalName();
                                System.out.println("[gRPC-Server] New connected Python JChannel-Client, the server generates an Address for it: " +
                                        generated_address + ", and logical name : " + generated_name);

                            } else{
                                // 1. if it is not first connect, it will contain a JChannelAddress property.
                                String logical_name = req.getPyReqMsg().getConReqPy().getLogicalName();
                                for (Address each : NameCache.getContents().keySet()){
                                    if (NameCache.getContents().get(each).equals(logical_name)){
                                        generated_address = each;
                                        generated_name = logical_name;

                                    }
                                }
                                System.out.println("[gRPC-Server] A reconnecting Python JChannel-Client, Address: " +
                                        generated_address + ", and logical name : " + generated_name);
                            }
                            // 2. Store the responseObserver of the client. Map<Address, streamObserver>
                            joinPy(responseObserver, generated_address, generated_name);
                            // 3. forward the connect request with generated Address and generated logical name to other nodes for storing
                            ByteArrayDataOutputStream out = new ByteArrayDataOutputStream();
                            UUID u = (UUID) generated_address;
                            try {
                                u.writeTo(out);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            ConnectReq conReq = ConnectReq.newBuilder().setLogicalName(generated_name)
                                    .setJchannAddressByte(ByteString.copyFrom(out.buffer())).build();
                            Request reqForward = Request.newBuilder().setConnectRequest(conReq).build();
                            forwardMsg(reqForward);
                            // 4. store the generated Address and logcial to this node's NameCache
                            NameCache.add(generated_address, generated_name);
                            System.out.println("done for python client connect");
                        } else if (pyReq.hasDisconReqPy()){
                            System.out.println("[gRPC-Server] Receive the disconnect() request from a Python client.");
                            System.out.println("[gRPC-Server] " + pyReq.getDisconReqPy().getLogicalName() + "(Python client) sends a disconnect request for grpc channel.");
                            byte[] b = null;
                            // remove responseObserver for the disconnect()
                            lock.lock();
                            try {
                                // 1. remove the client responseObserver
                                for (Address add : py_clients.keySet()) {
                                    if (add.toString().equals(pyReq.getDisconReqPy().getLogicalName())){
                                        b = Util.objectToByteBuffer(add);
                                        DisconnectRepPy disRepPy = DisconnectRepPy.newBuilder().setResult(true).build();
                                        RepMsgForPyClient repMsgPy = RepMsgForPyClient.newBuilder().setDisconRepPy(disRepPy).build();
                                        Response rep = Response.newBuilder().setPyRepMsg(repMsgPy).build();
                                        responseObserver.onNext(rep);
                                        py_clients.remove(add);
                                        // 2. remove the client from its cluster information
                                        jchannel.disconnectCluster("ClientCluster", add);
                                    }
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally {
                                lock.unlock();
                            }
                            // also notify other nodes to delete it
                            DisconnectReq disReq = DisconnectReq.newBuilder().setJchannelAddress(ByteString.copyFrom(b)).build();
                            Request generated_msg = Request.newBuilder().setDisconnectRequest(disReq).build();
                            forwardMsg(generated_msg);
                            onCompleted();
                        } else if (pyReq.hasGetStateReqPy()){
                            StateRepPy statePy;
                            lock.lock();
                            try{
                                statePy = StateRepPy.newBuilder().setSize(jchannel.state.size()).addAllLine(jchannel.state).build();
                            } finally {
                                lock.unlock();
                            }
                            RepMsgForPyClient msgPy = RepMsgForPyClient.newBuilder().setStateRepPy(statePy).build();
                            Response rep = Response.newBuilder().setPyRepMsg(msgPy).build();
                            System.out.println("[gRPC-Server] Receive a getState() request from a python client, return a response:" + rep);
                            responseObserver.onNext(rep);
                        } else if (pyReq.hasMsgReqPy()){
                            MessageReqPy msgReq = req.getPyReqMsg().getMsgReqPy();
                            Message msgObj = new ObjectMessage(null, msgReq.getContentStr());
                            for (Address each : py_clients.keySet()){
                                if (each.toString().equals(msgReq.getSource())){
                                    msgObj.setSrc(each);
                                }
                                if (each.toString().equals(msgReq.getDest()) && !msgReq.getDest().equals("")){
                                    msgObj.setDest(each);
                                }
                            }
                            System.out.println("[gRPC-Server] Receive a Message from Python JChannel-client(after convert): " + msgObj);
                            Address dest = msgObj.getDest();
                            ByteArrayDataOutputStream out = new ByteArrayDataOutputStream();

                            byte[] b = null;
                            try {
                                msgObj.writeTo(out);
                                b = out.buffer();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            MessageReqRep msgReqRep = MessageReqRep.newBuilder().setMessageObj(ByteString.copyFrom(b)).setType(3).build();
                            // dest == all JChannel-Servers and common JChannels
                            if (dest == null) {
                                ReentrantLock lock = new ReentrantLock();
                                lock.lock();
                                try{
                                    System.out.println("[gRPC-Server] The Message will be broadcast.");
                                    forwadMsgToServer(msgReqRep);
                                    forwardMsgToJChannel(msgObj);
                                } finally {
                                    lock.unlock();
                                }

                                // dest == this JChannel-Server
                            } else if (dest.equals(jchannel.channel.getAddress())){
                                System.out.println("[gRPC-Server] The Message will be broadcast to all clients connecting to this server.");
                                // change
                                try {
                                    jchannel.channel.send(jchannel.channel.getAddress(), msgReq);
                                } catch (Exception e){
                                    e.printStackTrace();
                                }
                                // dest == a JChannel-Client, which is connected to this JChannel-Server
                            } else if (clients.containsKey(dest)){
                                System.out.println("[gRPC-Server] The Message will be unicast to a client connecting to this server.");
                                unicast(msgReqRep);
                                // dest == a JChannel-Server having a gRPC address
                            } else if (jchannel.nodesMap.containsKey(dest)){
                                System.out.println("[gRPC-Server] The Message will be unicast to a JChannel-Server.");
                                try {
                                    jchannel.channel.send(dest, msgReq);
                                } catch (Exception e){
                                    e.printStackTrace();
                                }
                                // dest is a common JChannel, without having a gRPC address
                            } else if (!(jchannel.nodesMap.containsKey(dest)) && jchannel.channel.getView().containsMember(dest)){
                                System.out.println("[gRPC-Server] The Message will be unicast to a common JChannel.");
                                try {
                                    msgObj.setSrc(jchannel.channel.getAddress());
                                    jchannel.channel.send(msgObj);
                                } catch (Exception e){
                                    e.printStackTrace();
                                }
                                // dest == a JChannel-Client, which is connected to other JChannel-Server
                            } else if (!(clients.containsKey(dest) && !(jchannel.channel.getView().containsMember(dest)))){
                                System.out.println("[gRPC-Server] The Message will be unicast to a client connecting to other server.");
                                forwadMsgToServer(req.getMessageReqRep());
                            } else if (py_clients.containsKey(dest)) {
                                unicastPy(msgReq.getSource(), msgReq.getContentStr(), dest);
                                System.out.println("[gRPC-Server] The Message will be unicast to a Python Client.");
                            } else {
                                System.out.println("[gRPC-Server] Receive invalid message.");
                            }
                        }else if (req.hasGetDiscardOwnMsgReq()){
                            GetDiscardOwnMsgReq getDiscardOwnMsgReq = req.getGetDiscardOwnMsgReq();
                            System.out.println("[gRPC-Server] Receive the getDiscardOwnMessage() request for the JChannel-server DiscardOwnMessage from JChannel-Client:"
                                    + getDiscardOwnMsgReq.getJchannelAddress());
                            GetDiscardOwnMsgRep discardRep = GetDiscardOwnMsgRep.newBuilder()
                                    .setDiscard(jchannel.channel.getDiscardOwnMessages()).build();
                            Response rep = Response.newBuilder().setGetDiscardOwnRep(discardRep).build();
                            System.out.println(rep);
                            responseObserver.onNext(rep);
                        } else if (req.hasSetDiscardOwnMsgReq()){
                            SetDiscardOwnMsgReq setDiscardOwnMsgReq = req.getSetDiscardOwnMsgReq();
                            System.out.println("[gRPC-Server] Receive the setDiscardOwnMessage() request for the JChannel-server DiscardOwnMessage from JChannel-Client:"
                                    + setDiscardOwnMsgReq.getJchannalAddress());
                            jchannel.channel.setDiscardOwnMessages(setDiscardOwnMsgReq.getDiscard());
                            SetDiscardOwnMsgRep discardRep = SetDiscardOwnMsgRep.newBuilder()
                                    .setDiscard(jchannel.channel.getDiscardOwnMessages()).build();
                            Response rep = Response.newBuilder().setSetDiscardOwnRep(discardRep).build();
                            System.out.println(rep);
                            responseObserver.onNext(rep);
                        }else{
                            System.out.println("Invalid type of message from python client.");
                        }
                    } else{
                        System.out.println("[gRPC-Server] Invalid message.");
                    }
                }
                // can add an automatic deleting the cancelled client.
                @Override
                public void onError(Throwable throwable) {
                    lock.lock();
                    try {
                        System.out.println("[gRPC] onError:" + throwable.getMessage() + " Remove it from the node.");

                        Address add = removeClient(responseObserver);
                        ByteArrayDataOutputStream out = new ByteArrayDataOutputStream();
                        UUID u = (UUID) add;
                        u.writeTo(out);
                        ExchangeMsg exchangeMsg = ExchangeMsg.newBuilder()
                                .setType("[DisconnectNotGrace]").setContentByt(ByteString.copyFrom(out.buffer())).build();
                        ChannelMsg channelMsg = ChannelMsg.newBuilder().setExchangeMsg(exchangeMsg).build();
                        // Message msg = new ObjectMessage(null, channelMsg);
                        // msg.setFlagIfAbsent(Message.TransientFlag.DONT_LOOPBACK);
                        forwardMsg(channelMsg);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }

                @Override
                public void onCompleted() {
                    responseObserver.onCompleted();
                }
            };
        }
        protected void joinPy(StreamObserver<Response> responseObserver, Address generated_address, String generated_name){
            lock.lock();
            try{
                // using the generated Address by this JChannel-server as key, responseObserver as value
                py_clients.put(generated_address, responseObserver);
                System.out.println("[gRPC-Server] Store the new Python client.");
                ConnectRepPy conRep = ConnectRepPy.newBuilder().setLogicalName(generated_name).setResult(true).build();
                RepMsgForPyClient pyRep = RepMsgForPyClient.newBuilder().setConRepPy(conRep).build();
                Response rep = Response.newBuilder()
                        .setPyRepMsg(pyRep)
                        .build();
                // return (send) the ConnectResponse to that JChannel-client
                responseObserver.onNext(rep);
                System.out.println("[gRPC-Server] Return a connect() response to the Python new client.");
                jchannel.connectCluster("ClientCluster", generated_address, generated_name);
                // return response for updating the available servers
                UpdateAddPy updatePy = UpdateAddPy.newBuilder().setAddresses(this.jchannel.generateAddMsg()).build();
                RepMsgForPyClient pyMsgAdd = RepMsgForPyClient.newBuilder().setUpdateAddPy(updatePy).build();
                Response rep2 = Response.newBuilder()
                        .setPyRepMsg(pyMsgAdd)
                        .build();
                responseObserver.onNext(rep2);
                System.out.println("[gRPC-Server] Return a message for the current available gRPC addresses of servers.");
                // make a server view
                View v = this.jchannel.channel.getView();
                int num = (int) v.getViewId().getId();
                Address[] members = v.getMembersRaw();
                LinkedList<String> membersList = new LinkedList<String>();
                for (Address each: members){
                    membersList.add(each.toString());
                }
                ServerViewPy serverViewPy = ServerViewPy.newBuilder().setCoordinator(v.getCoord().toString())
                        .setNum(num).addAllMembers(membersList).setSize(membersList.size()).build();
                RepMsgForPyClient pyRep2 = RepMsgForPyClient.newBuilder().setServerViewPy(serverViewPy).build();
                Response rep_pyServerView = Response.newBuilder().setPyRepMsg(pyRep2).build();
                unicastRep(rep_pyServerView, generated_address);
                System.out.println("[gRPC-Server] Return a message for the JChannel-Server view.");
                System.out.println(rep_pyServerView);
            }
            // 3. run finally, confirm the lock will be unlock.
            finally {
                // remember unlock
                lock.unlock();
            }
        }

        protected UpdateNameCacheRep generateNameCacheMsg(){
            UpdateNameCacheRep nameCacheRep = null;
            ReentrantLock lock = new ReentrantLock();
            lock.lock();
            try{
                UpdateNameCacheRep.Builder builder = UpdateNameCacheRep.newBuilder();
                Map<Address, String> m = NameCache.getContents();
                for (Address oneAddress: m.keySet()) {
                    ByteArrayDataOutputStream vOutStream = new ByteArrayDataOutputStream();
                    if (oneAddress instanceof UUID){
                        UUID u = (UUID) oneAddress;
                        u.writeTo(vOutStream);
                    } else{
                        throw new IllegalArgumentException("It does not belong to UUID Address.");
                    }
                    byte[] v_byte = vOutStream.buffer();
                    builder.addAddress(ByteString.copyFrom(v_byte));
                    builder.addLogicalName(oneAddress.toString());
                }
                nameCacheRep = builder.build();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            return nameCacheRep;
        }

        protected Address removeClient(StreamObserver responseObserver){

            for (Address add:clients.keySet()) {
                if (clients.get(add) == responseObserver){
                    clients.remove(add);
                    System.out.println("[gRPC] Found the error client and its observer, remove it from clients Map.");
                    jchannel.disconnectClusterNoGraceful(add);
                    return add;
                }
            }
            for (Address pyClientAdd: py_clients.keySet()){
                if (py_clients.get(pyClientAdd) == responseObserver){
                    py_clients.remove(pyClientAdd);
                    System.out.println("[gRPC] Found the error client and its observer, remove it from clients Map.");
                    jchannel.disconnectClusterNoGraceful(pyClientAdd);
                    return pyClientAdd;
                }
            }
            return null;
        }

        /* The unary rpc, the response of ask rpc call method for the try connection from clients.
         */
        public void ask(ReqAsk req, StreamObserver<RepAsk> responseObserver){
            System.out.println("[gRPC-Server] Receive an ask request for reconnection.");
            RepAsk askMsg = RepAsk.newBuilder().setSurvival(true).build();
            responseObserver.onNext(askMsg);
            responseObserver.onCompleted();
        }

        protected void join(StreamObserver<Response> responseObserver, Address generated_address, String generated_name){
            // 1. get lock
            lock.lock();
            // 2. critical section,
            try{
                /* add a new client to Map<uuid, responseObserver>
                 return a connect response
                 */
                // using the generated Address by this JChannel-server as key, responseObserver as value
                clients.put(generated_address, responseObserver);
                System.out.println("[gRPC-Server] Store the new client.");
                // convert generated Address to byte and generate ConnectResponse
                ByteArrayDataOutputStream outputStream = new ByteArrayDataOutputStream();
                byte[] address_byte = null;
                try {
                    UUID u = (UUID) generated_address;
                    u.writeTo(outputStream);
                    address_byte = outputStream.buffer();
                } catch (Exception e){
                    e.printStackTrace();
                }
                ConnectRep joinResponse = ConnectRep.newBuilder()
                        .setResult(true)
                        .setAddress(ByteString.copyFrom(address_byte))
                        .setLogicalName(generated_name)
                        .build();
                Response rep = Response.newBuilder()
                        .setConnectResponse(joinResponse)
                        .build();
                // return (send) the ConnectResponse to that JChannel-client
                responseObserver.onNext(rep);
                System.out.println("[gRPC-Server] Return a connect() response to the new client.");
                jchannel.connectCluster("ClientCluster", generated_address, generated_name);

                // return response for updating the available servers
                UpdateRep updateRep = UpdateRep.newBuilder()
                        .setAddresses(this.jchannel.generateAddMsg())
                        .build();
                Response rep2 = Response.newBuilder()
                        .setUpdateResponse(updateRep)
                        .build();
                responseObserver.onNext(rep2);
                System.out.println("[gRPC-Server] Return a message for the current available gRPC addresses of servers.");
                View view = jchannel.channel.getView();
                ByteArrayDataOutputStream vOutStream = new ByteArrayDataOutputStream();
                try {
                    view.writeTo(vOutStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                byte[] v_byte = vOutStream.buffer();
                ViewRep_server view_rep = ViewRep_server.newBuilder().setSender(jchannel.channel.address().toString()).setViewByte(ByteString.copyFrom(v_byte)).build();
                Response rep_broView = Response.newBuilder().setViewRepServer(view_rep).build();
                unicastRep(rep_broView, generated_address);
                System.out.println("[gRPC-Server] Return a message for the JChannel-Server view.");
                System.out.println(rep_broView);
            }
            // 3. run finally, confirm the lock will be unlock.
            finally {
                // remember unlock
                lock.unlock();
            }
        }
        protected void broadcastResponsePy(String source, String content){
            MessageRepPy msgRep = MessageRepPy.newBuilder().setSource(source).setContentStr(content).build();
            RepMsgForPyClient pyMsg = RepMsgForPyClient.newBuilder().setMsgRepPy(msgRep).build();
            Response rep = Response.newBuilder().setPyRepMsg(pyMsg).build();
            System.out.println("[gRPC-Server] Broadcast this message to all Python clients connecting to this server.");
            if (py_clients.size() != 0){
                for (Address u : py_clients.keySet()){
                    py_clients.get(u).onNext(rep);
                    System.out.println(rep);
                }

            } else {
                System.out.println("[gRPC-Server] The size of connecting Python clients is 0.");
            }
        }

        // Broadcast the messages for updating addresses of servers
        protected void broadcastResponse(Response rep){
            System.out.println("[gRPC-Server] Broadcast this message to all clients connecting to this server.");
            if (clients.size() != 0){
                for (Address u : clients.keySet()){
                    clients.get(u).onNext(rep);
                }
                System.out.println(rep);
            } else {
                System.out.println("[gRPC-Server] The size of connecting clients is 0.");
            }
        }
        protected void unicastRep(Response response, Address dest){
            lock.lock();
            try{
                for (Address add : clients.keySet()){
                    if (add.equals(dest)){
                        clients.get(add).onNext(response);
                        System.out.println("[gRPC-Server] Send message to a JChannel-Client, " + dest);
                    }
                }
                for (Address pyAdd : py_clients.keySet()){
                    if (pyAdd.equals(dest)){
                        py_clients.get(pyAdd).onNext(response);
                        System.out.println("[gRPC-Server] Send message to a Python JChannel-Client, " + dest);
                    }
                }
                System.out.println("[gRPC-Server] One unicast for message successfully.");

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
        protected void unicastPy(String source, String content, Address dest){
            if (!py_clients.containsKey(dest)){
                return;
            }
            lock.lock();
            try{
                MessageRepPy msg = MessageRepPy.newBuilder().setSource(source).setContentStr(content).build();
                RepMsgForPyClient pyMsg = RepMsgForPyClient.newBuilder().setMsgRepPy(msg).build();
                Response rep = Response.newBuilder().setPyRepMsg(pyMsg).build();
                for (Address add : py_clients.keySet()){
                    if (add.equals(dest)){
                        py_clients.get(add).onNext(rep);
                        System.out.println("[gRPC-Server] Send message to a Python JChannel-Client, " + dest);
                    }
                }
                System.out.println("[gRPC-Server] One unicast to Python client for message successfully.");

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
        protected void unicast(MessageReqRep req){
            lock.lock();
            try{
                Response rep = Response.newBuilder().setMessageReqRep(req).build();
                Message msgObj = UtilsRJ.convertMessage(rep.getMessageReqRep());
                for (Address add : clients.keySet()){
                    if (add.equals(msgObj.getDest())){
                        clients.get(add).onNext(rep);
                        System.out.println("[gRPC-Server] Send message to a JChannel-Client, " + msgObj.getDest());
                    }
                }
                System.out.println("[gRPC-Server] One unicast for message successfully.");

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
        public void broadcastViewPy(View v){
            LinkedList<String> list = new LinkedList();
            for (int i = 0; i < v.getMembersRaw().length; i++) {
                list.add(v.getMembersRaw()[i].toString());
            }
            ClientViewPy viewMsg = ClientViewPy.newBuilder().setCoordinator(v.getCoord().toString())
                    .setNum((int) v.getViewId().getId()).setSize(v.getMembers().size()).addAllMembers(list).build();
            RepMsgForPyClient pyMsg = RepMsgForPyClient.newBuilder().setClientViewPy(viewMsg).build();
            Response rep = Response.newBuilder().setPyRepMsg(pyMsg).build();
            lock.lock();
            try{
                for (Address pyAdd:py_clients.keySet()){
                    py_clients.get(pyAdd).onNext(rep);
                    System.out.println("[gRPC-Server] Send view to a Python JChannel-Client, " + pyAdd);
                    System.out.println(rep);
                }
            }finally {
                lock.unlock();
            }
        }

        public void broadcastView(ViewRep videRep){
            lock.lock();
            try{
                Response rep = Response.newBuilder()
                        .setViewResponse(videRep)
                        .build();
                //ClusterMap clusterObj = (ClusterMap) jchannel.serviceMap.get(cluster);
                for (Address add : clients.keySet()){
                    clients.get(add).onNext(rep);
                    System.out.println("[gRPC-Server] Send view to a JChannel-Client, " + add);
                }
                System.out.println("[gRPC-Server] One broadcast for view successfully.");
                System.out.println(rep);
            } finally {
                lock.unlock();
            }
        }

        // send message to all common JChannels
        protected void forwardMsgToJChannel(Message msg) {
            jchannel.checkNodes();
            Address src = this.jchannel.channel.getAddress();
            for (Address address : jchannel.channel.getView().getMembers()) {
                if (!jchannel.nodesMap.containsKey(address)) {
                    try {
                        Message new_msg = null;
                        if (msg.getType() == 4){
                            //System.out.println("ForwardMsgToJChannel old:" + msg + ",  " + msg.getFlags() + ", " + msg.getHeaders());
                            Message longMsg = (LongMessage) msg;
                            new_msg = new LongMessage(address, longMsg.getObject());
                            new_msg.setSrc(src);
                            //System.out.println("ForwardMsgToJChannel new:" + new_msg + ",  " + new_msg.getFlags() + ", " + new_msg.getHeaders());
                        } else {
                            new_msg = msg.copy(true, false);
                            new_msg.setSrc(src).setDest(address);
                        }
                        // Message new_msg = UtilsRJ.cloneMessage(msg, src, address);
                        jchannel.channel.send(new_msg);
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("[JChannel-Server] Send a message to all commom JChannels.");
        }

        protected void forwardMsg(ChannelMsg chmsg){
            jchannel.checkNodes();
            // send messages exclude itself.
            for (Address address: jchannel.nodesMap.keySet()) {
                if(!address.equals(jchannel.channel.getAddress())) {
                    try {

                        Message msg = new ObjectMessage(address, chmsg);
                        msg.setDest(address);
                        this.jchannel.channel.send(msg);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            System.out.println("forwardMsg (ChannelMsg) to other JChannel-Servers: " + chmsg);
        }

        protected void forwadMsgToServer(MessageReqRep msgReq){
            //System.out.println("Test: the current NodeMap members:" + jchannel.nodesMap);
            jchannel.checkNodes();
            for (Address address: jchannel.nodesMap.keySet()) {
                try {
                    Message msg = new ObjectMessage(address, msgReq);
                    System.out.println("[JChannel-Server] Forward a message for Request(MessageReqRep) to a JChannel-Server, "
                            + address + ", the Message: " + msg);
                    this.jchannel.channel.send(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //System.out.println("[JChannel-Server] Forward a message for Request(MessageReqRep) to other JChannel-Servers: " + msgReq);
        }

        /**
         * forwardMsg() forwards the Request object to other JChannel-Servers, using the ObjectMessage in send()
         * @param req
         */
        protected void forwardMsg(Request req){
            jchannel.checkNodes();
            // forward Request for client information to other JChannel-Servers excluding itself, e.g. connectRequest, disconnectRequest.
            if (!req.hasMessageReqRep()){
                for (Address address: jchannel.nodesMap.keySet()) {
                    if(!address.equals(jchannel.channel.getAddress())){
                        try {
                            Message msg = new ObjectMessage(address, req);
                            System.out.println("[JChannel-Server] Forward a message for Request(not MessageReqRep) to a JChannel-Server, dest:" + msg);
                            this.jchannel.channel.send(msg);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            System.out.println("[JChannel-Server] Forward a message for Request(not MessageReqRep) to other JChannel-Servers: " + req);
        }
    }

    // Get ip address of client when receive the join request.
    private class ClientAddInterceptor implements ServerInterceptor {
        @Override
        public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
            String ip = call.getAttributes().get(Grpc.TRANSPORT_ATTR_REMOTE_ADDR).toString();
            System.out.println("Found request from IP address: " + ip);
            ips.put(ips.size(), ip);
            return next.startCall(call, headers);
        }
    }

    public static void main(String[] args) throws Exception {
        // Port, NodeName, ClusterName
        final NodeServer server = new NodeServer(Integer.parseInt(args[0]), args[1]);
        System.out.printf("Inf: %s %s \n", args[0], args[1]);
        // start gRPC service
        server.start();
        server.blockUntilShutdown();
    }
}