package cn.yingming.grpc1;

import io.grpc.jchannelRpc.MessageReqRep;
import io.grpc.jchannelRpc.Response;
import io.grpc.jchannelRpc.StateRep;
import io.grpc.jchannelRpc.ViewRep;
import org.jgroups.Message;
import org.jgroups.Receiver;
import org.jgroups.View;
import org.jgroups.util.MessageBatch;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;

public class ReceiverRJ implements Receiver {
    final LinkedList<MessageReqRep> state;

    public ReceiverRJ() {
        this.state = new LinkedList<MessageReqRep>();
    }

    @Override
    public void receive(Message msg) {
        System.out.println(msg);
    }

    @Override
    public void viewAccepted(View new_view) {
        System.out.println("** Client View: " + new_view);
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
        throw new UnsupportedOperationException("Not support.");
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

