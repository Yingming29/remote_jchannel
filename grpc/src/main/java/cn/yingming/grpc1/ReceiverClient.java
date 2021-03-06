package cn.yingming.grpc1;

import org.jgroups.*;
import org.jgroups.util.MessageBatch;
import org.jgroups.util.Util;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class ReceiverClient implements Receiver {
    final List<String> state;

    public ReceiverClient() {
        this.state = new LinkedList<>();
    }

    @Override
    public void receive(Message msg) {
        System.out.println("-------------");
        String line = null;
        if (msg instanceof EmptyMessage){
            line = msg.getSrc() + ": " + "EmptyMessage";
        } else if (msg instanceof CompositeMessage){
            CompositeMessage compMsg = (CompositeMessage) msg;
            LinkedList<String> compMsgList = new LinkedList<>();
            compMsg.forEach(eachOne -> compMsgList.add(eachOne.getObject()));
            line = msg.getSrc() + " (CompositeMessage): " + compMsgList;
            System.out.println(msg);
        } else if (msg instanceof BytesMessage){
            line = msg.getSrc() + " (BytesMessage): " + msg.getPayload();
            String result = new String((byte[]) msg.getPayload());
            System.out.println("verify bytes: " + result);
        } else if (msg instanceof NioMessage){
            NioMessage nioMsg = (NioMessage) msg;
            line = msg.getSrc() + "(NioMessage): " + msg.getPayload();
            CharBuffer charBuffer = null;
            ByteBuffer buffer = nioMsg.getPayload();
            String result_bb = null;
            try{
                Charset charSet = StandardCharsets.UTF_8;
                CharsetDecoder decoder = charSet.newDecoder();
                charBuffer = decoder.decode(buffer);
                buffer.flip();
                result_bb = charBuffer.toString();
            } catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("verify ByteBuffer: " + result_bb);
        } else if (msg instanceof LongMessage){
            line = msg.getSrc() + "(LongMessage): " + msg.getObject();
        } else {
            line = msg.getSrc() + "(ObjectMessage): " + msg.getPayload();
        }
        //System.out.println("Receiver" + Thread.currentThread().toString());
        System.out.println(line);
        synchronized (state){
            state.add(line);
        }
        System.out.println("-------------");
        /*
        String line = msg.getSrc() + ": " + msg.getPayload();
        System.out.println(line);
         */
    }

    @Override
    public void viewAccepted(View new_view) {
        System.out.println("** JChannel View (in the Client Receiver): " + new_view);
    }

    @Override
    public void receive(MessageBatch batch) {
        throw new UnsupportedOperationException("Not support.");
    }

    @Override
    public void getState(OutputStream output) throws Exception {
        throw new UnsupportedOperationException("Not support.");
    }

    @Override
    public void setState(InputStream input) throws Exception {
        ReentrantLock lock = new ReentrantLock();
        List<String> list;
        list = Util.objectFromStream(new DataInputStream(input));
        lock.lock();
        try {
            state.clear();
            state.addAll(list);
            System.out.println(list.size() + " messages in chat history.");
            list.forEach(System.out::println);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void block() {
        throw new UnsupportedOperationException("Not support.");
    }

    @Override
    public void unblock() {
        throw new UnsupportedOperationException("Not support.");
    }


}

