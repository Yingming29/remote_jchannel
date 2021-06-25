package cn.yingming.grpc1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SimpleChatRJ {

    RemoteJChannel remoteJChannel;
    ReceiverRJ receiver;

    private void start(String name, String server_address, String cluster, String state_target) throws Exception{

        receiver = new ReceiverRJ();
        remoteJChannel = new RemoteJChannel(name, server_address);
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
                    String[] strs = line.split(" ");
                    remoteJChannel.send(strs[1], strs[2]);
                } else if (line.startsWith("byte")){
                    byte[] buf = line.getBytes();
                    remoteJChannel.send(buf);
                } else{
                    // MessageRJ msg = new MessageRJ(line);
                    remoteJChannel.send(line);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new SimpleChatRJ().start(args[0], args[1], args[2], args[3]);
    }
}
