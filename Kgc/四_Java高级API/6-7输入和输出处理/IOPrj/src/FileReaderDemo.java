import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

// 用字符流将“D:\JJSha\text.txt”读出来
// （1）“D:\JJSha\text.txt”的编码格式是ANSI(GBK)
public class FileReaderDemo {
	public static void main(String[] args) {
		// 查看本地平台（这里为MyEclipse）的字符编码类型
		System.out.println(System.getProperty("file.encoding"));
		Reader fr = null;
		try {
//			fr = new FileReader("D:\\JJSha\\text.txt");
			FileInputStream fis = new FileInputStream("D:/JJSha/text.txt"); 
			// InputStreamReader可以指定以何种字符编码格式去读文件（只要读文件时用的编码格式和文件本身的编码格式一致，一般就不会出现乱码。也就是说流的编码格式和文件的编码格式一致时，就不会出现乱码了。）
			fr = new InputStreamReader(fis, "GBK");
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
