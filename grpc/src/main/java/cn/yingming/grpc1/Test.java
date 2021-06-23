package cn.yingming.grpc1;

import org.jgroups.*;
import org.jgroups.util.Base64;
import org.jgroups.util.ByteArrayDataInputStream;
import org.jgroups.util.ByteArrayDataOutputStream;
import org.jgroups.util.UUID;

import java.io.*;
import java.util.LinkedList;

public class Test {

    public static void main(String[] args) throws Exception {

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

    }
}
