package cn.yingming.grpc1;

import io.grpc.jchannelRpc.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class ClusterMap implements Serializable {
    public ConcurrentHashMap<String, String> map;
    public int viewNum;
    public String creator;
    public ReentrantLock lock;
    // the members list with join order.
    public ArrayList orderList;
    // message history
    public LinkedList history;
    public ClusterMap(String creator){
        this.map = new ConcurrentHashMap<String, String>();
        this.viewNum = 0;
        this.creator = creator;
        this.lock = new ReentrantLock();
        this.orderList = new ArrayList<String>();
        this.history = new LinkedList<MessageRep>();
    }
    public ConcurrentHashMap getMap(){
        return this.map;
    }

    public void removeClient(String uuid){
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
    public String getCreator(){ return (String) this.orderList.get(0);}
    public ViewRep generateView(){
        ViewRep rep;
        this.lock.lock();
        try{
            rep = ViewRep.newBuilder()
                    .setCreator(getCreator())
                    .setViewNum(getViewNum())
                    .setSize(this.orderList.size())
                    .addAllOneAddress(this.orderList)
                    .build();
            addViewNum();
        } finally {
            this.lock.unlock();
        }
        return rep;
    }
    public ArrayList getList(){
        return this.orderList;
    }

    public void addMember(String address){
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
        MessageRep rep = null;
        if (msg.getContent().equals("")){
            rep = MessageRep.newBuilder()
                    .setJchannelAddress(msg.getJchannelAddress())
                    .setContentByte(msg.getContentByte())
                    .build();
            System.out.println("add history for byte msg.");
        } else{
            rep = MessageRep.newBuilder()
                    .setJchannelAddress(msg.getJchannelAddress())
                    .setContent(msg.getContent())
                    .build();
            System.out.println("add history for string msg.");
        }
        lock.lock();
        try{
            history.add(rep);
        } finally {
            lock.unlock();
        }
    }
}