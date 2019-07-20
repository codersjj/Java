import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

// 借助字符流，往“D:\JJSha\text.txt”中写内容
public class WriterDemo {
	public static void main(String[] args) {
		Writer fw = null;
		try {
			fw = new FileWriter("D:/JJSha/text.txt", true);
			String info = "十月初四";
			fw.write(info);
			fw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
