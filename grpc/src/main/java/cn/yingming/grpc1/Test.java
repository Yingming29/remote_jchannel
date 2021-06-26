package cn.yingming.grpc1;

import com.google.protobuf.ByteString;
import io.grpc.jchannelRpc.MessageReq;
import io.grpc.jchannelRpc.UpdateNameCacheRep;
import org.jgroups.*;
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

    }
}
