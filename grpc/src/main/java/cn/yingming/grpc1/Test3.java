package cn.yingming.grpc1;

import com.google.protobuf.ByteString;
import io.grpc.jchannelRpc.MessageReq;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Test3 implements Serializable {
    public int a;
    public Test3(){
        a = 1;
    }

    public void method(){
        try{
            throw new IllegalStateException("123");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        ArrayList l = new ArrayList();
        int a = 1;
        String b = "2";
        MessageRJ c = new MessageRJ();
        byte[] d = "asdas".getBytes(StandardCharsets.UTF_8);
        l.add(a);
        l.add(b);
        l.add(c);
        l.add(d);
        for (int i = 0; i < l.size(); i++) {
            //System.out.println(l.get(i).getClass());
        }
        Test3 t3 = new Test3();
        byte[] bys = UtilsRJ.serializeObj(t3);
        ByteString bs = ByteString.copyFrom(bys);

        MessageReq msgReq1 = MessageReq.newBuilder()
                .setSource("uuid")
                .setJchannelAddress("jchannaddr")
                .setContentByte(bs)
                .setTimestamp("time")
                .setDestination("dst")
                .build();

        System.out.println(msgReq1);
        if (msgReq1.getCluster().equals("")){
            System.out.println(11);
        }
        ByteString bs2 = msgReq1.getContentByte();
        byte[] by2 = bs2.toByteArray();
        System.out.println(by2.toString());

        Object obj = UtilsRJ.unserializeObj(by2);
        System.out.println(obj.getClass());
        Test3 x = (Test3) obj;
        System.out.println(x.a);
    }
}
