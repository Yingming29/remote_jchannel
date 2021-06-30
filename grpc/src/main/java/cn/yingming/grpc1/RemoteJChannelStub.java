package cn.yingming.grpc1;

import com.google.protobuf.ByteString;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.jchannelRpc.*;
import io.grpc.stub.StreamObserver;
import org.jgroups.*;
import org.jgroups.util.*;
import org.jgroups.util.UUID;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
        Date d = new Date();
        SimpleDateFormat dft = new SimpleDateFormat("hh:mm:ss");
        if (obj instanceof String) {
            String input = (String) obj;
            // single send request
            if (input.equals("disconnect")) {
                // disconnect request
                ByteArrayDataOutputStream out = new ByteArrayDataOutputStream();
                UUID u = (UUID) this.client.jchannel_address;
                try {
                    u.writeTo(out);
                } catch (Exception e){
                    e.printStackTrace();
                }
                DisconnectReq msgReq = DisconnectReq.newBuilder()
                        .setJchannelAddress(ByteString.copyFrom(out.buffer()))
                        .setCluster(this.client.cluster)
                        .setTimestamp(dft.format(d))
                        .build();
                return Request.newBuilder()
                        .setDisconnectRequest(msgReq).build();
            } else if(input.equals("getAddress()")){
                GetAddressReq getAddressReq = GetAddressReq.newBuilder()
                        .setJchannelAddress(this.client.jchannel_address.toString())
                        .build();
                return Request.newBuilder().setGetAddressReq(getAddressReq).build();
            } else if(input.equals("getDiscardOwnMessage()")) {
                GetDiscardOwnMsgReq discardMsgReq = GetDiscardOwnMsgReq.newBuilder().setJchannelAddress(this.client.jchannel_address.toString()).build();
                return Request.newBuilder().setGetDiscardOwnMsgReq(discardMsgReq).build();
            } else if(input.equals("getState()")) {
                GetStateReq getStateReq = GetStateReq.newBuilder().setJchannelAddress(this.client.jchannel_address.toString()).build();
                return Request.newBuilder().setGetStateReq(getStateReq).build();
            } else if(input.startsWith("setDiscardOwnMessage()")) {
                String[] strs = input.split(" ");
                boolean b = false;
                if (strs[1].equals("true")){
                    b = true;
                }
                SetDiscardOwnMsgReq discardMsgReq = SetDiscardOwnMsgReq.newBuilder()
                        .setDiscard(b).setJchannalAddress(this.client.jchannel_address.toString()).build();
                System.out.println(discardMsgReq);
                return Request.newBuilder().setSetDiscardOwnMsgReq(discardMsgReq).build();
            } else if(input.equals("getStats()")){
                GetStatsReq getStatsReq = GetStatsReq.newBuilder().setJchannelAddress(this.client.jchannel_address.toString()).build();
                return Request.newBuilder().setGetStatReq(getStatsReq).build();
            } else if(input.equals("getName()")){
                GetNameReq getNameReq = GetNameReq.newBuilder()
                        .setJchannelAddress(this.client.jchannel_address.toString())
                        .build();
                return Request.newBuilder().setGetNameReq(getNameReq).build();
            } else if(input.startsWith("setStats()")){
                System.out.println(input);
                SetStatsReq setStatsReq = null;
                if (input.equals("setStats() true")){
                    setStatsReq = SetStatsReq.newBuilder().setJchannelAddress(this.client.jchannel_address.toString()).setStats(true).build();
                } else if (input.equals("setStats() false")){
                    setStatsReq = SetStatsReq.newBuilder().setJchannelAddress(this.client.jchannel_address.toString()).setStats(false).build();
                }
                return Request.newBuilder().setSetStatsReq(setStatsReq).build();
            } else if(input.equals("getProperties()")){
                GetPropertyReq getPropertyReq = GetPropertyReq.newBuilder().setJchannelAddress(this.client.jchannel_address.toString()).build();
                return Request.newBuilder().setGetPropertyReq(getPropertyReq).build();
            } else if(input.equals("getClusterName()")){
                GetClusterNameReq subReq = GetClusterNameReq.newBuilder().setJchannelAddress(this.client.jchannel_address.toString())
                        .build();
                return Request.newBuilder().setGetClusterNameReq(subReq).build();
            } else if(input.startsWith("printProtocolSpec()")){
                PrintProtocolSpecReq subReq = null;
                if (input.equals("printProtocolSpec() true")){
                    subReq = PrintProtocolSpecReq.newBuilder().setJchannelAddress(this.client.jchannel_address.toString())
                            .setIncludeProps(true).build();
                } else if (input.equals("printProtocolSpec() false")){
                    subReq = PrintProtocolSpecReq.newBuilder().setJchannelAddress(this.client.jchannel_address.toString())
                            .setIncludeProps(false).build();
                }
                return Request.newBuilder().setPrintProtoReq(subReq).build();
            } else if(input.equals("isOpen()")){
                IsStateReq req = IsStateReq.newBuilder().setJchannelAddress(this.client.jchannel_address.toString()).setType("isOpen").build();
                return Request.newBuilder().setIsStateReq(req).build();
            } else if(input.equals("isConnecting()")){
                IsStateReq req = IsStateReq.newBuilder().setJchannelAddress(this.client.jchannel_address.toString()).setType("isConnecting").build();
                return Request.newBuilder().setIsStateReq(req).build();
            } else if(input.equals("isConnected()")){
                IsStateReq req = IsStateReq.newBuilder().setJchannelAddress(this.client.jchannel_address.toString()).setType("isConnected").build();
                return Request.newBuilder().setIsStateReq(req).build();
            } else if(input.equals("isClosed()")){
                IsStateReq req = IsStateReq.newBuilder().setJchannelAddress(this.client.jchannel_address.toString()).setType("isClosed").build();
                return Request.newBuilder().setIsStateReq(req).build();
            } else if(input.startsWith("getState()")){
                String[] strs = input.split(" ");
                Address source = this.client.jchannel_address;
                Address target = null;
                StateReq stateReq = null;
                for (Address each:NameCache.getContents().keySet()) {
                    if (NameCache.getContents().get(each).equals(strs[1])){
                        target = each;
                    }
                }
                try {
                    stateReq = StateReq.newBuilder().setJchannelAddress(ByteString.copyFrom(Util.objectToByteBuffer(source)))
                            .setTarget(ByteString.copyFrom(Util.objectToByteBuffer(target))).setTimeout(Long.parseLong(strs[2])).build();
                } catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println("getState()"+ stateReq);
                return Request.newBuilder().setStateReq(stateReq).build();
            }
        } else if(obj instanceof Message) {
            Message m = (Message) obj;
            m.setSrc(client.jchannel_address);
            ByteArrayDataOutputStream out = new ByteArrayDataOutputStream();
            try {
                m.writeTo(out);
            } catch (Exception e){
                e.printStackTrace();
            }
            MessageReqRep msg = MessageReqRep.newBuilder().setMessageObj(ByteString.copyFrom(out.buffer())).setType(UtilsRJ.getMsgType(m)).build();
            Request req = Request.newBuilder().setMessageReqRep(msg).build();
            return req;
        } else if (obj instanceof DumpStatsReq){
            DumpStatsReq dumpReq = (DumpStatsReq) obj;
            return Request.newBuilder().setDumpStatsReq(dumpReq).build();
        }
        return null;
    }

    public void setGeneratedAddress(ConnectRep connectRep) {
        if (connectRep.getAddress().equals("")) {
            throw new IllegalArgumentException("The ConnectResponse does not have generated Address.");
        } else {
            this.stubLock.lock();
            try {
                UUID u = new UUID();
                ByteArrayDataInputStream in = new ByteArrayDataInputStream(connectRep.getAddress().toByteArray());
                u.readFrom(in);
                // three properties:
                this.client.jchannel_address = u;
                this.client.name = connectRep.getLogicalName();
                client.isWork.set(true);
                client.down.set(true);
                NameCache.add(this.client.jchannel_address, this.client.name);
                System.out.println("setGeneratedAddress() prints NameCache: " + NameCache.printCache());
            } catch (IOException e) {
                e.printStackTrace();
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

    public void judgeResponse(Response response){
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
        } else if(response.hasGetStateRep()){
            GetStateRep rep = response.getGetStateRep();
            System.out.println("getState() response:" + rep);
            this.client.state = rep.getState();
            synchronized (this.client.obj) {
                this.client.obj.notify();
            }
        } else if(response.hasDumpStatsRep()){
            DumpStatsRep dumpRep = response.getDumpStatsRep();
            System.out.println("dumpStats() response:" + dumpRep);
            this.client.statsMap = (Map<String, Map<String, Object>>) UtilsRJ.unserializeObj(dumpRep.getSerializeMap().toByteArray());
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
        } else if(response.hasSetStatsRep()){
            SetStatsRep rep = response.getSetStatsRep();
            System.out.println("setStats() response:" + rep);
            this.client.stats = rep.getStats();
            synchronized (this.client.obj) {
                this.client.obj.notify();
            }
        } else if(response.hasGetPropertyRep()){
            GetPropertyRep rep = response.getGetPropertyRep();
            System.out.println("getProperties() response:" + rep);
            this.client.property = rep.getProperties();
            synchronized (this.client.obj) {
                this.client.obj.notify();
            }
        } else if(response.hasGetDiscardOwnRep()){
            GetDiscardOwnMsgRep rep = response.getGetDiscardOwnRep();
            System.out.println("getDiscardOwnMessage() response:" + rep);
            this.client.discard_own_messages = rep.getDiscard();
            synchronized (this.client.obj) {
                this.client.obj.notify();
            }
        } else if(response.hasSetDiscardOwnRep()){
            SetDiscardOwnMsgRep rep = response.getSetDiscardOwnRep();
            System.out.println("setDiscardOwnMessage() response:" + rep);
            this.client.discard_own_messages = rep.getDiscard();
            synchronized (this.client.obj) {
                this.client.obj.notify();
            }
        } else if(response.hasIsStateRep()){
            IsStateRep rep = response.getIsStateRep();
            System.out.println("isState(isOpen/isConnecting/isConnected/isClosed) response:" + rep);
            this.client.isState = rep.getResult();
            synchronized (this.client.obj) {
                this.client.obj.notify();
            }
        } else if (response.hasUpdateNameCache()){
            UpdateNameCacheRep nameCacheRep = response.getUpdateNameCache();
            List<ByteString> addressList = nameCacheRep.getAddressList();
            List<String> nameList = nameCacheRep.getLogicalNameList();
            for (int i = 0; i < addressList.size(); i++) {
                ByteString bs = addressList.get(i);
                byte[] byte_address = bs.toByteArray();
                UUID u = new UUID();
                ByteArrayDataInputStream in = new ByteArrayDataInputStream(byte_address);
                try {
                    u.readFrom(in);
                } catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println("A pair of Address and logical name: " + u + ", " + nameList.get(i));
                stubLock.lock();
                try {
                    NameCache.add(u, nameList.get(i));
                } finally {
                    stubLock.unlock();
                }
            }
        } else if(response.hasViewRepServer()){
            ViewRep_server view_server = response.getViewRepServer();
            ByteArrayDataInputStream v_in = new ByteArrayDataInputStream(view_server.getViewByte().toByteArray());
            View new_view = new View();
            try {
                new_view.readFrom(v_in);
            } catch (Exception e){
                e.printStackTrace();
            }
            stubLock.lock();
            try {
                this.client.remoteView = new_view;
            } finally {
                stubLock.unlock();
            }
            System.out.println("View of JChannel-node(Receiver of JChannel-client?): " + this.client.remoteView);
        } else if (response.hasMessageReqRep()) {
            // change to receiver, remove printMsg
            if (this.client.receiver != null) {
                try {
                    Message msg = Util.objectFromByteBuffer(response.getMessageReqRep().getMessageObj().toByteArray());
                    // Message msg = UtilsRJ.convertMessage(response.getMessageReqRep());
                    this.client.receiver.receive(msg);
                } catch (Exception e) {
                    // e.printStackTrace();
                }
            } else {
                System.out.println("Receive message, but RemoteJChannel does not have receiver.");
            }
        } else if (response.hasGetStatsRep()) {
            GetStatsRep rep = response.getGetStatsRep();
            System.out.println("getStats() response:" + rep);
            this.client.stats = rep.getStats();
            synchronized (this.client.obj) {
                this.client.obj.notify();
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
            ByteArrayDataInputStream v_in = new ByteArrayDataInputStream(view.getView().toByteArray());
            View new_view = new View();
            stubLock.lock();
            try {
                new_view.readFrom(v_in);
            } catch (Exception e){
                this.client.view = new_view;
                e.printStackTrace();
            } finally {
                stubLock.unlock();
            }
            // cheng :  remove the stats and discardOwnMessage on the JChannel Client //////////add 1 view num in the record stats
            /*
            if (this.client.stats) {
                this.client.stats_obj.addViewSize();
            }

             */
            // change:  add receiver of remote// viewAccepted
            if (this.client.receiver != null) {
                try {
                    this.client.receiver.viewAccepted(new_view);
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
            byte[] b = state.getState().toByteArray();
            ByteArrayInputStream in = new ByteArrayInputStream(b);
            try{
                if (!(this.client.getReceiver() == null)){
                    this.client.getReceiver().setState(in);
                } else {
                    System.out.println("[Stub] Receive a state (message history), but the JChannel-Client does not have Receiver.");
                }
            } catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    private StreamObserver startGrpc(AtomicBoolean isWork, RemoteJChannel client) {

        ReentrantLock lock = new ReentrantLock();
        // Service 1
        StreamObserver<Request> requestStreamObserver = asynStub.connect(new StreamObserver<Response>() {

            @Override
            public void onNext(Response response) {
                System.out.println(response);
                judgeResponse(response);
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

        ReqAsk req = ReqAsk.newBuilder().setSource(client.jchannel_address.toString()).build();
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
        ByteArrayDataOutputStream out = new ByteArrayDataOutputStream();
        try {
            this.client.jchannel_address.writeTo(out);
        } catch (Exception e){
            e.printStackTrace();
        }
        ConnectReq joinReq = ConnectReq.newBuilder()
                .setCluster(client.cluster)
                .setLogicalName(client.jchannel_address.toString())
                .setJchannAddressByte(ByteString.copyFrom(out.buffer()))
                .setReconnect(true)
                .setTimestamp(dft.format(d))
                .build();
        System.out.println("Reconnect msg:" + joinReq);
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
                        // System.out.println("generate address request"+ Thread.currentThread());
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
                        System.out.println("exit 0 on control thread.");
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
                // System.out.println(12312);
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
                    /*
                    try{
                        if (this.stub.client.stats){
                            this.stub.client.stats_obj.addRecord(msgReq);
                        }
                    } catch (Exception e){
                        e.printStackTrace();
                    }

                     */
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
