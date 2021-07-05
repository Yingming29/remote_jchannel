package cn.yingming.grpc1;

import java.io.*;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import io.grpc.jchannelRpc.ChannelMsg;
import org.jgroups.*;
import org.jgroups.stack.ProtocolStack;
import org.jgroups.util.*;


public class SimpleChat implements Receiver{

	JChannel channel;
	final List<String> state = new LinkedList<String>();

	private void start() throws Exception {
		channel = new JChannel();
		channel.setReceiver(this).connect("NodeCluster");
		channel.getState(null, 1000);
		eventLoop();
		channel.close();
	}
	@Override
	public void viewAccepted(View new_view) {
		System.out.println("** view: " + new_view);
	}
	@Override
	public void receive(Message msg) {
		String line = null;
		if (msg instanceof EmptyMessage){
			line = msg.getSrc() + ": " + "EmptyMessage";
		} else if (msg instanceof CompositeMessage){
			CompositeMessage compMsg = (CompositeMessage) msg;
			LinkedList<String> compMsgList = new LinkedList<>();
			compMsg.forEach(eachOne -> compMsgList.add(eachOne.getObject()));
			line = msg.getSrc() + " (CompositeMessage): " + compMsgList;
		} else if (msg instanceof BytesMessage){
			line = msg.getSrc() + " (BytesMessage): " + msg.getPayload();
			String result = new String((byte[]) msg.getPayload());
			System.out.println("try convert bytes: " + result);
		} else if (msg instanceof NioMessage){
			NioMessage nioMsg = (NioMessage) msg;
			line = msg.getSrc() + "(NioMessage): " + msg.getPayload();
			ByteArrayDataOutputStream out = new ByteArrayDataOutputStream();
			byte[] b = null;
			try {
				nioMsg.writePayload(out);
				b = out.buffer();
			} catch (IOException e) {
				e.printStackTrace();
			}
			String result = new String(b);
			System.out.println("try convert ByteBuffer: " + result);
		} else {
			line = msg.getSrc() + "(ObjectMessage): " + msg.getPayload();
		}
		System.out.println(line);
		synchronized (state){
			state.add(line);
		}
		/*
		if (msg.getObject() instanceof ChannelMsg){
			System.out.println("Receive a message for grpc address, drop it.");
		} else {
			String line = msg.getSrc() + ": " + msg.getPayload();
			System.out.println(line);
			synchronized (state){
				state.add(line);
			}
		}

		 */
	}
	@Override
	public void getState(OutputStream output) throws Exception{
		System.out.println("Provide a state.");
		synchronized (state){
			Util.objectToStream(state, new DataOutputStream(output));
		}
	}
	@Override
	public void setState(InputStream input) throws Exception{
		System.out.println("Receive a state.");
		List<String> list;
		list = Util.objectFromStream(new DataInputStream(input));
		synchronized (state){
			state.clear();
			state.addAll(list);
		}
		System.out.println(list.size() + " messages in chat history.");
		list.forEach(System.out::println);
	}


	private void eventLoop() {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			try {
				System.out.println(">");
				// Before readLine(), clear the in stream.
				System.out.flush();
				String line = in.readLine().toLowerCase();
				if (line.startsWith("quit") || line.startsWith("exit")) {
					break;
				}

				// Different input for demo
				if (line.startsWith("to")){
					String[] strs = line.split(" ");
					int target_index = Integer.parseInt(strs[1]);
					Address target = channel.getView().getMembers().get(target_index);
					Message msg = new ObjectMessage(target, line);
					System.out.println("Message Test:" + msg);
					channel.send(msg);
				} else if (line.startsWith("msg0")){
					Message msg0 = new BytesMessage(null, "byte".getBytes());
					System.out.println("Message Test:" + msg0);
					channel.send(msg0);
				} else if (line.startsWith("msg1")){
					ByteBuffer bb = ByteBuffer.wrap("byte".getBytes());
					Message msg1 = new NioMessage(null, bb);
					System.out.println("Message Test:" + msg1);
					channel.send(msg1);
				} else if (line.startsWith("msg2")){
					Message msg2 = new EmptyMessage(null);
					System.out.println("Message Test:" + msg2);
					channel.send(msg2);
				} else if (line.startsWith("msg3")){
					String obj = "msg3 Object";
					Message msg3 = new ObjectMessage(null, obj);
					System.out.println("Message Test:" + msg3);
					channel.send(msg3);
				} else if (line.startsWith("msg4")){
					long long_num = 100000L;
					Message msg4 = new LongMessage(null, long_num);
					System.out.println("Message Test:" + msg4);
					channel.send(msg4);
				} else if (line.startsWith("msg5")){
					Message subMsg1 = new ObjectMessage(null, "subMessage");
					Message subMsg2 = new ObjectMessage(null, "subMessage");
					Message subMsg3 = new ObjectMessage(null, "subMessage");
					Message msg5 = new CompositeMessage(null, subMsg1, subMsg2, subMsg3);
					System.out.println("Message Test:" + msg5);
					channel.send(msg5);
				} else if (line.startsWith("msg6")){
					Message original_msg = new ObjectMessage(null, "originalMessageStringObj");
					Message msg6 = new FragmentedMessage(original_msg, 0, 200);
					System.out.println("Message Test:" + msg6);
					channel.send(msg6);
				} else {
					Message msg = new ObjectMessage(null, line);
					System.out.println("Send a Message all members." + msg);
					channel.send(msg);
				}

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		new SimpleChat().start();
	}
}
