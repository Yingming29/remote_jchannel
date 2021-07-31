package cn.yingming.grpc1;

import java.io.*;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

import io.grpc.jchannelRpc.ChannelMsg;
import org.jgroups.*;
import org.jgroups.protocols.relay.SiteMaster;
import org.jgroups.protocols.relay.SiteUUID;
import org.jgroups.stack.IpAddress;
import org.jgroups.util.*;


public class SimpleChat implements Receiver{

	JChannel channel;
	final List<String> state = new LinkedList<String>();

	private void start() throws Exception {
		channel = new JChannel();
		channel.setReceiver(this).connect("NodeCluster");
		// channel.getState(null, 1000);
		eventLoop();
		channel.close();
	}
	@Override
	public void viewAccepted(View new_view) {
		System.out.println("** view: " + new_view);
	}
	@Override
	public void receive(Message msg) {
		// filter a Msg
		if (msg instanceof ObjectMessage && msg.getObject() instanceof ChannelMsg){
			return ;
		}
		System.out.println("-------------");
		String line = null;
		if (msg instanceof EmptyMessage){
			line = msg.getSrc() + ": " + "EmptyMessage";
		} else if (msg instanceof CompositeMessage){
			CompositeMessage compMsg = (CompositeMessage) msg;
			LinkedList<String> compMsgList = new LinkedList<>();
			compMsg.forEach(eachOne -> compMsgList.add(eachOne.getObject()));
			line = msg.getSrc() + " (CompositeMessage): " + compMsgList;
			System.out.println(msg);
		} else if (msg instanceof BytesMessage){
			line = msg.getSrc() + " (BytesMessage): " + msg.getPayload();
			String result = new String((byte[]) msg.getPayload());
			System.out.println("verify bytes: " + result);
		} else if (msg instanceof NioMessage){
			NioMessage nioMsg = (NioMessage) msg;
			line = msg.getSrc() + "(NioMessage): " + msg.getPayload();
			CharBuffer charBuffer = null;
			ByteBuffer buffer = nioMsg.getPayload();
			String result_bb = null;
			try{
				Charset charSet = StandardCharsets.UTF_8;
				CharsetDecoder decoder = charSet.newDecoder();
				charBuffer = decoder.decode(buffer);
				buffer.flip();
				result_bb = charBuffer.toString();
			} catch (Exception e){
				e.printStackTrace();
			}
			System.out.println("verify ByteBuffer: " + result_bb);
		} else if (msg instanceof LongMessage){
			line = msg.getSrc() + "(LongMessage): " + msg.getObject();
		} else {
			line = msg.getSrc() + "(ObjectMessage): " + msg.getPayload();
		}
		System.out.println(line);
		synchronized (state){
			state.add(line);
		}
		System.out.println("-------------");
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
		// System.out.println("Receive a state.");
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
				String line = in.readLine();
				if (line.startsWith("quit") || line.startsWith("exit")) {
					break;
				}
				/*
				UUID
				IpAddress
				SiteMaster
				SiteUUID

				 */

				// Different input for demo
				if (line.startsWith("to")){
					String[] strs = line.split(" ");
					int target_index = Integer.parseInt(strs[1]);
					Address target = channel.getView().getMembers().get(target_index);
					Message msg = new ObjectMessage(target, line);
					channel.send(msg);
				} else if (line.startsWith("msg0")){
					Message msg0 = new BytesMessage(null, "byteMsg from JChannel".getBytes());
					channel.send(msg0);
				} else if (line.startsWith("msg1")){
					ByteBuffer bb = ByteBuffer.wrap("byteBufferMsg from JChannel".getBytes());
					Message msg1 = new NioMessage(null, bb);
					channel.send(msg1);
				} else if (line.startsWith("msg2")){
					Message msg2 = new EmptyMessage(null);
					channel.send(msg2);
				} else if (line.startsWith("msg3")){
					String obj = "objectMsg from JChannel";
					Message msg3 = new ObjectMessage(null, obj);
					channel.send(msg3);
				} else if (line.startsWith("msg4")){
					long long_num = 100000L;
					Message msg4 = new LongMessage(null, long_num);
					channel.send(msg4);
				} else if (line.startsWith("msg5")){
					Message subMsg1 = new ObjectMessage(null, "subObjMsg1 from JChannel");
					Message subMsg2 = new ObjectMessage(null, "subObjMsg2 from JChannel");
					Message subMsg3 = new ObjectMessage(null, "subObjMsg3 from JChannel");
					Message msg5 = new CompositeMessage(null, subMsg1, subMsg2, subMsg3);
					channel.send(msg5);
					// not supported FragmentedMessage
				} else if (line.startsWith("msg6")){
					Message original_msg = new ObjectMessage(null, "originalMessageStringObj");
					Message msg6 = new FragmentedMessage(original_msg, 0, 200);
					channel.send(msg6);
				} else if (line.equals("printNameCache")){
					System.out.println(NameCache.printCache());
				} else if (line.equals("printMsgDetails")) {
					Message msgHeaders = new ObjectMessage(null, "StrObj");
					channel.send(msgHeaders);
					System.out.println("Headers:" + msgHeaders.getHeaders());
					System.out.println("Flags:" + msgHeaders.getFlags());
				} else if (line.equals("printMsgDetails2")) {
					Message msgHeaders = new EmptyMessage();
					channel.send(msgHeaders);
					System.out.println("Headers:" + msgHeaders.getHeaders());
					System.out.println("Flags:" + msgHeaders.getFlags());
				} else {
					Message msg = new ObjectMessage(null, line);
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
