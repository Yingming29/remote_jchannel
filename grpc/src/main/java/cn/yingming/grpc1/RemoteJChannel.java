package cn.yingming.grpc1;

import io.grpc.jchannelRpc.DumpStatsReq;
import org.jgroups.*;
import org.jgroups.stack.AddressGenerator;
import org.jgroups.stack.ProtocolStack;
import org.jgroups.util.*;

import java.util.*;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

public class RemoteJChannel extends JChannel {
    // address is the grpc server target
    public String address;
    // name and remote name
    // name can be removed?
    public String name;
    public String remoteName;
    // client cluster and remote cluster
    public String cluster;
    public String remoteCluster;
    // client and remote JChannel Address
    public Address jchannel_address;
    public Address real_jchannel_address;
    public AtomicBoolean isWork;
    public ArrayList msgList;
    public RemoteJChannelStub clientStub;
    public AtomicBoolean down;
    // client view and remote server view
    public View view;
    public View remoteView;
    // whether receive message of itself
    public boolean discard_own_messages;
    // whether stats?
    public boolean stats;
    // record for stats of remote jchannel
    public Receiver receiver;
    public Object obj;
    public String remoteProtocolStack_string;
    public String property;
    public Map<String, Map<String, Object>> statsMap;
    public String state;

    public RemoteJChannel(String address) throws Exception{
        this.address = address;
        // as the source of the RemoteJChannel
        this.name = null;
        this.cluster = null;
        this.msgList = new ArrayList<Object>();
        // generated fake address.
        this.jchannel_address = null;
        this.real_jchannel_address = null;
        this.clientStub = null;
        this.view = null;
        // whether the grpc connection work
        this.isWork = new AtomicBoolean(false);
        // whether shutdown the RemoteJChannel
        this.down = new AtomicBoolean(false);
        // whether receive message of itself
        this.discard_own_messages = false;
        // whether record the stats of the RemoteJChannel
        this.stats = false;
        // change: create class for stats obj record
        this.receiver = null;
        this.obj = new Object();
        this.remoteName = null;
        this.remoteCluster = null;
        this.remoteProtocolStack_string = null;
        this.remoteView = new View();
        this.property = null;
        this.statsMap = null;
        this.state = null;
    }

    @Override
    public Receiver getReceiver() {
        return this.receiver;
    }

    @Override
    public JChannel setReceiver(Receiver r) {
        if (this.isWork.get()){
            System.out.println("The RemoteJChannel is working.");
        } else{
            this.receiver = r;
        }
        return this;
    }

    @Override
    public JChannel receiver(Receiver r) {
        if (this.isWork.get()){
            System.out.println("The RemoteJChannel is working.");
        } else{
            this.receiver = r;
        }
        return this;
    }

    @Override
    /** getAddress() -> address()
     * address() is a remote grpc call. get the Address of remote JChannel (real JChannel)
     */
    public Address getAddress() {
        return address();
    }

    @Override
    /** grpc call for the Address of remote JChannel (real jchannel)  -> return result by grpc
     * @return Address, the Address of the remote JChannel (real JChannel) of this client.
     */
    public Address address() {
        if (!isWork.get()){
            System.out.println("The RemoteJChannel client does not start work. Return null");
            return null;
        }
        this.clientStub.add_save("getAddress()");
        synchronized (obj){
            try{
                obj.wait(5000);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        // System.out.println("utils: "+ Util.createRandomAddress("client"));
        return this.real_jchannel_address;
    }

    /**
     * Get the local Address of client, which is generated and sent by remote JChannel, cached locally in the client..
     * @return Address, the Address of the RemoteJChannelClient.
     */
    public Address getLocalAddress(){
        if (!isWork.get() && !down.get()){
            System.out.println("The RemoteJChannel client does not start work. Return null");
            return null;
        }
        return this.jchannel_address;
    }

    /**
     * call name()
     * @return String, the name of remote JChannel.
     */
    public String getName() {
        if (!isWork.get() && !down.get()){
            System.out.println("The RemoteJChannel client does not start work. Return null");
            return null;
        }
        return name();
    }

    /**
     * grpc call get the logical name of the remote JChannel (real JChannel).
     * @return String, the name of remote JChannel.
     */
    public String name() {
        if (!isWork.get()){
            System.out.println("The RemoteJChannel client does not start work. Return null");
            return null;
        }
        this.clientStub.add_save("getName()");
        synchronized (obj){
            try{
                obj.wait(5000);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return this.remoteName;
    }

    @Override
    // setName
    public JChannel name(String name) {
        throw new UnsupportedOperationException("RemoteJChannel client cannot setName() and name(String name).");
    }
    @Override
    /**
     * getClusterName() is a remote grpc call. get the remote JChannel's cluster, e.g. NodeCluster
     * @return String, the cluster name of remote JChannel.
     */
    public String clusterName() {
        return this.getClusterName();
    }

    @Override
    public View getView() {
        throw new UnsupportedOperationException("RemoteJChannel does not have View object. " +
                "Please use new method getRemoteJChannelView().");
    }

    @Override
    public View view() {
        throw new UnsupportedOperationException("RemoteJChannel does not have View object. " +
                "Please use new method remoteJChannelView().");
    }

    public View getRemoteJChannelView(){
        return this.remoteJChannelView();
    }

    public View remoteJChannelView(){
        return this.isWork.get() ? this.view : null;
    }
    @Override
    public boolean getStats() {
        if (!isWork.get()){
            System.out.println("The RemoteJChannel client does not start work. Return false");
            return false;
        }
        this.clientStub.add_save("getStats()");
        synchronized (obj){
            try{
                obj.wait(5000);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return this.stats;
    }
    @Override
    public boolean stats() {
        if (!isWork.get()){
            System.out.println("The RemoteJChannel client does not start work. Return false");
            return false;
        }
        this.clientStub.add_save("getStats()");
        synchronized (obj){
            try{
                obj.wait(5000);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return this.stats;
    }
    @Override
    public JChannel setStats(boolean stats) {
        if (!isWork.get()){
            System.out.println("The RemoteJChannel client does not start work.");
            return this;
        }
        if (stats){
            this.clientStub.add_save("setStats() true");
        } else{
            this.clientStub.add_save("setStats() false");
        }
        synchronized (obj){
            try{
                obj.wait(5000);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return this;
    }
    @Override
    public JChannel stats(boolean stats) {
        if (!isWork.get()){
            System.out.println("The RemoteJChannel client does not start work.");
            return this;
        }
        if (stats){
            this.clientStub.add_save("setStats() true");
        } else{
            this.clientStub.add_save("setStats() false");
        }
        synchronized (obj){
            try{
                obj.wait(5000);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return this;
    }
    @Override
    public boolean getDiscardOwnMessages() {
        if (!isWork.get()){
            System.out.println("The RemoteJChannel client does not start work.");
            return false;
        }
        this.clientStub.add_save("getDiscardOwnMessage()");
        synchronized (obj){
            try{
                obj.wait(5000);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return this.discard_own_messages;
    }

    @Override
    public JChannel setDiscardOwnMessages(boolean flag) {
        if (!isWork.get()){
            System.out.println("The RemoteJChannel client does not start work.");
            return this;
        }
        this.clientStub.add_save("setDiscardOwnMessage() " + flag);
        synchronized (obj){
            try{
                obj.wait(5000);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return this;
    }

    @Override
    /*
    The methods returns the Address String of remote real JChannel.
     */
    public String getAddressAsString() {
        this.clientStub.add_save("getAddress()");
        synchronized (obj){
            try{
                obj.wait(5000);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return real_jchannel_address != null ? real_jchannel_address.toString() : "n/a";
    }

    @Override
    /*
    The methods returns the Address String of remote real JChannel.
     */
    public String getAddressAsUUID() {
        this.clientStub.add_save("getAddress()");
        synchronized (obj){
            try{
                obj.wait(5000);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return real_jchannel_address instanceof org.jgroups.util.UUID ? ((org.jgroups.util.UUID)real_jchannel_address).toStringLong() : null;
    }

    @Override
    public String getClusterName() {
        if (!isWork.get() && !down.get()){
            System.out.println("The RemoteJChannel client does not start work. Return null");
            return null;
        }
        this.clientStub.add_save("getClusterName()");
        synchronized (obj){
            try{
                obj.wait(5000);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return this.remoteCluster;
    }

    // get the ClientCluster name
    public String getLocalClusterName(){
        return this.isWork.get() ? this.cluster : null;
    }

    @Override
    public String getViewAsString() {
        if (isWork.get() && this.view != null){
            return this.view.toString();
        } else{
            throw new IllegalStateException("View cannot be get if channel is not connected or does not have View");
        }
    }

    @Override
    public String getState() {
        if (!isWork.get() && !down.get()){
            System.out.println("The RemoteJChannel client does not start work. Return null");
            return null;
        }
        this.clientStub.add_save("getState()");
        synchronized (obj){
            try{
                obj.wait(5000);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return this.state;
    }
    @Override
    public boolean isOpen() {
        if(this.clientStub != null && this.clientStub.channel != null){
            if (!this.clientStub.channel.isTerminated() && !this.clientStub.channel.isShutdown()){
                return true;
            }
        } else{
            throw new IllegalStateException("The stub or channel of stub does not work.");
        }
        return false;
    }

    @Override
    // change
    public boolean isConnected() {
        throw new UnsupportedOperationException("RemoteJChannel does not have CONNECTED state." +
                "Please use isOpen() or getState().");
    }

    @Override
    public boolean isConnecting() {
        throw new UnsupportedOperationException("RemoteJChannel does not have CONNECTING state." +
                "Please use isOpen(), isClose() or getState().");
    }

    @Override
    public boolean isClosed() {
        if(this.clientStub != null && this.clientStub.channel != null){
            if (this.clientStub.channel.isTerminated() || this.clientStub.channel.isShutdown()){
                return true;
            }
        } else{
            throw new IllegalStateException("The stub or channel of stub does not work.");
        }
        return false;
    }

    public static String getVersion() {
        return Version.printDescription();
    }

    @Override
    public String getProperties() {
        if (!isWork.get() && !down.get()){
            System.out.println("The RemoteJChannel client does not start work. Return null");
            return null;
        }
        this.clientStub.add_save("getProperties()");
        synchronized (obj){
            try{
                obj.wait(5000);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return this.property;
    }

    @Override
    public String printProtocolSpec(boolean include_props) {
        this.clientStub.add_save("printProtocolSpec() " + include_props);
        synchronized (obj){
            try{
                obj.wait(5000);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return this.remoteProtocolStack_string;
    }


    @Override
    public Map<String, Map<String, Object>> dumpStats() {
        if (!isWork.get()){
            System.out.println("The RemoteJChannel client does not work. Return null");
            return null;
        }
        DumpStatsReq dumpStatsReq = DumpStatsReq.newBuilder().setJchannelAddress(this.jchannel_address.toString()).build();
        this.clientStub.add_save(dumpStatsReq);
        synchronized (obj){
            try{
                obj.wait(5000);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return this.statsMap;
    }

    @Override
    public Map<String, Map<String, Object>> dumpStats(String protocol_name, List<String> attrs) {
        if (!isWork.get()){
            System.out.println("The RemoteJChannel client does not work. Return null");
            return null;
        }
        DumpStatsReq dumpStatsReq = DumpStatsReq.newBuilder().setProtocolName(protocol_name).addAllAttrs(attrs).setJchannelAddress(this.jchannel_address.toString()).build();
        this.clientStub.add_save(dumpStatsReq);
        synchronized (obj){
            try{
                obj.wait(5000);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return this.statsMap;
    }

    @Override
    public Map<String, Map<String, Object>> dumpStats(String protocol_name) {
        if (!isWork.get()){
            System.out.println("The RemoteJChannel client does not work. Return null");
            return null;
        }
        DumpStatsReq dumpStatsReq = DumpStatsReq.newBuilder().setProtocolName(protocol_name).setJchannelAddress(this.jchannel_address.toString()).build();
        this.clientStub.add_save(dumpStatsReq);
        synchronized (obj){
            try{
                obj.wait(5000);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return this.statsMap;
    }


    @Override
    public synchronized JChannel connect(String cluster_name) throws Exception {
        if (cluster_name == null || cluster_name.equals("")){
            throw new IllegalArgumentException("The cluster_name cannot be null.");
        }
        this.cluster = cluster_name;
        boolean checkResult = this.checkProperty();
        if (checkResult){
            this.clientStub = new RemoteJChannelStub(this);
            this.clientStub.startStub();
            return this;
        } else{
            throw new IllegalStateException("The connect() does not work " +
                    "because the RemoteJchannel miss some properties.");
        }
    }

    // target is the address of grpc server.
    public synchronized JChannel connect(String cluster_name, String target) throws Exception {
        if (cluster_name == null || cluster_name.equals("")){
            throw new IllegalArgumentException("The cluster_name cannot be null.");
        } else if (target == null || target.equals("")){
            throw new IllegalArgumentException("The target cannot be null.");
        }
        this.cluster = cluster_name;
        this.address = target;
        boolean checkResult = this.checkProperty();
        if (checkResult){
            this.clientStub = new RemoteJChannelStub(this);
            this.clientStub.startStub();
            return this;
        } else{
            throw new IllegalStateException("The connect() does not work " +
                    "because the RemoteJchannel miss some properties.");
        }
    }
    // the cluster can be changed to null, because they connects to one cluster
    private boolean checkProperty(){
        if (this.address == null || this.address.equals("")){
            throw new IllegalStateException("The address (for grpc server) of RemoteJChannel is null.");
        } else if (this.cluster == null || this.cluster.equals("")){
            throw new IllegalStateException("The cluster of RemoteJChannel is null.");
        } else if (this.isWork.get()){
            throw new IllegalStateException("The isWork of RemoteJChannel is true.");
        } else if (this.msgList == null){
            throw new IllegalStateException("The msgList (message list) of RemoteJChannel is null.");
        } else{
            return true;
        }
    }

    @Override
    protected synchronized JChannel connect(String cluster_name, boolean useFlushIfPresent) throws Exception {
        throw new UnsupportedOperationException("RemoteJChannel does not support this connect().");
    }
    @Override
    public synchronized JChannel connect(String cluster_name, Address target, long timeout) throws Exception {
        throw new UnsupportedOperationException("RemoteJChannel does not support this connect()." +
                "PLease use connect(String cluster) or connect(String cluster, String target)");
    }
    @Override
    public synchronized JChannel connect(String cluster_name, Address target, long timeout, boolean useFlushIfPresent) throws Exception {
        throw new UnsupportedOperationException("RemoteJChannel does not support this connect().");
    }
    @Override
    /**
     * send disconnect request to JChannel-server
     * @return RemoteJChannel
     */
    public synchronized JChannel disconnect(){
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try{
            String msg = "disconnect";
            this.msgList.add(msg);
        } finally {
            lock.unlock();
        }
        return this;
    }
    @Override
    /**
     * send disconnect request to JChannel-server
     *
     */
    public synchronized void close(){
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try{
            String msg = "disconnect";
            this.msgList.add(msg);
        } finally {
            lock.unlock();
        }

    }
    @Override
    /** Send a Message object to remote JChannel.
     *
     */
    public JChannel send(Message msg) throws Exception {
        if(msg == null)
            throw new NullPointerException("msg is null");
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try{
            this.msgList.add(msg);
        } finally {
            lock.unlock();
        }

        return this;
    }
    @Override
    /** Send an object to remote JChannel with an Address dest
     *
     */
    public JChannel send(Address dst, Object obj) throws Exception {
        Message msg=new ObjectMessage(dst, obj);
        return send(msg);
    }
    @Override
    /** Send a byte array to remote JChannel with an Address dest
     *
     */
    public JChannel send(Address dst, byte[] buf) throws Exception {
        return send(new BytesMessage(dst, buf));
    }
    @Override
    /** Send an byte array with offset and length to remote JChannel with an Address dest
     *
     */
    public JChannel send(Address dst, byte[] buf, int offset, int length) throws Exception {
        return send(new BytesMessage(dst, buf, offset, length));
    }

    @Override
    public JChannel getState(Address target, long timeout) throws Exception {
        throw new UnsupportedOperationException("RemoteJChannel does not support this method." +
                " Please use the getStateRJ(String target)");
    }

    @Override
    public JChannel getState(Address target, long timeout, boolean useFlushIfPresent) throws Exception {
        throw new UnsupportedOperationException("RemoteJChannel does not support this method." +
                " Please use the getStateRJ(String target)");
    }

    // change the cmd ?
    public JChannel getStateRJ(String target){
        if (this.msgList == null){
            throw new NullPointerException("The msgList is null.");
        }
        String cmd;
        if (target == null || target.equals("")){
            cmd = "getState() null";
        } else{
            cmd = "getState() " + target.trim();
        }
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try{
             // System.out.println("Give a getStateRJ() cmd to stub.");
            this.msgList.add(cmd);
        } finally {
            lock.unlock();
        }
        return this;
    }

    @Override
    public String toString(boolean details) {
        StringBuilder sb = new StringBuilder();
        sb.append("JChannel address=").append(this.jchannel_address).append('\n').append("cluster_name=").append(this.cluster).append('\n').append("my_view=").append(this.view.toString()).append('\n').append("state=").append(this.getState()).append('\n');
        if (details) {
            sb.append("discard_own_messages=").append(this.discard_own_messages).append('\n');
            sb.append("state_transfer_supported=").append("Not support").append('\n');
            sb.append("props=").append("Not support").append('\n');
            sb.append("grpc server address=").append(this.address).append('\n');
            sb.append("available grpc server addresses=").append(this.clientStub.serverList.toString()).append('\n');
        }

        return sb.toString();
    }

    @Override
    protected JChannel getState(Address target, long timeout, Callable<Boolean> flushInvoker) throws Exception {
        throw new UnsupportedOperationException("RemoteJChannel does not support this method.");
    }

    @Override
    protected JChannel checkClosed() {
        if (!this.down.get()) {
            throw new IllegalStateException("channel is closed");
        } else {
            return this;
        }
    }

    @Override
    protected JChannel checkClosedOrNotConnected() {
        if (!this.down.get()) {
            throw new IllegalStateException("channel is closed");
        } else {
            return this;
        }
    }
    @Override
    protected JChannel _close(boolean disconnect) {
        if (!this.down.get()) {
            return this;
        } else {
            if (disconnect) {
                this.disconnect();
            }
            return this;
        }
    }
    @Override
    protected Address determineCoordinator() {
        if (!isWork.get() && !down.get()){
            return this.remoteView.getCoord();
        } else {
            return null;
        }
    }
    protected Address determineClientCoordinator() {
        if (!isWork.get() && !down.get()){
            return this.view.getCoord();
        } else {
            return null;
        }
    }

}
