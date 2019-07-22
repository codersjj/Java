package cn.kgc.p3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

// 服务器：做的事情变了
// 一直监听客户请求
// 一旦监听到有客户请求，立即创建一个线程，开启线程。
public class LoginServer {
	public static void main(String[] args) {
		try {
			// 1、创建一个服务器端的Socket，端口号5000。
			ServerSocket serverSocket = new ServerSocket(5000);
			while (true) {
				// 2、使用accept()方法（在没有连接进入之前一直处于阻塞等待状态）侦听并接收到此ServerSocket的连接，这里的socket可以理解为客户端的socket。
				Socket socket = serverSocket.accept();
				// 创建一个和该客户端响应的线程
				LoginThread loginThread = new LoginThread(socket);
				// 启动线程（start()方法，其实就是调的run()方法）
				loginThread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
