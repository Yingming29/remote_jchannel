package cn.yingming.grpc1;

import org.jgroups.Address;
import org.jgroups.JChannel;

public class SubJChannel extends JChannel {
    public SubJChannel() throws Exception {

    }
    public Address getAddressForNode(){
        Address add = this.generateAddress();
        System.out.println(add);
        return add;
    }
    public static void main(String[] args) throws Exception {
        SubJChannel sj = new SubJChannel();
        sj.getAddressForNode();
    }

}
