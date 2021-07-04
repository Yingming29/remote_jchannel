package cn.yingming.grpc1;

import com.google.protobuf.ByteString;
import io.grpc.jchannelRpc.SetDiscardOwnMsgReq;
import io.grpc.jchannelRpc.UpdateNameCacheRep;
import org.jgroups.*;
import org.jgroups.stack.Protocol;
import org.jgroups.util.*;
import org.jgroups.util.UUID;

import java.io.*;
import java.util.*;

public class Test {

    public static void main(String[] args) throws Exception {
        /*
        SubJChannel sj = new SubJChannel();
        Address a = sj.getAddressForNode();
        Address b = sj.getAddressForNode();
        Address c = sj.getAddressForNode();

        LinkedList list = new LinkedList<Address>();
        list.add(a);
        list.add(b);
        list.add(c);
        ViewId id = new ViewId(a, 2);

        View v = new View(id, list);
        String str_d = c.toString();
        Address d = UUID.fromString(str_d);
        System.out.println(list.contains(d));

         */
        /*
        System.out.println(v.getCoord());
        System.out.println(UUID.fromString(v.getCoord().toString()));
        System.out.println("----------");

        UUID u = UUID.randomUUID();
        Message msg = new ObjectMessage(u, "123");
        msg.setDest(u);
        msg.setSrc(u);
        ByteArrayDataOutputStream d2 = new ByteArrayDataOutputStream();
        msg.writeTo(d2);
        byte[] b2_byte = d2.buffer();
        System.out.println(b2_byte);
        ByteArrayDataInputStream in = new ByteArrayDataInputStream(b2_byte);
        Message omsg = new ObjectMessage();
        omsg.readFrom(in);
        System.out.println(omsg);

        ByteArrayDataOutputStream d3 = new ByteArrayDataOutputStream();
        u.writeTo(d3);
        byte[] b3_byte = d3.buffer();
        System.out.println(b3_byte);
        ByteArrayDataInputStream in3 = new ByteArrayDataInputStream(b3_byte);
        UUID u3 = new UUID();
        u3.readFrom(in3);
        System.out.println(u3);

        ByteArrayDataOutputStream vOutStream = new ByteArrayDataOutputStream();
        v.writeTo(vOutStream);
        byte[] v_byte = vOutStream.buffer();
        System.out.println(v_byte);
        ByteArrayDataInputStream v_in = new ByteArrayDataInputStream(v_byte);
        View new_view = new View();
        new_view.readFrom(v_in);
        System.out.println(new_view);

         */

        System.out.println("______________________________");


        Address add1 = UUID.randomUUID();
        Address add2 = UUID.randomUUID();
        Address add3 = UUID.randomUUID();

        System.out.println(add1.toString());
        System.out.println(add2.toString());
        System.out.println(add3.toString());

        NameCache.add(add1 , "add1 string");
        NameCache.add(add2 , "add2 string");
        NameCache.add(add3 , "add3 string");
        /*
        Address add1_compare = UUID.fromString(add1_str);
        if (add1.equals(add1_compare)){
            System.out.println("Address equal:? " + true);
        }

         */

        UpdateNameCacheRep.Builder builder = UpdateNameCacheRep.newBuilder();
        Map<Address, String> m = NameCache.getContents();


        for (Address oneAddress: m.keySet()) {
            ByteArrayDataOutputStream vOutStream = new ByteArrayDataOutputStream();
            if (oneAddress instanceof UUID){
                UUID u = (UUID) oneAddress;
                u.writeTo(vOutStream);
            } else{
                throw new ClassNotFoundException("It does not belong to UUID Address.");
            }
            byte[] v_byte = vOutStream.buffer();
            builder.addAddress(ByteString.copyFrom(v_byte));
            builder.addLogicalName(oneAddress.toString());
        }
        UpdateNameCacheRep rep = builder.build();
        System.out.println("Build rep." + rep);

        List l = rep.getAddressList();
        for (int i = 0; i < l.size(); i++) {
            ByteString bs = (ByteString) l.get(i);
            byte[] byte_address = bs.toByteArray();
            UUID u_test = new UUID();
            ByteArrayDataInputStream in3 = new ByteArrayDataInputStream(byte_address);
            u_test.readFrom(in3);
            System.out.println(u_test);
        }

        List l2 = rep.getLogicalNameList();
        System.out.println(l2);


        System.out.println("===============");
        Message msgtype = new BytesMessage();
        System.out.println(msgtype.getClass());
        ByteArrayDataOutputStream out  = new ByteArrayDataOutputStream();
        msgtype.writeTo(out);
        byte[] byte_msgtype = out.buffer();



        /**
         * Test for all types of Message
         */
        BytesMessage msg1 = new BytesMessage();
        ObjectMessage msg2 = new ObjectMessage();
        NioMessage msg3 = new NioMessage();
        EmptyMessage msg4 = new EmptyMessage();
        ObjectMessage msg5 = new ObjectMessage();
        LongMessage msg6 = new LongMessage();
        CompositeMessage msg7 = new CompositeMessage();
        FragmentedMessage msg8 =  new FragmentedMessage();

        if (msg2.getClass().toString().equals("class org.jgroups.BytesMessage")){
            System.out.println("true");
        }

        HashMap mp = new HashMap<String, Object>();

        SetDiscardOwnMsgReq discardMsgReq = SetDiscardOwnMsgReq.newBuilder()
                .setDiscard(false).setJchannalAddress("address String").build();
        System.out.println(discardMsgReq);

        System.out.println("================================");
        List<String> test_list = new LinkedList<String>();
        test_list.add("1");
        test_list.add("2");
        test_list.add("3");
        System.out.println("Print1: " + test_list);

        byte[] test_byte = Util.objectToByteBuffer(test_list);
        System.out.println(test_byte);

        List<String> test_list2 = (List<String>) Util.objectFromByteBuffer(test_byte);
        System.out.println(test_list2);


        System.out.println("=============");
        long long_num = 10000L;
        Message long_msg = new LongMessage(null, long_num);
        System.out.println(long_msg);
        ByteArrayDataOutputStream long_out = new ByteArrayDataOutputStream();
        long_msg.writeTo(long_out);
        byte[] long_byte = long_out.buffer();

        System.out.println("Convert");
        ByteArrayDataInputStream in = new ByteArrayDataInputStream(long_byte);
        Message new_long_msg = new LongMessage();
        new_long_msg.readFrom(in);
        System.out.println(new_long_msg);

        byte[] long_byte2 = Util.objectToByteBuffer(long_msg);
        System.out.println("Convert2");
        Message new_long_msg2 = Util.objectFromByteBuffer(long_byte2);
        System.out.println(new_long_msg2);
    }
}
