package cn.yingming.grpc1;

import com.google.protobuf.ByteString;
import io.grpc.*;
import io.grpc.jchannelRpc.*;
import io.grpc.stub.StreamObserver;
import org.jgroups.Address;
import org.jgroups.Message;
import org.jgroups.ObjectMessage;
import org.jgroups.View;
import org.jgroups.util.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

// The node is given with a gRPC server and a JChannel.
public class NodeServer {
    //  Port and server object of grpc server
    private int port;
    private Server server;
    //  Node name, cluster name, JChannel of node
    String nodeName;
    String jClusterName;
    NodeJChannel jchannel;
    JChannelsServiceImpl gRPCservice;
    // <no, ip>, it stores all ip address for clients, who are connecting to this server. not useful.
    private ConcurrentHashMap<Integer, String> ips;
    public NodeServer(int port, String nodeName, String jClusterName) throws Exception {
        // port, name, and cluster name of this node
        this.port = port;
        this.nodeName = nodeName;
        this.jClusterName = jClusterName;
        // not useful, store clients address.
        this.ips = new ConcurrentHashMap<>();
        // create JChannel given node name and cluster name
        this.jchannel = new NodeJChannel(nodeName, jClusterName, "127.0.0.1:" + port);
        // create grpc server, and its service is given the jchannel for calling send() method on jchannel.
        this.gRPCservice = new JChannelsServiceImpl(this.jchannel);
        this.server = ServerBuilder.forPort(port)
                .addService(this.gRPCservice)  // service for bidirectional streaming
                .intercept(new ClientAddInterceptor())
                .build();
    }

    // Start grpc
    private void start() throws Exception {
        // Give the entry of gRPC for calling broadcast().
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
        private final ConcurrentHashMap<Address, StreamObserver<Response>> clients = new ConcurrentHashMap<>();
        protected final ReentrantLock lock = new ReentrantLock();
        NodeJChannel jchannel;

        // Constructor with JChannel for calling send() method.
        private JChannelsServiceImpl(NodeJChannel jchannel) throws Exception {
            this.jchannel = jchannel;
        }
        // service 1, bi-directional streaming rpc
        public StreamObserver<Request> connect(StreamObserver<Response> responseObserver){
            return new StreamObserver<Request>() {
                @Override
                public void onNext(Request req) {
                    /* Condition1
                       Receive the connect() request.
                     */
                    if (req.hasConnectRequest()){
                        System.out.println("[gRPC] Receive the connect() request from a client.");
                        System.out.println("A new JChannel-client connects to this JChannel-node (JChannel-server), " +
                                req.getConnectRequest().getCluster());
                        // Description: connect(String cluster) request:
                        Address generated_address = null;
                        String generated_name = null;
                        if (!req.getConnectRequest().getReconnect()){
                            // 1.The server generates the Address and the logical name
                            // for the requester with the first connect to JChannel-node.
                            generated_address = UUID.randomUUID();
                            generated_name = Util.generateLocalName();
                            System.out.println("New connected JChannel-Client, the server generates an Address for it: " +
                                    generated_address + ", and logical name : " + generated_name);
                        } else{
                            // 1. if it is not first connect, it will contain a JChannelAddress property.
                            generated_address = UUID.fromString(req.getConnectRequest().getJchannelAddress());
                            generated_name = req.getConnectRequest().getLogicalName();
                        }
                        // 2. Store the responseObserver of the client. Map<Address, streamObserver>
                        join(req.getConnectRequest(), responseObserver, generated_address, generated_name);
                        // 3. forward the connect request with generated Address and generated logical name to other nodes for storing
                        ConnectReq conReqWithAddress = ConnectReq.newBuilder()
                                .setCluster(req.getConnectRequest().getCluster())
                                .setTimestamp(req.getConnectRequest().getTimestamp())
                                .setJchannelAddress(generated_address.toString())
                                .setLogicalName(generated_name)
                                .build();
                        Request reqForward = Request.newBuilder().setConnectRequest(conReqWithAddress).build();
                        forwardMsg(reqForward);
                        // 4. store the generated Address and logcial to this node's NameCache
                        NameCache.add(generated_address, generated_name);
                        // 5. update the current NameCache to the client

                    /*  Condition2
                        Receive the disconnect() request.
                     */
                    } else if (req.hasDisconnectRequest()){
                        System.out.println("[gRPC] Receive the disconnect() request from a client.");
                        System.out.println(req.getDisconnectRequest().getJchannelAddress() + " quits the cluster, " +
                                req.getDisconnectRequest().getCluster());
                        // remove responseObserver for the disconnect()
                        lock.lock();
                        try {
                            // 1. remove the client responseObserver
                            for (Address add : clients.keySet()) {
                                if (add.equals(UUID.fromString(req.getDisconnectRequest().getJchannelAddress()))){
                                    clients.remove(add);
                                }
                            }
                            // 2. remove the client from its cluster information
                            jchannel.disconnectCluster(req.getDisconnectRequest().getCluster(),
                                    UUID.fromString(req.getDisconnectRequest().getJchannelAddress()));
                        } finally {
                            lock.unlock();
                        }
                        // also notify other nodes to delete it
                        forwardMsg(req);
                        onCompleted();
                    } else if (req.hasStateReq()){
                        StateReq stateReq = req.getStateReq();
                        System.out.println("[gRPC] Receive the getState() request for message history from a client.");
                        System.out.println(stateReq.getJchannelAddress() + "(" +
                                stateReq.getSource() + ") calls getState() for cluster, " +
                                stateReq.getCluster());
                        lock.lock();
                        try {
                            // generate the history
                            ClusterMap cm = (ClusterMap) jchannel.serviceMap.get(stateReq.getCluster());
                            StateRep stateRep = cm.generateState();
                            Response rep = Response.newBuilder()
                                    .setStateRep(stateRep)
                                    .build();
                            List l = rep.getStateRep().getOneOfHistoryList();
                            for (int i = 0; i < rep.getStateRep().getOneOfHistoryList().size(); i++) {
                                System.out.println(l.get(i).getClass());
                                MessageRep mp = (MessageRep) l.get(i);
                                System.out.println(mp.getJchannelAddress() + ":" + mp.getContent());
                            }
                            // send to this client
                            for (Address uuid : clients.keySet()) {
                                if (uuid.equals(req.getStateReq().getSource())){
                                    clients.get(uuid).onNext(rep);
                                    // System.out.println("rep: " + rep);
                                }
                            }
                        } finally {
                            lock.unlock();
                        }

                    } else if (req.hasStateMsg2()){
                        StateMsg_withTarget_2 msg = req.getStateMsg2();
                        System.out.println("[gRPC] Receive the getState(target) result for message history from a client.");
                        System.out.println(msg.getJchannelAddress() + "(" +
                                msg.getSource() + ") returns getState() result for cluster, " +
                                msg.getCluster() + " for remote jchannel " + msg.getTarget());
                        lock.lock();
                        try{
                            // forward msg to other JChannels
                            forwardMsg(req);
                            // send msg to its gRPC clients
                            unicast_stateMsg2(msg);
                        }finally {
                            lock.unlock();
                        }
                    }else if (req.hasStateMsg1()){
                        StateMsg_withTarget_1 msg = req.getStateMsg1();
                        System.out.println("[gRPC] Receive the getState(target) request for message history from a client.");
                        System.out.println(msg.getJchannelAddress() + "(" +
                                msg.getSource() + ") calls getState() for cluster, " +
                                msg.getCluster() + " with target " + msg.getTarget());
                        lock.lock();
                        try{
                            // forward msg to other JChannels
                            forwardMsg(req);
                            // send msg to its gRPC clients
                            unicast_stateMsg1(msg);
                        }finally {
                            lock.unlock();
                        }
                    } else if (req.hasGetAddressReq()){
                        /*
                        Receive the getAddress() request for getting the real JChannel-Server's Address
                        If the real JChannel of node does not work, return a response with isWork "false".
                        */
                        GetAddressReq getAddressReq = req.getGetAddressReq();
                        System.out.println("[grpc] Receive the getAddress() request for the JChannel-server Address from JChannel-Client:"
                                + getAddressReq.getJchannelAddress() + "(" + getAddressReq.getSource() + ")");
                        GetAddressRep getAddressRep;
                        if (jchannel.channel.getAddress() != null){
                            // convert the Address of real JChannel to bytes and put in the message
                            ByteArrayDataOutputStream outputStream = new ByteArrayDataOutputStream();
                            try {
                                jchannel.channel.getAddress().writeTo(outputStream);
                                // Util.createRandomAddress().writeTo(outputStream);
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
                        System.out.println("[grpc] Receive the getName() request for the JChannel-server name from JChannel-Client:"
                                + getNameReq.getJchannelAddress() + "(" + getNameReq.getSource() + ")");
                        GetNameRep getNameRep;
                        if (jchannel.channel.getName() != null){
                            getNameRep = GetNameRep.newBuilder().setName(jchannel.channel.getName()).build();
                        } else{
                            getNameRep = GetNameRep.newBuilder().build();
                        }
                        Response rep = Response.newBuilder().setGetNameRep(getNameRep).build();
                        System.out.println(rep);
                        responseObserver.onNext(rep);

                    } else if(req.hasGetClusterNameReq()){
                        // getClusterName() request
                        GetClusterNameReq clusterReq = req.getGetClusterNameReq();
                        System.out.println("[grpc] Receive the getClusterName() request for the JChannel-server cluster from JChannel-Client:"
                                + clusterReq.getJchannelAddress() + "(" + clusterReq.getSource() + ")");
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
                        System.out.println("[grpc] Receive the printProtocolSpec() request for the JChannel-server from JChannel-Client:"
                                + printProtoReq.getJchannelAddress() + "(" + printProtoReq.getSource() + ")");
                        PrintProtocolSpecRep printProtoRep = PrintProtocolSpecRep.newBuilder()
                                .setProtocolStackSpec(jchannel.channel.printProtocolSpec(printProtoReq.getIncludeProps())).build();
                        Response rep = Response.newBuilder().setPrintProtoRep(printProtoRep).build();
                        System.out.println(rep);
                        responseObserver.onNext(rep);

                    } else{
                        /* Condition3
                           Receive the common message, send() request.
                           Two types: broadcast ot unicast
                         */
                        MessageReq msgReq = req.getMessageRequest();
                        // send() messages for broadcast and unicast in the cluster for clients
                        System.out.println("[gRPC] " + msgReq.getJchannelAddress() + " sends message: "
                                + msgReq.getContent() + "/" + msgReq.getContentByte().toString()
                                + " at " + msgReq.getTimestamp());
                        // Type1, broadcast
                        if (msgReq.getDestination() == null || msgReq.getDestination().equals("")){
                            System.out.println("[gRPC] Broadcast in the cluster " + msgReq.getCluster());
                            lock.lock();
                            try{
                                // add to history
                                ClusterMap cm = (ClusterMap) jchannel.serviceMap.get(msgReq.getCluster());
                                cm.addHistory(msgReq);
                                // forward msg to other nodes
                                forwardMsg(req);
                                // send msg to its gRPC clients
                                broadcast(msgReq);

                            }finally {
                                lock.unlock();
                            }
                        // Type2, unicast
                        } else{
                            System.out.println("[gRPC] Unicast in the cluster " + msgReq.getCluster() + " to " + msgReq.getDestination());
                            lock.lock();
                            try{
                                // forward msg to other JChannels
                                forwardMsg(req);
                                // send msg to its gRPC clients
                                unicast(msgReq);
                            }finally {
                                lock.unlock();
                            }
                        }
                    }
                }
                // can add an automatic deleting the cancelled client.
                @Override
                public void onError(Throwable throwable) {

                    System.out.println("[gRPC] onError:" + throwable.getMessage() + " Remove it from the node.");
                    System.out.println(responseObserver);
                    Address add = removeClient(responseObserver);
                    String line = "[DisconnectNotGrace] " + add;
                    Message msg = new ObjectMessage(null, line);
                    msg.setFlagIfAbsent(Message.TransientFlag.DONT_LOOPBACK);
                    try {
                        jchannel.channel.send(msg);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onCompleted() {
                    responseObserver.onCompleted();
                }
            };
        }

        protected Address removeClient(StreamObserver responseObserver){

            this.lock.lock();
            try{
                for (Address add:clients.keySet()) {
                    if (clients.get(add) == responseObserver){
                        clients.remove(add);
                        System.out.println("[gRPC] Found the error client, remove it from clients Map.");
                        jchannel.disconnectClusterNoGraceful(add);
                        return add;
                    }

                }
            } finally {
                this.lock.unlock();
            }
            return null;
        }

        /* The unary rpc, the response of ask rpc call method for the try connection from clients.
         */
        public void ask(ReqAsk req, StreamObserver<RepAsk> responseObserver){
            System.out.println("[gRPC] Receive an ask request for reconnection from " + req.getSource());
            RepAsk askMsg = RepAsk.newBuilder().setSurvival(true).build();
            responseObserver.onNext(askMsg);
            responseObserver.onCompleted();
        }

        protected void join(ConnectReq req, StreamObserver<Response> responseObserver, Address generated_address, String generated_name){
            // 1. get lock
            lock.lock();
            // 2. critical section,
            try{
                /* add a new client to Map<uuid, responseObserver>
                 return a connect response
                 */
                // using the generated Address by this JChannel-server as key, responseObserver as value
                clients.put(generated_address, responseObserver);
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
                // String cluster, Address JChannel_address, String generated_name
                // <cluster, <uuid, JChanner_address>>
                jchannel.connectCluster(req.getCluster(), generated_address, generated_name);

                // return response for updating the available servers
                UpdateRep updateRep = UpdateRep.newBuilder()
                        .setAddresses(this.jchannel.generateAddMsg())
                        .build();
                Response rep2 = Response.newBuilder()
                        .setUpdateResponse(updateRep)
                        .build();
                responseObserver.onNext(rep2);

                // Copy and send a JChannel-server View to the client
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
                broadcastResponse(rep_broView);
                System.out.println(rep_broView);
            }
            // 3. run finally, confirm the lock will be unlock.
            finally {
                // remember unlock
                lock.unlock();
            }
        }

        // Broadcast messages from its clients.
        public void broadcast(MessageReq req){
            lock.lock();
            try{
                Response rep;
                // Byte message
                if (req.getContent().equals("")){
                    MessageRep msgRep = MessageRep.newBuilder()
                            .setJchannelAddress(req.getJchannelAddress())
                            .setContentByte(req.getContentByte())
                            .build();
                    rep = Response.newBuilder()
                            .setMessageResponse(msgRep)
                            .build();
                } else{
                    // String  message
                    MessageRep msgRep = MessageRep.newBuilder()
                            .setJchannelAddress(req.getJchannelAddress())
                            .setContent(req.getContent())
                            .build();
                    rep = Response.newBuilder()
                            .setMessageResponse(msgRep)
                            .build();
                }
                ClusterMap clusterObj = (ClusterMap) jchannel.serviceMap.get(req.getCluster());
                for (Address add : clients.keySet()){
                    if (clusterObj.getMap().containsKey(add)){
                        clients.get(add).onNext(rep);
                        System.out.println("[gRPC] Send message to a JChannel-Client, " + clusterObj.getMap().get(add));
                    }
                }
                System.out.println("One broadcast for message successfully.");
                System.out.println(rep.toString());

            } finally {
                lock.unlock();
            }
        }

        protected void updateNameCache(StreamObserver observer){
            lock.lock();
            try{
                Map m = NameCache.getContents();
                UpdateNameCacheRep.Builder builder = UpdateNameCacheRep.newBuilder();


            } finally {
                lock.unlock();
            }

        }

        // Broadcast the messages for updating addresses of servers
        protected void broadcastResponse(Response message){
            lock.lock();
            try{
                // Iteration of StreamObserver for broadcast message.
                for (Address u : clients.keySet()){
                    clients.get(u).onNext(message);
                }
            } finally {
                lock.unlock();
            }
        }
        public void unicast_stateMsg2(StateMsg_withTarget_2 req){
            String msgCluster = req.getCluster();
            String msgDest = req.getTarget();
            lock.lock();
            try{
                ClusterMap clusterObj = (ClusterMap) jchannel.serviceMap.get(msgCluster);
                Response msg = Response.newBuilder().setStateMsg2(req).build();
                for (Address add : clients.keySet()){
                    if (clusterObj.getMap().containsKey(add)){
                        if (clusterObj.getMap().get(add).equals(msgDest)){
                            clients.get(add).onNext(msg);
                            System.out.println("[gRPC] Send a message for getState(target) result to a JChannel-Client, " + clusterObj.getMap().get(add));
                        }
                    }
                }
                System.out.println("One unicast for state msg2.");

            } finally {
                lock.unlock();
            }

        }
        public void unicast_stateMsg1(StateMsg_withTarget_1 req){
            String msgCluster = req.getCluster();
            String msgDest = req.getTarget();
            lock.lock();
            try{
                ClusterMap clusterObj = (ClusterMap) jchannel.serviceMap.get(msgCluster);
                Response msg = Response.newBuilder().setStateMsg1(req).build();
                for (Address add : clients.keySet()){
                    if (clusterObj.getMap().containsKey(add)){
                        if (clusterObj.getMap().get(add).equals(msgDest)){
                            clients.get(add).onNext(msg);
                            System.out.println("[gRPC] Send a message for getState(target) to a JChannel-Client, " + clusterObj.getMap().get(add));
                        }
                    }
                }
                System.out.println("One unicast for state msg1.");

            } finally {
                lock.unlock();
            }
        }

        public void unicast(MessageReq req){
            String msgCluster = req.getCluster();
            String msgDest = req.getDestination();
            lock.lock();
            try{
                Response rep;
                // byte conent message
                if (req.getContent().equals("")){
                    MessageRep msgRep = MessageRep.newBuilder()
                            .setJchannelAddress(req.getJchannelAddress())
                            .setContentByte(req.getContentByte())
                            .build();
                    rep = Response.newBuilder()
                            .setMessageResponse(msgRep)
                            .build();
                } else{
                    // String content message
                    MessageRep msgRep = MessageRep.newBuilder()
                            .setJchannelAddress(req.getJchannelAddress())
                            .setContent(req.getContent())
                            .build();
                    rep = Response.newBuilder()
                            .setMessageResponse(msgRep)
                            .build();
                }

                ClusterMap clusterObj = (ClusterMap) jchannel.serviceMap.get(msgCluster);
                System.out.println("----------");
                System.out.println(clusterObj.getMap());
                System.out.println(msgDest);
                System.out.println("----------");
                for (Address add : clients.keySet()){
                    if (clusterObj.getMap().containsKey(add)){
                        if (clusterObj.getMap().get(add).equals(msgDest)){
                            clients.get(add).onNext(rep);
                            System.out.println("[gRPC] Send message to a JChannel-Client, " + clusterObj.getMap().get(add));
                        }
                    }
                }
                System.out.println("One unicast for message successfully.");

            } finally {
                lock.unlock();
            }

        }

        public void broadcastView(ViewRep videRep, String cluster){
            lock.lock();
            try{
                Response rep = Response.newBuilder()
                        .setViewResponse(videRep)
                        .build();
                ClusterMap clusterObj = (ClusterMap) jchannel.serviceMap.get(cluster);
                for (Address add : clients.keySet()){
                    if (clusterObj.getMap().containsKey(add)){
                        clients.get(add).onNext(rep);
                        System.out.println("Send view to a JChannel-Client, " + clusterObj.getMap().get(add));
                    }
                }
                System.out.println("One broadcast for view successfully.");
                System.out.println(rep.toString());

            } finally {
                lock.unlock();
            }
        }

        protected void forwardMsg(Request req){
            byte[] b =  req.toByteArray();
            Message msg = new ObjectMessage(null, b);
            // send messages exclude itself.
            msg.setFlagIfAbsent(Message.TransientFlag.DONT_LOOPBACK);
            try {
                this.jchannel.channel.send(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("forwardMsg(Request req): " + req);
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
        final NodeServer server = new NodeServer(Integer.parseInt(args[0]), args[1], args[2]);
        System.out.printf("Inf: %s %s %s \n",args[0], args[1], args[2]);
        // start gRPC service
        server.start();
        server.blockUntilShutdown();
    }
}