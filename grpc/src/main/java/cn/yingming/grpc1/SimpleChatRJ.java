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
                if (line.startsWith("print-address")){
                    System.out.println(remoteJChannel.jchannel_address);
                }
                if (line.startsWith("unicast")) {
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
