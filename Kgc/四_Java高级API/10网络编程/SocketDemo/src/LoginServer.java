import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

// 服务器
public class LoginServer {
	public static void main(String[] args) {
		try {
			// 接收客户端请求
			// 1、创建一个服务器端的Socket，端口号5000。
			ServerSocket serverSocket = new ServerSocket(5000);
			// 2、使用accept()方法（在没有连接进入之前一直处于阻塞等待状态）侦听并接收到此ServerSocket的连接，这里的socket可以理解为客户端的socket。
			Socket socket = serverSocket.accept();
			// 获得输入流，获得用户的请求
			InputStream is = socket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String info;
			while ((info = br.readLine()) != null) {
				System.out.println("客户端说：" + info);
			}
			
			// 给客户端一个响应
			String reply = "欢迎登录!";
			// 通过输出流将响应发送回客户端
			OutputStream os = socket.getOutputStream();
			os.write(reply.getBytes());
			
			// 释放相应资源
			os.close();
			br.close();
			is.close();
			socket.close();
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
