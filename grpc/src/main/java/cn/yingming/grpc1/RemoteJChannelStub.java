package cn.yingming.grpc1;

import com.google.protobuf.ByteString;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.jchannelRpc.*;
import io.grpc.stub.StreamObserver;
import org.jgroups.util.ByteArrayDataInputStream;
import org.jgroups.util.UUID;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class RemoteJChannelStub {

    public RemoteJChannel client;

    private ReentrantLock stubLock;
    public ArrayList serverList;
    public ManagedChannel channel;
    private JChannelsServiceGrpc.JChannelsServiceBlockingStub blockingStub;
    private JChannelsServiceGrpc.JChannelsServiceStub asynStub;
    private StreamObserver observer;
    private Object obj;


    RemoteJChannelStub(RemoteJChannel client) {
        this.client = client;
        this.stubLock = new ReentrantLock();
        this.serverList = new ArrayList<String>();
        this.channel = ManagedChannelBuilder.forTarget(client.address).usePlaintext().build();
        this.asynStub = JChannelsServiceGrpc.newStub(this.channel);
        this.blockingStub = JChannelsServiceGrpc.newBlockingStub(this.channel);
        this.observer = null;
        this.obj = new Object();
    }

    public Request judgeRequest(Object obj) {
        System.out.println("judgeRequest"+ Thread.currentThread());
        Date d = new Date();
        SimpleDateFormat dft = new SimpleDateFormat("hh:mm:ss");
        if (obj instanceof String) {
            String input = (String) obj;
            // single send request
            if (input.equals("disconnect")) {
                // disconnect request
                DisconnectReq msgReq = DisconnectReq.newBuilder()
                        .setSource(this.client.uuid)
                        .setJchannelAddress(this.client.jchannel_address.toString())
                        .setCluster(this.client.cluster)
                        .setTimestamp(dft.format(d))
                        .build();
                return Request.newBuilder()
                        .setDisconnectRequest(msgReq).build();
            } else if(input.equals("getAddress()")){
                GetAddressReq getAddressReq = GetAddressReq.newBuilder()
                        .setJchannelAddress(this.client.jchannel_address.toString())
                        .setSource(this.client.uuid)
                        .build();
                return Request.newBuilder().setGetAddressReq(getAddressReq).build();
            } else if(input.equals("getName()")){
                GetNameReq getNameReq = GetNameReq.newBuilder()
                        .setJchannelAddress(this.client.jchannel_address.toString())
                        .setSource(this.client.uuid).build();
                return Request.newBuilder().setGetNameReq(getNameReq).build();
            } else if(input.equals("getClusterName()")){
                GetClusterNameReq subReq = GetClusterNameReq.newBuilder().setJchannelAddress(this.client.jchannel_address.toString())
                        .setSource(this.client.uuid).build();
                return Request.newBuilder().setGetClusterNameReq(subReq).build();
            } else if(input.startsWith("printProtocolSpec()")){
                PrintProtocolSpecReq subReq = null;
                if (input.equals("printProtocolSpec() true")){
                    subReq = PrintProtocolSpecReq.newBuilder().setJchannelAddress(this.client.jchannel_address.toString())
                            .setSource(this.client.uuid).setIncludeProps(true).build();
                } else if (input.equals("printProtocolSpec() false")){
                    subReq = PrintProtocolSpecReq.newBuilder().setJchannelAddress(this.client.jchannel_address.toString())
                            .setSource(this.client.uuid).setIncludeProps(false).build();
                }
                return Request.newBuilder().setPrintProtoReq(subReq).build();
            } else if (input.startsWith("getState()")) {
                String[] strs = input.split(" ");
                if (strs.length > 2) {
                    throw new IllegalArgumentException("getState() error.");
                } else {
                    if (strs[1].equals("null")) {
                        StateReq stateReq = StateReq.newBuilder()
                                .setSource(client.uuid)
                                .setCluster(client.cluster)
                                .setJchannelAddress(client.jchannel_address.toString())
                                .build();

                        return Request.newBuilder().setStateReq(stateReq).build();
                    } else {
                        StateMsg_withTarget_1 msg = StateMsg_withTarget_1.newBuilder()
                                .setSource(this.client.uuid)
                                .setCluster(this.client.cluster)
                                .setJchannelAddress(this.client.jchannel_address.toString())
                                .setTarget(strs[1])
                                .build();
                        return Request.newBuilder().setStateMsg1(msg).build();
                    }
                }
            }
        } else if (obj instanceof MessageRJ) {
            MessageRJ msg = (MessageRJ) obj;
            boolean checkResult = msg.check();
            MessageReq msgReq;
            if (!checkResult) {
                // if the message has both of byte[] buf and String msg property, drop the byte[].
                try {
                    throw new IllegalArgumentException("The MessageRJ has both of buf and msg property.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // unicast
                if (msg.getDst() != null || msg.getDst() != "") {
                    msgReq = MessageReq.newBuilder()
                            .setSource(this.client.uuid)
                            .setJchannelAddress(this.client.jchannel_address.toString())
                            .setCluster(this.client.cluster)
                            .setContent(msg.getMsg())
                            .setTimestamp(dft.format(d))
                            .setDestination(msg.getDst())
                            .build();
                    //System.out.println("1");
                    return Request.newBuilder().setMessageRequest(msgReq).build();
                    // broadcast
                } else {
                    msgReq = MessageReq.newBuilder()
                            .setSource(this.client.uuid)
                            .setJchannelAddress(this.client.jchannel_address.toString())
                            .setCluster(this.client.cluster)
                            .setContent(msg.getMsg())
                            .setTimestamp(dft.format(d))
                            .build();
                    // System.out.println("2");
                    return Request.newBuilder().setMessageRequest(msgReq).build();
                }

            } else {
                // send message with String
                if (msg.getBuf() == null) {
                    if (msg.getDst() != null) {
                        msgReq = MessageReq.newBuilder()
                                .setSource(this.client.uuid)
                                .setJchannelAddress(this.client.jchannel_address.toString())
                                .setCluster(this.client.cluster)
                                .setContent(msg.getMsg())
                                .setTimestamp(dft.format(d))
                                .setDestination(msg.getDst())
                                .build();
                        // System.out.println("3");
                        return Request.newBuilder().setMessageRequest(msgReq).build();
                    } else {
                        msgReq = MessageReq.newBuilder()
                                .setSource(this.client.uuid)
                                .setJchannelAddress(this.client.jchannel_address.toString())
                                .setCluster(this.client.cluster)
                                .setContent(msg.getMsg())
                                .setTimestamp(dft.format(d))
                                .build();
                        // System.out.println("4");
                        // System.out.println(msgReq);
                        return Request.newBuilder().setMessageRequest(msgReq).build();
                    }
                } else {
                    if (msg.getDst() != null) {
                        msgReq = MessageReq.newBuilder()
                                .setSource(this.client.uuid)
                                .setJchannelAddress(this.client.jchannel_address.toString())
                                .setCluster(this.client.cluster)
                                .setContentByte(ByteString.copyFrom(msg.getBuf()))
                                .setTimestamp(dft.format(d))
                                .setDestination(msg.getDst())
                                .build();
                    } else {
                        msgReq = MessageReq.newBuilder()
                                .setSource(this.client.uuid)
                                .setJchannelAddress(this.client.jchannel_address.toString())
                                .setCluster(this.client.cluster)
                                .setContentByte(ByteString.copyFrom(msg.getBuf()))
                                .setTimestamp(dft.format(d))
                                .build();
                    }
                    return Request.newBuilder().setMessageRequest(msgReq).build();
                }
            }
        }
        return null;
    }

    public void setGeneratedAddress(ConnectRep connectRep) {
        if (connectRep.getAddress().equals("")) {
            throw new IllegalArgumentException("The ConnectResponse does not have generated Address.");
        } else {
            this.stubLock.lock();
            try {
                this.client.jchannel_address = UUID.fromString(connectRep.getAddress());
                client.isWork.set(true);
                client.down.set(true);
            } finally {
                this.stubLock.unlock();
            }
            System.out.println("[Stub]: Receive the connect response with generated Address, Address = " +
                    this.client.jchannel_address);
            synchronized (this.client.down) {
                this.client.down.notify();
            }
        }
    }

    public void judgeResponse(Response response) {
       //  System.out.println("Test print all response:" + response);
        // other
        if (response.hasConnectResponse()) {
            if (!response.getConnectResponse().getResult()) {
                throw new IllegalStateException("connect failed.Please check jchannel address set.");
            } else {
                setGeneratedAddress(response.getConnectResponse());
            }
        } else if(response.hasGetAddressRep()){
            GetAddressRep getAddressRep = response.getGetAddressRep();
            System.out.println("getAddress() response:" + getAddressRep);
            if (!getAddressRep.getIsWork()){
                this.client.real_jchannel_address = null;
            } else{
                ByteArrayDataInputStream inStream = new ByteArrayDataInputStream(getAddressRep.getAddress().toByteArray());
                UUID u = new UUID();
                try {
                    u.readFrom(inStream);
                    this.client.real_jchannel_address = u;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            synchronized (this.client.obj) {
                this.client.obj.notify();
            }
        } else if(response.hasGetNameRep()){
            GetNameRep getNameRep = response.getGetNameRep();
            System.out.println("getName() response:" + getNameRep);
            this.client.remoteName = getNameRep.getName();
            synchronized (this.client.obj) {
                this.client.obj.notify();
            }
        } else if(response.hasGetClusterNameRep()){
            GetClusterNameRep rep = response.getGetClusterNameRep();
            System.out.println("getCluster() response:" + rep);
            this.client.remoteCluster = rep.getClusterName();
            synchronized (this.client.obj) {
                this.client.obj.notify();
            }
        } else if(response.hasPrintProtoRep()){
            PrintProtocolSpecRep rep = response.getPrintProtoRep();
            System.out.println("printProtocolSpec() response:" + rep);
            this.client.remoteProtocolStack_string = rep.getProtocolStackSpec();
            synchronized (this.client.obj) {
                this.client.obj.notify();
            }
        } else if (response.hasMessageResponse()) {
            // get message from server
            // add the message response to stats object
            try {
                if (this.client.stats) {
                    this.client.stats_obj.addRecord(response);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // change to receiver, remove printMsg
            if (this.client.receiver != null) {
                try {
                    this.client.receiver.receiveRJ(response.getMessageResponse());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Receive message, but RemoteJChannel does not have receiver.");
            }
        } else if (response.hasUpdateResponse()) {
            // update the available server addresses
            update(response.getUpdateResponse().getAddresses());
        } else if (response.hasDisconnectResponse()) {
            stubLock.lock();
            try {
                client.down.set(false);
            } finally {
                stubLock.unlock();
            }

        } else if (response.hasViewResponse()) {
            ViewRep view = response.getViewResponse();

            this.client.view.updateView(view);
            // add 1 view num in the record stats
            if (this.client.stats) {
                this.client.stats_obj.addViewSize();
            }
            // change:  add receiver of remote// viewAccepted
            if (this.client.receiver != null) {
                try {
                    this.client.receiver.viewAcceptedRJ(view);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Receive view, but RemoteJChannel does not have receiver.");
            }
        } else if (response.hasStateRep()) {
            StateRep state = response.getStateRep();
            // System.out.println("JudgeResponse 0 conect:" + msg_obj.getContent()
            //  + " jchannel address: " + msg_obj.getJchannelAddress());
            if (this.client.receiver != null) {
                try {
                    this.client.receiver.setStateRJ(state.getOneOfHistoryList());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Receive state without target, but RemoteJChannel does not have receiver.");
            }
        } else if (response.hasStateMsg1()) {
            StateMsg_withTarget_1 msg1 = response.getStateMsg1();
            if (!this.client.jchannel_address.equals(msg1.getTarget())) {
                System.out.println("error getState(target) message.");
            } else {
                if (this.client.receiver != null) {
                    stubLock.lock();
                    try {
                        StateMsg_withTarget_2 msg2 = StateMsg_withTarget_2.newBuilder()
                                .setCluster(this.client.cluster)
                                .setJchannelAddress(this.client.jchannel_address.toString())
                                .setSource(this.client.uuid)
                                .setTarget(msg1.getJchannelAddress())
                                .addAllOneOfHistory(this.client.receiver.getStateRJ())
                                .build();
                        Request req = Request.newBuilder()
                                .setStateMsg2(msg2)
                                .build();
                        this.observer.onNext(req);
                    } finally {
                        stubLock.unlock();
                    }
                } else {
                    System.out.println("The RemoteJChannel does not have receiver.");
                }

            }
        } else if (response.hasStateMsg2()) {
            StateMsg_withTarget_2 msg = response.getStateMsg2();
            if (this.client.receiver != null) {
                try {
                    this.client.receiver.setStateRJ(msg.getOneOfHistoryList());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Receive state, but RemoteJChannel does not have receiver.");
            }
        } // else if ()
    }

    private StreamObserver startGrpc(AtomicBoolean isWork, RemoteJChannel client) {

        ReentrantLock lock = new ReentrantLock();
        // Service 1
        StreamObserver<Request> requestStreamObserver = asynStub.connect(new StreamObserver<Response>() {

            @Override
            public void onNext(Response response) {
                // whether discard message of itself
                String add = response.getMessageResponse().getJchannelAddress();
                if (client.discard_own_messages && response.hasMessageResponse() && add.equals(client.jchannel_address)) {

                } else {
                    judgeResponse(response);
                }
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(throwable.getMessage());
                System.out.println("[gRPC]: onError() of gRPC connection, the client needs to reconnect to the next server.");

                lock.lock();
                try {
                    isWork.set(false);
                } finally {
                    lock.unlock();
                }
                channel.shutdown();
                onCompleted();
            }

            @Override
            public void onCompleted() {
                System.out.println("[gRPC]: onCompleted of the current channel.");
            }
        });
        return requestStreamObserver;

    }


    public void update(String addresses){
        String[] add = addresses.split(" ");
        List<String> newList = Arrays.asList(add);
        stubLock.lock();
        try {
            serverList.clear();
            serverList.addAll(newList);
            System.out.println("Update addresses of servers: " + serverList);
        } finally {
            stubLock.unlock();
        }
    }
    public void add_save(Object input){
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            this.client.msgList.add(input);
        } finally {
            lock.unlock();
        }
    }

    private boolean tryOneConnect() {
        try {
            Thread.sleep(5000);
        } catch (Exception e){
            e.printStackTrace();
        }

        ReqAsk req = ReqAsk.newBuilder().setSource(client.uuid).build();
        try {
            RepAsk rep = this.blockingStub.withDeadlineAfter(5000, TimeUnit.MILLISECONDS).ask(req);
            if (rep.getSurvival()) {
                return true;
            } else {
                System.out.println("[Reconnection]: One server refuses, next server.");
            }
        } catch (Exception e) {
            System.out.println("[Reconnection]: The new try connection is also not available.");
            // e.printStackTrace();
        }
        return false;
    }

    private void connectCluster(StreamObserver requestStreamObserver) {
        // Generated time
        Date d = new Date();
        SimpleDateFormat dft = new SimpleDateFormat("hh:mm:ss");
        // connect() request
        ConnectReq joinReq = ConnectReq.newBuilder()
                .setSource(client.uuid)
                .setCluster(client.cluster)
                .setTimestamp(dft.format(d))
                .build();
        Request req = Request.newBuilder()
                .setConnectRequest(joinReq)
                .build();
       requestStreamObserver.onNext(req);
    }
    private void reconnectCluster(StreamObserver requestStreamObserver) {
        // Generated time
        Date d = new Date();
        SimpleDateFormat dft = new SimpleDateFormat("hh:mm:ss");
        // connect() request
        ConnectReq joinReq = ConnectReq.newBuilder()
                .setSource(client.uuid)
                .setCluster(client.cluster)
                .setJchannelAddress(client.jchannel_address.toString())
                .setReconnect(true)
                .setTimestamp(dft.format(d))
                .build();
        Request req = Request.newBuilder()
                .setConnectRequest(joinReq)
                .build();
        requestStreamObserver.onNext(req);
    }
    // Do a reconnection loop with given times. e.g. 10 times.
    private boolean reconnect() {
        int count = 0;
        if (this.serverList.size() == 0){
            System.out.println("The available server list is null. Cannot select new address of node.");
            this.stubLock.lock();
            try{
                this.client.down.set(false);
            } finally {
                this.stubLock.unlock();
            }
            return false;
        }
        while (true) {
            count++;
            Random r = new Random();
            int randomSelect = r.nextInt(this.serverList.size());
            String newAdd = (String) this.serverList.get(randomSelect);
            System.out.println("[Reconnection]: Random selected server for reconnection:" + newAdd);
            // try to build new channel and generate new stubs for new server
            this.channel = ManagedChannelBuilder.forTarget(newAdd).usePlaintext().build();
            this.asynStub = JChannelsServiceGrpc.newStub(this.channel);
            this.blockingStub = JChannelsServiceGrpc.newBlockingStub(this.channel);
            // send a unary request for test
            boolean tryResult = tryOneConnect();
            // using try result to judge
            if (tryResult) {
                client.address = newAdd;
                System.out.println("[Reconnection]: Reconnect successfully to server-" + client.address);
                return true;
            }
            // maximum reconnection time
            if (count > 9999) {
                break;
            }
        }
        System.out.println("[Reconnection]: Reconnect many times, end the reconnection loop.");
        return false;
    }

    class Control implements Runnable {
        ArrayList sharedList;
        AtomicBoolean isWork;
        RemoteJChannelStub stub;
        AtomicBoolean down;
        public Control(ArrayList sharedList, AtomicBoolean isWork, AtomicBoolean down,RemoteJChannelStub stub) {
            this.sharedList = sharedList;
            this.isWork = isWork;
            this.stub = stub;
            this.down = down;
        }

        @Override
        public void run() {
            while (true) {
                // start gRPC client and call connect() request.
                stub.observer = startGrpc(this.isWork, this.stub.client);
                // The first connect
                if (!down.get() && !isWork.get()){
                    System.out.println("first connection");
                    connectCluster(stub.observer);
                } else{
                    // reconnection to other server with correct Address
                    System.out.println("not first connection");
                    reconnectCluster(stub.observer);
                }
                // wait for result
                synchronized (down){
                    try {
                        System.out.println("generate address request"+ Thread.currentThread());
                        down.wait();
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
                // check loop for connection problem and input content, and send request.
                this.checkLoop(this.stub.observer);
                // System.out.println("222" + client.down.get());
                // reconnect part.
                if (!client.down.get()){
                    try{
                        // System.out.println("exit 0 on control thread.");
                        System.exit(0);
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
                boolean result = reconnect();
                if (!result) {
                    //System.out.println("End the control loop of stub.");
                    break;
                }
            }

        }

        // check input and state of streaming, and send messsage
        private void checkLoop(StreamObserver requestSender) {
            while (true) {
                if (!client.down.get()){
                    try{
                        //System.out.println("exit 0 on control thread.");
                        System.exit(0);
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
                // the if statement is for inputLoop thread and state of bidirectional streaming.
                // If the channel
                if (client.msgList.size() != 0 && client.isWork.get()) {
                    Object obj = client.msgList.get(0);
                    // System.out.println("2"+ Thread.currentThread());
                    Request msgReq = judgeRequest(obj);
                    requestSender.onNext(msgReq);
                    try{
                        if (this.stub.client.stats){
                            this.stub.client.stats_obj.addRecord(msgReq);
                        }
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    stubLock.lock();
                    try {
                        client.msgList.remove(0);
                    } finally {
                        stubLock.unlock();
                    }


                } else if (!client.isWork.get()) {
                    break;
                }
            }
        }

    }

    public void startStub(){
        Control control = new Control(client.msgList, client.isWork, client.down,this);
        Thread thread1 = new Thread(control);
        thread1.start();
    }
}
