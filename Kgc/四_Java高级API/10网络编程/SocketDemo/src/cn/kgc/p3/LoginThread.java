package cn.kgc.p3;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

// 线程类
// 接收客户请求，给客户一个响应（之前服务器做的事情）
// 线程构造方法中去绑定客户端的Socket
public class LoginThread extends Thread {
	private Socket socket;
	
	public LoginThread(Socket socket) { // 需要传的是客户端的socket
		this.socket = socket; // 线程的socket绑定客户端的socket
	}
	
	// 线程做的事都是通过run()方法实现
	// 接收客户请求，给客户一个响应（之前服务器做的事情）	
	public void run() {
		try{
			// 获得socket的输入流，获得用户的请求
			InputStream is = socket.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			User user = (User)ois.readObject();
			System.out.println("客户端说：" + user.getUserName() + "-" + user.getPwd());
	
			// 获得客户端的IP信息
			InetAddress ia = socket.getInetAddress();
			String ip = ia.getHostAddress();
			System.out.println("相应客户端的IP:" + ip);
			
			// 给客户端一个响应
			String reply = "欢迎登录!";
			// 通过输出流将响应发送回客户端
			OutputStream os = socket.getOutputStream();
			os.write(reply.getBytes());
			
			// 释放相应资源
			ois.close();
			os.close();
			is.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
