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
		System.out.println(msg);
		String line = msg.getSrc() + ": " + msg.getPayload();
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
		list = (List<String>)Util.objectFromStream(new DataInputStream(input));
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
					System.out.println("Send a Message to a JChannel." + msg);
					channel.send(msg);
				} else if (line.startsWith("msg1")){
					Message msg1 = new BytesMessage(null, "byte".getBytes());
					System.out.println("Send a Message all members." + msg1);
					channel.send(msg1);
				} else if (line.startsWith("msg2")){
					long long_num = 100000L;
					Message msg2 = new LongMessage(null, long_num);
					System.out.println("Send a Message all members." + msg2);
					channel.send(msg2);
				} else if (line.startsWith("msg3")){
					Message subMsg = new ObjectMessage(null, "subMessage");
					Message msg3 = new CompositeMessage(null, subMsg, subMsg, subMsg);
					System.out.println("Send a Message all members." + msg3);
					channel.send(msg3);
				} else if (line.startsWith("msg4")){
					Message msg4 = new EmptyMessage(null);
					System.out.println("Send a Message all members." + msg4);
					channel.send(msg4);
				} else if (line.startsWith("msg5")){
					ByteBuffer bb = ByteBuffer.wrap("byte".getBytes());
					Message msg5 = new NioMessage(null, bb);
					System.out.println("Send a Message all members." + msg5);
					channel.send(msg5);
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
