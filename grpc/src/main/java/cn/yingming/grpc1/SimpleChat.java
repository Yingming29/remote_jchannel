package cn.yingming.grpc1;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.util.ArrayList;

import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ObjectMessage;
import org.jgroups.Receiver;
import org.jgroups.View;
import org.jgroups.util.UUID;


public class SimpleChat extends JChannel implements Receiver{
	SimpleChat channel;
	String user_name = System.getProperty("user.name", "n/a");

	public SimpleChat() throws Exception {
	}

	private void start() throws Exception {
		channel = new SimpleChat();
		channel.setReceiver(this).connect("ChatCluster");
		eventLoop();
		channel.close();
	}
	@Override
	public void viewAccepted(View new_view) {
		System.out.println("** view: " + new_view);
		// System.out.println("** coord Add: " + new_view.getCoord());
		// System.out.println("** coord fromString: " + UUID.fromString(new_view.getCoord().toString()));
	}
	@Override
	public void receive(Message msg) {
		System.out.println("1?");
		String line = msg.getSrc() + ":" + msg.getPayload();
		System.out.println("Before decode:" + line);
		System.out.println(msg.toString());

		// String content = new String((byte[]) msg.getPayload());
		// line = msg.getSrc() + ":" + content;
		// System.out.println("After decode:" + line);
		// System.out.println(channel.dumpStats());
		System.out.println("getobject1:" + msg.getObject());
		System.out.println("getobject2:" + msg.getObject().getClass());
		System.out.println("getobject3:" + msg.getObject().toString());
		System.out.println("getpayload:" + msg.getPayload().toString());
		System.out.println(msg.getSrc().getClass());
		// System.out.println(msg.getDest());

		/*
		try{
			ArrayList al = new ArrayList();
			al.add("avg_size_up");
			System.out.println(channel.dumpStats("FRAG2", al));
		} catch(Exception e){
			e.printStackTrace();
		}

		 */

		// System.out.println(channel.printProtocolSpec(true));
		// System.out.println(channel.printProtocolSpec(false));
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
				byte[] b = line.getBytes();
				MessageRJ msgrj = new MessageRJ("1", "1");
				Message msg = new ObjectMessage(null, msgrj);
				// msg.setFlagIfAbsent(Message.TransientFlag.DONT_LOOPBACK);
				//System.out.println(channel.generateAddress());
				byte[] ser_obj = UtilsRJ.serializeObj(msg);
				System.out.println(ser_obj);
				System.out.println(UtilsRJ.unserializeObj(ser_obj));
				Message unser_msg = (Message) UtilsRJ.unserializeObj(ser_obj);
				System.out.println(unser_msg.getObject().toString());
				// channel.setStats(true);
				// channel.send(msg);
				// channel.dumpStats();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		new SimpleChat().start();
	}
}
