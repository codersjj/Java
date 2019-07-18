import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

// 用字符流将“D:\JJSha\text.txt”读出来
public class FileReaderDemo {
	public static void main(String[] args) {
		FileReader fr = null;
		try {
			fr = new FileReader("D:\\JJSha\\text.txt");
			StringBuffer s = new StringBuffer();
			char[] ch = new char[1024];
			int len = -1;
			while ((len = fr.read(ch)) != -1) {
				s.append(ch);
			}
			System.out.println(s);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
