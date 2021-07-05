package cn.yingming.grpc1;

import org.jgroups.*;
import org.jgroups.util.MessageBatch;
import org.jgroups.util.Util;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class ReceiverRJ implements Receiver {
    final List<String> state;

    public ReceiverRJ() {
        this.state = new LinkedList<>();
    }

    @Override
    public void receive(Message msg) {
        System.out.println(msg);
        if (msg instanceof EmptyMessage){
            System.out.println("EmptyMessage");
        } else if (msg instanceof CompositeMessage){
            CompositeMessage compMsg = (CompositeMessage) msg;
            System.out.println("CompositeMessage");
            compMsg.forEach(System.out::println);
        } else if (msg instanceof BytesMessage){
            System.out.println("BytesMessage");
            System.out.println(msg.getPayload().toString());
        }
        synchronized (state){
            state.add(msg.toString());
        }
        /*
        String line = msg.getSrc() + ": " + msg.getPayload();
        System.out.println(line);
         */
    }

    @Override
    public void viewAccepted(View new_view) {
        System.out.println("** JChannel-Server View (on the Client-Receiver): " + new_view);
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

