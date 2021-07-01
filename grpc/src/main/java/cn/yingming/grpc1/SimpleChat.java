package cn.yingming.grpc1;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

import org.jgroups.*;
import org.jgroups.stack.ProtocolStack;
import org.jgroups.util.*;


public class SimpleChat extends JChannel implements Receiver{
	SimpleChat channel;

	public SimpleChat() throws Exception {
	}

	private void start() throws Exception {
		channel = new SimpleChat();
		channel.setReceiver(this).connect("Node3");
		//channel.setStats(true);
		eventLoop();
		channel.close();
	}
	@Override
	public void viewAccepted(View new_view) {
		System.out.println("** view: " + new_view);

	}
	@Override
	public void receive(Message msg) {
		System.out.println(msg.getSrc() + ": " + msg.getPayload());
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
				if (line.startsWith("TO")){
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
