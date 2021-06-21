package cn.yingming.grpc1;

import io.grpc.jchannelRpc.MessageRep;
import io.grpc.jchannelRpc.Response;
import io.grpc.jchannelRpc.ViewRep;
import org.jgroups.Message;
import org.jgroups.Receiver;
import org.jgroups.View;
import org.jgroups.util.MessageBatch;
import org.jgroups.util.MessageIterator;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

public class ReceiverRJ implements Receiver {
    LinkedList state;

    public ReceiverRJ(){
        this.state = new LinkedList();
    }
    public void receiveRJ(MessageRep msg) {
        throw new UnsupportedOperationException("Not support.");
    }

    public void viewAcceptedRJ(ViewRep view) {
        throw new UnsupportedOperationException("Not support.");
    }

    public LinkedList getStateRJ() throws Exception {
        synchronized (state){
            return this.state;
        }
    }

    public void setStateRJ() throws Exception {
        throw new UnsupportedOperationException("Not support.");
    }



    @Override
    public void receive(Message msg) {
        throw new UnsupportedOperationException("Not support.");
    }

    @Override
    public void receive(MessageBatch batch) {
        throw new UnsupportedOperationException("Not support.");
    }

    @Override
    public void viewAccepted(View new_view) {
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

    @Override
    public void getState(OutputStream output) throws Exception {
        throw new UnsupportedOperationException("Not support.");
    }

    @Override
    public void setState(InputStream input) throws Exception {
        throw new UnsupportedOperationException("Not support.");
    }
}
