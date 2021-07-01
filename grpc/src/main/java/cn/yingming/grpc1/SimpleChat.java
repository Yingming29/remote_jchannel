package cn.yingming.grpc1;

import java.io.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
		String line = msg.getSrc() + ": " + msg.getPayload();
		System.out.println(line);
		synchronized (state){
			state.add(line);
		}
	}
	@Override
	public void getState(OutputStream output) throws Exception{
		synchronized (state){
			Util.objectToStream(state, new DataOutputStream(output));
		}
	}
	@Override
	public void setState(InputStream input) throws Exception{
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
