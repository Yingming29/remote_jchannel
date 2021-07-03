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
    public ReentrantLock lock;
    // the members list with join order.
    public LinkedList orderList;
    // message history
    // public LinkedList history;
    public ClusterMap(){
        this.map = new ConcurrentHashMap<>();
        this.viewNum = -1;
        this.lock = new ReentrantLock();
        this.orderList = new LinkedList<Address>();
        //this.history = new LinkedList<MessageReqRep>();
    }
    public ConcurrentHashMap getMap(){
        return this.map;
    }

    public void removeClient(Address uuid){
        System.out.println(this.map);
        this.orderList.remove(uuid);
        System.out.println("Remove the client from the client cluster, " + uuid);
    }
    public int getViewNum(){
        return viewNum;
    }
    public void addViewNum(){
        lock.lock();
        try{
            viewNum ++;
        } finally {
            lock.unlock();
        }
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
            System.out.println("[gRPC-Server] Generate the current client view, " + view);
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
        System.out.println("[JChannel-Server] Set the JChannel-Client view from the update response.");
        lock.lock();
        try{
            this.viewNum = (int) view.getViewId().getId();
            for (int i = 0; i < view.getMembers().size(); i++) {
                orderList.add(view.getMembersRaw()[i]);
                map.put(view.getMembersRaw()[i], view.getMembersRaw()[i].toString());
            }
            System.out.println("[JChannel-Server] Update the client view information, " + view);
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
                System.out.println("The address of JChannel-Client is existing in the cluster information." +
                        " Because it reconnects to this cluster.");
            }
        } finally {
            lock.unlock();
        }
    }
    /*
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

     */
    /*
    public void addHistory(MessageReqRep msg){
        lock.lock();
        try{
            history.add(msg);
        } finally {
            lock.unlock();
        }
    }

     */
}