// step1：导入相关的类
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

// 读取文件d:\JJSha\text.txt中的内容
public class FileInputStreamDemo {
	public static void main(String[] args) {
		FileInputStream fis = null;
		try {
			// step2：构造文件输入流FileInputStream对象
			fis = new FileInputStream("d:\\JJSha\\text.txt");
			// 可以读取到的字节数
			System.out.println("可以读取到的字节数：" + fis.available());
			// step3：读取文件内容数据，使用输入流的read()方法
			int data;
			while ((data = fis.read()) != -1) {
				System.out.print((char)data);
			}
			// 流读到最后，可以读取到的字节数就为0了。
			System.out.println("\n可以读取到的字节数：" + fis.available());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// step4：关闭文件流对象
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}
}
