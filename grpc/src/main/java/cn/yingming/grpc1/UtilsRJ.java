package cn.yingming.grpc1;

import io.grpc.jchannelRpc.MessageReqRep;
import org.jgroups.*;
import org.jgroups.util.ByteArrayDataInputStream;
import org.jgroups.util.Util;

import java.io.*;
import java.util.concurrent.ExecutionException;

public class UtilsRJ {

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
                /*
                try{
                    msg = new LongMessage();
                    msg.readFrom(in);
                    // System.out.println("here" + msg);
                } catch (Exception e) {
                    e.printStackTrace();
                    return Util.objectFromByteBuffer(req.getMessageObj().toByteArray());
                }
                 */
                Message mid = null;
                try {
                    mid = Util.objectFromByteBuffer(req.getMessageObj().toByteArray());
                } catch (Exception e){
                    // e.printStackTrace();
                    mid = new LongMessage();
                    mid.readFrom(in);
                }
                msg = new LongMessage(mid.getDest(), mid.getObject());
                msg.setSrc(mid.src());
                return msg;
                /*
                if (mid != null){
                    msg = new LongMessage(mid.getDest(), mid.getObject());
                    msg.setSrc(mid.src());
                    System.out.println("mid:" + msg);
                    return msg;
                } else {
                    throw new ClassNotFoundException("LongMessage error.");
                }

                 */
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
