package cn.yingming.grpc1;

import com.google.protobuf.ByteString;
import io.grpc.jchannelRpc.*;
import org.jgroups.Address;
import org.jgroups.View;
import org.jgroups.ViewId;
import org.jgroups.util.ByteArrayDataOutputStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class ClusterMap implements Serializable {
    public ConcurrentHashMap<Address, String> map;
    public int viewNum;
    public Address creator;
    public ReentrantLock lock;
    // the members list with join order.
    public LinkedList orderList;
    // message history
    public LinkedList history;
    public ClusterMap(Address creator){
        this.map = new ConcurrentHashMap<>();
        this.viewNum = 0;
        this.creator = creator;
        this.lock = new ReentrantLock();
        this.orderList = new LinkedList<Address>();
        this.history = new LinkedList<MessageRep>();
    }
    public ClusterMap(){
        this.map = new ConcurrentHashMap<>();
        this.viewNum = 0;
        this.creator = null;
        this.lock = new ReentrantLock();
        this.orderList = new LinkedList<Address>();
        this.history = new LinkedList<MessageRep>();
    }
    public ConcurrentHashMap getMap(){
        return this.map;
    }

    public void removeClient(Address uuid){
        String target = this.map.get(uuid);
        int index = orderList.indexOf(target);
        if (index != -1){
            System.out.println("Remove the client from its cluster.");
            orderList.remove(index);
        } else{
            System.out.println("The client does not exist in the cluster.");
        }

    }
    public int getViewNum(){
        return viewNum;
    }
    public void addViewNum(){
        viewNum ++;
    }
    // change
    public Address getCreator(){
        if (this.orderList.size() > 0){
            return (Address) this.orderList.get(0);
        } else{
            return null;
        }
    }
    // generate a client view.
    public ViewRep generateView(){
        ViewRep view_rep = null;
        this.lock.lock();
        try{
            ViewId viewId = new ViewId(getCreator(), getViewNum());
            View view = new View(viewId, orderList);
            ByteArrayDataOutputStream vOutStream = new ByteArrayDataOutputStream();
            view.writeTo(vOutStream);
            byte[] v_byte = vOutStream.buffer();
            view_rep = ViewRep.newBuilder().setView(ByteString.copyFrom(v_byte)).build();
            addViewNum();
            System.out.println("[JChannel] Generate the current client view, " + view);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.lock.unlock();
        }
        return view_rep;
    }
    public LinkedList getList(){
        return this.orderList;
    }

    public void setFromView(View view){
        lock.lock();
        try{
            this.creator = view.getCoord();
            this.viewNum = (int) view.getViewId().getId();
            for (int i = 0; i < view.getMembers().size(); i++) {
                orderList.add(view.getMembersRaw()[i]);
            }
            System.out.println("[JChannel] Update the client view information, " + view);
        } finally {
            lock.unlock();
        }
    }
    public void addMember(Address address){
        lock.lock();
        try{
            if (!this.orderList.contains(address)){
                this.orderList.add(address);
            } else{
                System.out.println("The jchannel address of remote jchannel is existing in the cluster information." +
                        " Because it reconnects to this cluster.");
            }
        } finally {
            lock.unlock();
        }
    }
    public StateRep generateState(){
        StateRep rep;
        lock.lock();
        try{
            rep = StateRep.newBuilder()
                    .setSize(history.size())
                    .addAllOneOfHistory(history)
                    .build();
        } finally {
            lock.unlock();
        }
        return rep;
    }
    public void addHistory(MessageReq msg){
        MessageRep rep = MessageRep.newBuilder().setMessageObj(ByteString.copyFrom(msg.getMessageObj().toByteArray())).build();
        lock.lock();
        try{
            history.add(rep);
        } finally {
            lock.unlock();
        }
    }
}