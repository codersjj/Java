import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

// 将pet.template文件中的内容读取出来
// 将读取出来的内容特定部分进行替换
// 将替换后的新内容重新写入到另外一个文件中
public class ReaderAndWriterDemo {
	public static void main(String[] args) {
		// 1.1、输入流完成读取功能
		BufferedReader reader = null;
		InputStreamReader isr = null;
		FileInputStream fis = null;
		
		// 3.1、输出流完成写入功能
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			// 1.2、从源文件中读取
			// FileInputStream为字节流
			fis = new FileInputStream("D:\\JJSha\\pet.template");
			// 把字节流包成字符流
			isr = new InputStreamReader(fis, "utf-8");
			reader = new BufferedReader(isr);
			StringBuffer sbf = new StringBuffer();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sbf.append(line);
			}
			
			// 2、文件内容替换
			System.out.println("替换前：" + sbf);
//			String newStr1 = sbf.toString().replace("{name}", "欧欧");
//			String newStr2 = newStr1.replace("{type}", "狗狗");
//			String newStr = newStr2.replace("{master}", "琪琪");
			String newStr = sbf.toString().replace("{name}", "欧欧").replace("{type}", "狗狗").replace("{master}", "琪琪");
			System.out.println("替换后：" + newStr);
			
			// 3.2、将替换后的新内容写入新文件
			fw = new FileWriter("D:\\JJSha\\newPet.txt"); // 申明写到哪个新文件里
			bw = new BufferedWriter(fw); // 用带缓存区的字符输出流把FileWriter包起来
			bw.write(newStr); // 把新的字符串写入新文件中
			bw.flush(); // 写完后记得把Writer清空缓存区
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
				
				bw.close();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
