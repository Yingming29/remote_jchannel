package cn.yingming.grpc1;

import io.grpc.jchannelRpc.MessageRep;
import io.grpc.jchannelRpc.Request;
import io.grpc.jchannelRpc.Response;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class StatsRJ {

    LinkedList record;
    ReentrantLock lock;
    int view_size;
    HashSet senders;
    int total_receivedBytes;
    int total_sentBytes;
    public StatsRJ(){
        this.record = new LinkedList();
        this.lock = new ReentrantLock();
        this.view_size = 0;
        this.senders = new HashSet();
        this.total_receivedBytes = 0;
        this.total_sentBytes = 0;
    }
    // add message request or response record.
    public void addRecord(Object obj){
        if (obj instanceof Response){
            lock.lock();
            try{
                // add record, sender set, and total received bytes
                String str = "-- received 1 msg (" + ((Response) obj).toByteArray().length + " bytes)";
                this.record.add(str);
                MessageRep rep = (MessageRep) obj;
                this.senders.add(rep.getJchannelAddress());
                this.total_receivedBytes = this.total_receivedBytes + ((Response) obj).toByteArray().length;
            }finally {
                lock.unlock();
            }
        } else if (obj instanceof Request){
            lock.lock();
            try{
                String str = "-- sent 1 msg (" + ((Request) obj).toByteArray().length + " bytes)";
                this.record.add(str);
                this.total_sentBytes = this.total_sentBytes + ((Request) obj).toByteArray().length;
            }finally {
                lock.unlock();
            }
        }
    }

    // how many views the remote jchannel receive
    // change at receive view
    public void addViewSize(){
        lock.lock();
        try{
            this.view_size++;
        }finally {
            lock.unlock();
        }
    }

    public int getView_size() {
        return view_size;
    }

    public LinkedList getRecord() {
        return record;
    }

    public HashSet getSenders() {
        return senders;
    }

    @Override
    public String toString(){
        double average = 0.0;
        StringBuilder sb = new StringBuilder();
        sb.append("Received ").append(this.view_size).append(" views.").append("\n");
        sb.append("Number of sender: ").append(this.senders.size()).append("\n");
        if (this.record.size() != 0){
            for (int i = 0; i < this.record.size(); i++) {
                sb.append(this.record.get(i).toString()).append("\n");
            }
            average = (this.total_receivedBytes * 1.0) / (this.senders.size() * 1.0);
        }
        sb.append("Result:").append("\n");
        sb.append("Total received: ").append(this.total_receivedBytes).append(" bytes.").append("\n");
        sb.append("Total sent: ").append(this.total_sentBytes).append(" bytes.").append("\n");
        sb.append("Average node: ").append(average).append(" bytes").append("\n");
        return sb.toString();
    }
}
