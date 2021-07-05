package cn.yingming.grpc1;

import io.grpc.jchannelRpc.MessageReqRep;
import org.jgroups.*;
import org.jgroups.util.ByteArrayDataInputStream;
import org.jgroups.util.Util;

import java.io.*;

public class UtilsRJ {
    /*
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
    */

    //
    public static Message cloneMessage(Message msg, Address src, Address dest){
        if (msg instanceof BytesMessage){
            Message new_msg = new BytesMessage();
            new_msg.setSrc(src).setDest(dest).setPayload(msg.getPayload());
            return new_msg;
        } else if (msg instanceof ObjectMessage){
            Message new_msg = new ObjectMessage();
            new_msg.setSrc(src).setDest(dest).setPayload(msg.getPayload());
            return new_msg;
        } else if (msg instanceof CompositeMessage){
            Message new_msg = new CompositeMessage();
            new_msg.setSrc(src).setDest(dest).setPayload(msg.getPayload());
            return new_msg;
        } else if (msg instanceof EmptyMessage){
            Message new_msg = new EmptyMessage();
            new_msg.setSrc(src).setDest(dest).setPayload(msg.getPayload());
            return new_msg;
        } else if (msg instanceof FragmentedMessage){
            Message new_msg = new FragmentedMessage();
            new_msg.setSrc(src).setDest(dest).setPayload(msg.getPayload());
            return new_msg;
        } else if (msg instanceof LongMessage){
            Message new_msg = new LongMessage();
            new_msg.setSrc(src).setDest(dest).setPayload(msg.getPayload());
            return new_msg;
        } else if (msg instanceof NioMessage){
            Message new_msg = new NioMessage();
            new_msg.setSrc(src).setDest(dest).setPayload(msg.getPayload());
            return new_msg;
        } else{
            return null;
        }
    }

    public static Message convertMessage(MessageReqRep req){
        int type = req.getType();
        ByteArrayDataInputStream in = new ByteArrayDataInputStream(req.getMessageObj().toByteArray());
        Message msg = null;
        try{

            if (type == 0){
                msg = new BytesMessage();
                msg.readFrom(in);
            } else if (type == 1){
                msg = new NioMessage();
                msg.readFrom(in);
            } else if (type == 2){
                msg = new EmptyMessage();
                msg.readFrom(in);
            } else if (type == 3){
                msg = new ObjectMessage();
                msg.readFrom(in);
            } else if (type == 4){
                return Util.objectFromByteBuffer(req.getMessageObj().toByteArray());
            } else if (type == 5){
                msg = new CompositeMessage();
                msg.readFrom(in);
            }  else if (type == 6){
                throw new UnsupportedOperationException("Not support Fragmented Message.");
            }  else {
                throw new ClassNotFoundException("Invalid Message type.");
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
