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
			// step3：读取文件内容数据，使用输入流的方法
			int data;
			while ((data = fis.read()) != -1) {
				System.out.print((char)data);
			}
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
