import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

// 借助带缓冲区的字符输出流，往“D:\JJSha\text.txt”中写内容
// 借助字符输入流将文件内容读出来显示在控制台上
public class BufferedWriterDemo {
	public static void main(String[] args) {
		Writer fw = null;
		BufferedWriter bw = null;
		
		Reader fr = null;
		BufferedReader br = null;
		try {
			// 输出
			fw = new FileWriter("D:/JJSha/text.txt", true);
			bw = new BufferedWriter(fw);
			
			bw.write("十月初四");
			bw.newLine();
			bw.write("77");
			// 使用输出流时，一定要记得清空缓存（flush）或关闭流，否则往外写内容会失败。
			bw.flush();
			
			// 输入
			fr = new FileReader("D:/JJSha/text.txt");
			br = new BufferedReader(fr);
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
				bw.close();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
