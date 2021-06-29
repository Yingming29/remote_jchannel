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
	String user_name = System.getProperty("user.name", "n/a");

	public SimpleChat() throws Exception {
	}

	private void start() throws Exception {
		channel = new SimpleChat();
		channel.setReceiver(this).connect("ChatCluster");
		//channel.setStats(true);
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
		//String line = msg.getSrc() + ":" + msg.getPayload();
		//System.out.println(line);
		System.out.println(msg);
		// String content = new String((byte[]) msg.getPayload());
		// line = msg.getSrc() + ":" + content;
		// System.out.println("After decode:" + line);
		// System.out.println(channel.dumpStats());
		/*
		System.out.println("getobject1:" + msg.getObject());
		System.out.println("getobject2:" + msg.getObject().getClass());
		System.out.println("getobject3:" + msg.getObject().toString());
		System.out.println("getpayload:" + msg.getPayload().toString());
		System.out.println(msg.getSrc().getClass());
		Address add = this.generateAddress();
        System.out.println(add);
		 */
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
				Message msg = new ObjectMessage(channel.view.getCoord(), line);
				channel.send(msg);
				System.out.println(channel.view.getCoord());
				/*
				Map<String,Map<String,Object>> m = channel.dumpStats();
				for (String each:m.keySet()) {
					for (String each2: m.get(each).keySet()){
						if (!(m.get(each).get(each2) instanceof String)){
							System.out.println(m.get(each).get(each2));
							System.out.println(m.get(each).get(each2).getClass());
						}
					}
				}
				System.out.println("------------------");
				System.out.println(m);
				byte[] b = line.getBytes();
				channel.send(null,b);

				 */
			/*
				byte[] byte_map = UtilsRJ.serializeObj(m);

				Object obj_map = UtilsRJ.unserializeObj(byte_map);
				if (obj_map != null) {
					System.out.println(obj_map.getClass());
					System.out.println(obj_map);
				}

			 */


				/*
				Address add = this.generateAddress();
				System.out.println(add);
				ByteArrayDataOutputStream d3 = new ByteArrayDataOutputStream();
				add.writeTo(d3);
				byte[] b3_byte = d3.buffer();
				System.out.println(b3_byte);

				ByteArrayDataInputStream in3 = new ByteArrayDataInputStream(b3_byte);

				UUID u3 = new UUID();

				u3.readFrom(in3);

				Address u4 = Util.createRandomAddress();
				Address u5 = Util.createRandomAddress();
				NameCache.add(u3,"u3");
				System.out.println(u3);
				System.out.println(u4);
				System.out.println(u5);
				System.out.println(NameCache.getContents());
				System.out.println(channel.address());

				System.out.println("-----------add namecache--------------");

				System.out.println("u3: " + u3);
				System.out.println("u4: " + u4);
				System.out.println("address: " + channel.address());

				System.out.println(NameCache.getContents());

				 */

				/*
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

				 */
				//System.out.println(channel.printProtocolSpec(true));
				// System.out.println(channel.printProtocolSpec(false));
				// System.out.println(JChannel.getVersion());
				//System.out.println(channel.getProtocolStack());
				// ProtocolStack ps = channel.getProtocolStack();


			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		new SimpleChat().start();
	}
}
