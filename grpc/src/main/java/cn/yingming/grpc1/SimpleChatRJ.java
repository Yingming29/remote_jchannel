package cn.yingming.grpc1;

import org.jgroups.Receiver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SimpleChatRJ {

    RemoteJChannel remoteJChannel;
    ReceiverRJ receiver;

    private void start() throws Exception{
        receiver = new ReceiverRJ();
        remoteJChannel = new RemoteJChannel("user3", "127.0.0.1:50051");
        remoteJChannel.setReceiverRJ(receiver);
        remoteJChannel.connect("ChatCluster");
        remoteJChannel.getStateRJ("JChannel-user1");
        eventLoop();
        remoteJChannel.close();
    }

    private void eventLoop(){
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            try{
                System.out.println(">");System.out.flush();
                String line = in.readLine().toLowerCase();
                if (line.startsWith("quit") || line.startsWith("exit")){
                    break;
                }
                // MessageRJ msg = new MessageRJ(line);
                remoteJChannel.send(line);
                System.out.println(line);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new SimpleChatRJ().start();
    }
}
