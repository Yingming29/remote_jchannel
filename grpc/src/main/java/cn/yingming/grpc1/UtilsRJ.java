package cn.yingming.grpc1;

import io.grpc.jchannelRpc.MessageReqRep;
import org.jgroups.*;
import org.jgroups.util.ByteArrayDataInputStream;

import java.io.*;

public class UtilsRJ {
    public static String getMsgType(Message msg){
        if (msg instanceof BytesMessage){
            return "BytesMessage";
        } else if (msg instanceof ObjectMessage){
            return "ObjectMessage";
        } else if (msg instanceof CompositeMessage){
            return "CompositeMessage";
        } else if (msg instanceof EmptyMessage){
            return "EmptyMessage";
        } else if (msg instanceof FragmentedMessage){
            return "FragmentedMessage";
        } else if (msg instanceof LongMessage){
            return "LongMessage";
        } else if (msg instanceof NioMessage){
            return "NioMessage";
        } else{
            return null;
        }
    }

    public static Message convertMessage(MessageReqRep req){
        String type = req.getType();
        ByteArrayDataInputStream in = new ByteArrayDataInputStream(req.getMessageObj().toByteArray());
        Message msg = null;
        try{
            if (type.equals("BytesMessage")){
                msg = new BytesMessage();
                msg.readFrom(in);
            } else if (type.equals("ObjectMessage")){
                msg = new ObjectMessage();
                msg.readFrom(in);
            } else if (type.equals("CompositeMessage")){
                msg = new CompositeMessage();
                msg.readFrom(in);
            } else if (type.equals("EmptyMessage")){
                msg = new EmptyMessage();
                msg.readFrom(in);
            } else if (type.equals("FragmentedMessage")){
                msg = new FragmentedMessage();
                msg.readFrom(in);
            } else if (type.equals("LongMessage")){
                msg = new LongMessage();
                msg.readFrom(in);
            } else if (type.equals("NioMessage")){
                msg = new NioMessage();
                msg.readFrom(in);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return msg;
    }



    // serialize object
    public static byte[] serializeObj(Object obj){
        ObjectOutputStream objStream = null;
        ByteArrayOutputStream bytesStream;

        bytesStream = new ByteArrayOutputStream();
        try{
            objStream = new ObjectOutputStream(bytesStream);
            objStream.writeObject(obj);
            byte[] bytes = bytesStream.toByteArray();
            System.out.println("Serialize successfully.");
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
    public static Object unserializeObj(byte[] bytes){
        ByteArrayInputStream bytesStream = null;
        try{
            bytesStream = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bytesStream);
            System.out.println("Unserialize successfully.");
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

}
