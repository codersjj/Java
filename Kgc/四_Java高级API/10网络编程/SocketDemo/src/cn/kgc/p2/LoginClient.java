package cn.kgc.p2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

// 客户端
public class LoginClient {
	public static void main(String[] args) {
		try {
			// 发送请求到服务器
			// 创建了一个客户端的Socket
			Socket socket = new Socket("localhost", 5000);
			// 通过输出流发送请求
			User user = new User("tom", "123456");
			// 通过socket获取输出流
			OutputStream os = socket.getOutputStream();
			// 把socket的输出流包装成对象输出流
			ObjectOutputStream oos = new ObjectOutputStream(os);
			// 把对象写出去
			oos.writeObject(user);
			/*String info = "用户名：Tom；密码：123456";
			OutputStream os = socket.getOutputStream();
			byte[] infos = info.getBytes();
			os.write(infos);*/
			
			// 先关闭输出流
			socket.shutdownOutput(); 
			// 通过输入流接收服务器给我的响应
			InputStream is = socket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String reply;
			while ((reply = br.readLine()) != null) {
				System.out.println("服务器说：" + reply);
			}
			
			// 释放资源
			br.close();
			is.close();
			oos.close();
			os.close();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
