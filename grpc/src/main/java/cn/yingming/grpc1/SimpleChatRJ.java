package cn.yingming.grpc1;

import org.jgroups.*;
import org.jgroups.util.NameCache;
import org.jgroups.util.UUID;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class SimpleChatRJ {

    JChannelClient remoteJChannel;
    ReceiverRJ receiver;

    private void start(String server_address, String cluster) throws Exception{

        receiver = new ReceiverRJ();
        remoteJChannel = new JChannelClient(server_address);
        remoteJChannel.setReceiver(receiver);
        //remoteJChannel.setDiscardOwnMessages(true);
        //remoteJChannel.setStats(true);
        remoteJChannel.connect(cluster);
        //remoteJChannel.getStateRJ(state_target);
        eventLoop();
        remoteJChannel.close();
    }

    private void eventLoop(){
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        while (true){
            try{
                System.out.println(">");System.out.flush();
                String line = in.readLine();
                if (line.startsWith("quit") || line.startsWith("exit")){
                    break;
                }
                if (line.equals("testSome")){
                    System.out.println("Test some remote grpc methods:");
                    System.out.println("getAddress() for JChannel-Server Address:" + remoteJChannel.getAddress());
                    System.out.println("getAddressAsString() for JChannel-Server Address:" + remoteJChannel.getAddressAsString());
                    System.out.println("getAddressAsUUID() for JChannel-Server Address:" + remoteJChannel.getAddressAsUUID());
                    System.out.println("getName() for JChannel-Server Name:" + remoteJChannel.getName());
                } else if (line.startsWith("getLocalAddress()")){
                    System.out.println("Client Address:" + remoteJChannel.getLocalAddress());
                } else if(line.startsWith("getRemoteAddress()")){
                    System.out.println("Real JChannel Address (JChannel Server):" + remoteJChannel.getAddress());
                } else if(line.startsWith("getAddressAsString()")){
                    System.out.println("getAddressAsString() for Real JChannel Address:" + remoteJChannel.getAddressAsString());
                } else if(line.startsWith("getAddressAsUUID()")){
                    System.out.println("getAddressAsUUID() for Real JChannel Address:" + remoteJChannel.getAddressAsUUID());
                } else if(line.startsWith("getName()")){
                    System.out.println("getName() for Real JChannel Address (JChannel Server):" + remoteJChannel.getName());
                } else if(line.startsWith("name()")){
                    System.out.println("getName() for Real JChannel Address (JChannel Server):" + remoteJChannel.getName());
                } else if(line.startsWith("getClusterName()") || line.startsWith("clusterName()")){
                    System.out.println("getClusterName() for Real JChannel Address (JChannel Server):" + remoteJChannel.getClusterName());
                } else if(line.startsWith("getStats()")){
                    System.out.println("getStats() for Real JChannel Address (JChannel Server):" + remoteJChannel.getStats());
                } else if(line.startsWith("printProtocolSpec()")){
                    String[] strs = line.split(" ", 2);
                    if(strs[1].equals("true")){
                        System.out.println("printProtocolSpec(true) for Real JChannel Address (JChannel Server):" + remoteJChannel.printProtocolSpec(true));
                    } else if (strs[1].equals("false")){
                        remoteJChannel.remoteProtocolStack_string = remoteJChannel.printProtocolSpec(false);
                        System.out.println("printProtocolSpec(false) for Real JChannel Address (JChannel Server):" + remoteJChannel.printProtocolSpec(false));
                    }
                } else if(line.startsWith("setStats()")){
                    String[] strs = line.split(" ");
                    if (strs[1].equals("true")){
                        System.out.println("setStats(true) for Real JChannel Address (JChannel Server):" + remoteJChannel.setStats(true));
                    } else if (strs[1].equals("false")){
                        System.out.println("setStats(false) for Real JChannel Address (JChannel Server):" + remoteJChannel.setStats(false));
                    }
                } else if (line.startsWith("dumpStats()")){
                    String[] strs = line.split(" ");
                    if (strs.length == 1){
                        System.out.println("dumpStats() for real JChannel Address (JChannel Server):" + remoteJChannel.dumpStats());
                    } else if (strs.length == 2){
                        System.out.println("dumpStats(Protocol_name) for real JChannel (JChannel Server):"+remoteJChannel.dumpStats(strs[1]));
                    } else if (strs.length > 2){
                        ArrayList attr_list = new ArrayList<String>();
                        for (int i = 2; i < strs.length; i++) {
                            attr_list.add(strs[i]);
                        }
                        System.out.println("dumpStats(Protocol_name, attrs) for real JChannel (JChannel Server):" + remoteJChannel.dumpStats(strs[1], attr_list));
                    }
                } else if(line.startsWith("getDiscardOwnMessage()")){
                    System.out.println("getDiscardOwnMessage() for real JChannel Address (JChannel Server):" + remoteJChannel.getDiscardOwnMessages());
                } else if(line.startsWith("setDiscardOwnMessage()")){
                    String[] strs = line.split(" ");
                    if (strs[1].equals("true")){
                        System.out.println("setDiscardOwnMessage(true) for real JChannel Address (JChannel Server):" + remoteJChannel.setDiscardOwnMessages(true));
                    } else {
                        System.out.println("setDiscardOwnMessage(false) for real JChannel Address (JChannel Server):" + remoteJChannel.setDiscardOwnMessages(false));
                    }
                } else if (line.startsWith("unicast")) {
                    // example: unicast content server/client 1
                    // unicast content client 1
                    String[] strs = line.split(" ");
                    int index = Integer.parseInt(strs[3]);
                    if (strs[2].equals("server")){
                        try{
                            remoteJChannel.send(this.remoteJChannel.remoteView.getMembers().get(index), strs[1]);
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    } else if (strs[2].equals("client")){
                        try{
                            // change
                            System.out.println(this.remoteJChannel.view);
                            remoteJChannel.send(this.remoteJChannel.view.getMembers().get(index), strs[1]);
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                } else if (line.startsWith("byte")){
                    byte[] buf = line.getBytes();
                    try{
                        remoteJChannel.send(null, buf);
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                } else if (line.startsWith("tryNameche")){
                    System.out.println(NameCache.printCache());
                    System.out.println(NameCache.get(this.remoteJChannel.real_jchannel_address));
                } else if(line.startsWith("getProperties()")){
                    System.out.println("getProperties() for Real JChannel Address (JChannel Server):" + remoteJChannel.getProperties());
                } else if (line.startsWith("disconnect")) {
                    System.out.println("Disconnect" + this.remoteJChannel.disconnect());
                } else if (line.equals("getState()")){
                    System.out.println("getState() for remote real JChannel: " + remoteJChannel.getState());
                } else if (line.equals("isOpen()")){
                    System.out.println("isOpen() for remote real JChannel: " + remoteJChannel.isOpen());
                } else if (line.equals("isConnecting()")){
                    System.out.println("isConnecting() for remote real JChannel: " + remoteJChannel.isConnecting());
                } else if (line.equals("isConnected()")){
                    System.out.println("isConnected() for remote real JChannel: " + remoteJChannel.isConnected());
                } else if (line.equals("isClosed()")){
                    System.out.println("isClosed() for remote real JChannel: " + remoteJChannel.isClosed());
                } else if (line.startsWith("getState() history")){
                    // getState() history 1 1000
                    String[] strs = line.split(" ");
                    Address target = null;
                    if (strs[2].equals("null")){
                        target = null;
                    } else {
                        int index = Integer.parseInt(strs[2]);
                        target = remoteJChannel.remoteView.getMembers().get(index);
                    }
                    long timeout = Long.parseLong(strs[3]);

                    try {
                        System.out.println("getState() for remote real JChannel: " + remoteJChannel.getState(target, timeout));
                        System.out.println("getState() target:" + target + ", timeout: " + timeout);
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                } else if (line.startsWith("msg0")){
                    Message msg0 = new BytesMessage(null, "byte".getBytes());
                    System.out.println("Message Test:" + msg0);
                    remoteJChannel.send(msg0);
                } else if (line.startsWith("msg1")){
                    ByteBuffer bb = ByteBuffer.wrap("byte".getBytes());
                    Message msg1 = new NioMessage(null, bb);
                    System.out.println("Message Test:" + msg1);
                    remoteJChannel.send(msg1);
                } else if (line.startsWith("msg2")){
                    Message msg2 = new EmptyMessage(null);
                    System.out.println("Message Test:" + msg2);
                    remoteJChannel.send(msg2);
                } else if (line.startsWith("msg3")){
                    String obj = "msg3 Object";
                    Message msg3 = new ObjectMessage(null, obj);
                    System.out.println("Message Test:" + msg3);
                    remoteJChannel.send(msg3);
                } else if (line.startsWith("msg4")){
                    long long_num = 100000L;
                    Message msg4 = new LongMessage(null, long_num);
                    System.out.println("Message Test:" + msg4);
                    remoteJChannel.send(msg4);
                    System.out.println(msg4);
                } else if (line.startsWith("msg5")){
                    Message subMsg1 = new ObjectMessage(null, "subMessage");
                    Message subMsg2 = new ObjectMessage(null, "subMessage");
                    Message subMsg3 = new ObjectMessage(null, "subMessage");
                    Message msg5 = new CompositeMessage(null, subMsg1, subMsg2, subMsg3);
                    System.out.println("Message Test:" + msg5);
                    remoteJChannel.send(msg5);
                } else{
                    try{
                        remoteJChannel.send(null, line);
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new SimpleChatRJ().start(args[0], args[1]);
    }
}
