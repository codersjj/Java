import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

// 将pet.template文件中的内容读取出来
// 将读取出来的内容特定部分进行替换
// 将替换后的新内容重新写入到另外一个文件中
public class ReaderAndWriterDemo {
	public static void main(String[] args) {
		BufferedReader reader = null;
		InputStreamReader isr = null;
		FileInputStream fis = null;
		
		try {
			// FileInputStream为字节流
			fis = new FileInputStream("D:\\JJSha\\pet.template");
			// 把字节流包成字符流
			isr = new InputStreamReader(fis, "utf-8");
			reader = new BufferedReader(isr);
			String line = null;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				isr.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
