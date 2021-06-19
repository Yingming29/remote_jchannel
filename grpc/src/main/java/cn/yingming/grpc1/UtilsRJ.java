package cn.yingming.grpc1;

import io.grpc.jchannelRpc.DisconnectReq;
import io.grpc.jchannelRpc.MessageReq;
import io.grpc.jchannelRpc.Request;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilsRJ {

    // serialize object for cluster information
    public static byte[] serializeClusterInf(Object obj){
        ObjectOutputStream objStream = null;
        ByteArrayOutputStream bytesStream = null;

        bytesStream = new ByteArrayOutputStream();
        try{
            objStream = new ObjectOutputStream(bytesStream);
            objStream.writeObject(obj);
            byte[] bytes = bytesStream.toByteArray();
            System.out.println("Serialize the cluster inf successfully.");
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                objStream.close();
                bytesStream.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }
    // unserialize object for cluster information
    public static Object unserializeClusterInf(byte[] bytes){
        ByteArrayInputStream bytesStream = null;
        try{
            bytesStream = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bytesStream);
            // System.out.println("Unserialize");
            return ois.readObject();
        } catch (IOException e){
            // e.printStackTrace();
        } catch (ClassNotFoundException e){
            // e.printStackTrace();
        } finally {
            try{
                bytesStream.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Request judgeRequest(Object obj, RemoteJChannelStub stub) {
        Date d = new Date();
        SimpleDateFormat dft = new SimpleDateFormat("hh:mm:ss");
        if (obj instanceof String){
            String input = (String) obj;
            // single send request
            if (input.startsWith("TO")){
                String[] strs = input.split(" ", 3);
                if (strs.length == 3){
                    // set up time for msg, and build message
                    MessageReq msgReq = MessageReq.newBuilder()
                            .setSource(stub.client.uuid)
                            .setJchannelAddress(stub.client.jchannel_address)
                            .setCluster(stub.client.cluster)
                            .setContent(strs[2])
                            .setTimestamp(dft.format(d))
                            .setDestination(strs[1])
                            .build();
                    Request req = Request.newBuilder().setMessageRequest(msgReq).build();
                    return req;
                } else{
                    // common message for broadcast to its cluster.
                    MessageReq msgReq = MessageReq.newBuilder()
                            .setSource(this.client.uuid)
                            .setJchannelAddress(this.client.jchannel_address)
                            .setCluster(this.client.cluster)
                            .setContent(input)
                            .setTimestamp(dft.format(d))
                            .build();
                    Request req = Request.newBuilder().setMessageRequest(msgReq).build();
                    return req;
                }
            } else if (input.equals("disconnect")) {
                // disconnect request
                DisconnectReq msgReq = DisconnectReq.newBuilder()
                        .setSource(this.client.uuid)
                        .setJchannelAddress(this.client.jchannel_address)
                        .setCluster(this.client.cluster)
                        .setTimestamp(dft.format(d))
                        .build();
                Request req = Request.newBuilder()
                        .setDisconnectRequest(msgReq).build();
                return req;

            } else{
                // common message for broadcast to its cluster.
                MessageReq msgReq = MessageReq.newBuilder()
                        .setSource(this.client.uuid)
                        .setJchannelAddress(this.client.jchannel_address)
                        .setCluster(this.client.cluster)
                        .setContent(input)
                        .setTimestamp(dft.format(d))
                        .build();
                Request req = Request.newBuilder().setMessageRequest(msgReq).build();
                return req;
            }
        }
        else if(obj instanceof MessageRJ){
            MessageRJ msg = (MessageRJ) obj;
            boolean checkResult = msg.check();
            if (!checkResult){
                try{
                    throw new IllegalArgumentException("The MessageRJ has both of buf and msg property.");
                } catch (Exception e){
                    e.printStackTrace();
                }
                if (msg.getDst() != null){
                    MessageReq msgReq = MessageReq.newBuilder()
                            .setSource(this.client.uuid)
                            .setJchannelAddress(this.client.jchannel_address)
                            .setCluster(this.client.cluster)
                            .setContent(msg.getMsg())
                            .setTimestamp(dft.format(d))
                            .setDestination(msg.getDst())
                            .build();
                    Request req = Request.newBuilder().setMessageRequest(msgReq).build();
                    return req;
                } else{
                    MessageReq msgReq = MessageReq.newBuilder()
                            .setSource(this.client.uuid)
                            .setJchannelAddress(this.client.jchannel_address)
                            .setCluster(this.client.cluster)
                            .setContent(msg.getMsg())
                            .setTimestamp(dft.format(d))
                            .build();
                    Request req = Request.newBuilder().setMessageRequest(msgReq).build();
                    return req;
                }

            } else{
                if (msg.getDst() != null){
                    MessageReq msgReq = MessageReq.newBuilder()
                            .setSource(this.client.uuid)
                            .setJchannelAddress(this.client.jchannel_address)
                            .setCluster(this.client.cluster)
                            .setContent(msg.getMsg())
                            .setTimestamp(dft.format(d))
                            .setDestination(msg.getDst())
                            .build();
                    Request req = Request.newBuilder().setMessageRequest(msgReq).build();
                    return req;
                } else{
                    MessageReq msgReq = MessageReq.newBuilder()
                            .setSource(this.client.uuid)
                            .setJchannelAddress(this.client.jchannel_address)
                            .setCluster(this.client.cluster)
                            .setContent(msg.getMsg())
                            .setTimestamp(dft.format(d))
                            .build();
                    Request req = Request.newBuilder().setMessageRequest(msgReq).build();
                    return req;
                }

            }
        }

    }

}
