package cn.yingming.grpc1;

import org.jgroups.util.NameCache;
import org.jgroups.util.UUID;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SimpleChatRJ {

    RemoteJChannel remoteJChannel;
    ReceiverRJ receiver;

    private void start(String server_address, String cluster) throws Exception{

        receiver = new ReceiverRJ();
        remoteJChannel = new RemoteJChannel(server_address);
        remoteJChannel.setReceiverRJ(receiver);
        //remoteJChannel.setDiscardOwnMessages(true);
        //remoteJChannel.setStats(true);
        remoteJChannel.connect(cluster);
        //remoteJChannel.getStateRJ(state_target);
        eventLoop();
        //remoteJChannel.close();
        //System.out.println(remoteJChannel.remoteJChannelDumpStats());
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
                if (line.startsWith("getLocalAddress()")){
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
                } else if(line.startsWith("printProtocolSpec()")){
                    String[] strs = line.split(" ", 2);
                    if(strs[1].equals("true")){
                        System.out.println("printProtocolSpec(true) for Real JChannel Address (JChannel Server):" + remoteJChannel.printProtocolSpec(true));
                    } else if (strs[1].equals("false")){
                        remoteJChannel.remoteProtocolStack_string = remoteJChannel.printProtocolSpec(false);
                        System.out.println("printProtocolSpec(false) for Real JChannel Address (JChannel Server):" + remoteJChannel.printProtocolSpec(false));
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
                } else if (line.startsWith("disconnect")) {
                    System.out.println("Disconnect" + this.remoteJChannel.disconnect());
                } else{
                    try{
                        remoteJChannel.send(null, line);
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new SimpleChatRJ().start(args[0], args[1]);
    }
}
