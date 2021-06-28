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
import java.util.List;

public class ReceiverRJ implements Receiver {
    final LinkedList<MessageReqRep> state;
    public ReceiverRJ(){
        this.state = new LinkedList<MessageReqRep>();
    }
    public void receiveRJ(Message msg) {
        System.out.println(msg);
    }

    public void viewAcceptedRJ(View new_view) {
        System.out.println("** Client View: " + new_view);

    }

    public LinkedList getStateRJ(){
        synchronized (state){
            return this.state;
        }
    }

    public void setStateRJ(List new_states){
        /*
        synchronized (this.state){
            this.state.clear();
            this.state.addAll(new_states);
        }
        System.out.println(this.state.size() + " messages in chat history.");
        for (int i = 0; i < this.state.size(); i++) {
            MessageRep msg = (MessageRep) this.state.get(i);
            if (!msg.getContent().equals("")){
                System.out.println(msg.getJchannelAddress() + ": " + msg.getContent());
            } else{
                System.out.println(msg.getJchannelAddress() + ": " + msg.getContentByte());
            }
        }

         */
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
