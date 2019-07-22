package cn.kgc.p4;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

// 基于UDP的Socket通信，客户端
public class LoginClient {
	public static void main(String[] args) {
		// 买礼物
		String info = "心形巧克力！";
		byte[] infos = info.getBytes();
		// 对方的地址和邮编（端口号）
		InetAddress ia;
		// 快递点
		DatagramSocket socket = null;
		try {
			// 获得收件人地址
			ia = InetAddress.getByName("localhost");
			// 包裹包装礼物
			DatagramPacket dp = new DatagramPacket(infos, infos.length, ia, 5000);
			socket = new DatagramSocket();
			// 通过快递点往外寄出礼物
			socket.send(dp);
			
			// 接收服务器的响应
			// 先准备一个空包（DatagramPacket）
			byte[] replys = new byte[1024];
			DatagramPacket dp1 = new DatagramPacket(replys, replys.length);
			// 在快递点取礼物
			socket.receive(dp1);
			// 拆礼物
			String reply = new String(dp1.getData(), 0, dp1.getData().length);
			System.out.println("服务器的响应 ：" + reply);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			socket.close();
		}
		
	}
}
