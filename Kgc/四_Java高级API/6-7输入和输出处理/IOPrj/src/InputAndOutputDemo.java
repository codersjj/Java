import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

// 将“d:\我的青春谁做主.txt”内容复制到“C:\Users\Shane\Desktop\hello.txt”中去
public class InputAndOutputDemo {
	public static void main(String[] args) {
		// 输入流，这里负责把d盘的文件数据读出来
		FileInputStream fis =null;
		// 输出流，这里负责把读出来的数据写进c盘的文件
		FileOutputStream fos =null;
		
		try {
			fis = new FileInputStream("d:\\我的青春谁做主.txt");
			// 覆盖源文件内容
//			fos = new FileOutputStream("C:/Users/Shane/Desktop/hello.txt");
			// 追加内容
			fos = new FileOutputStream("C:/Users/Shane/Desktop/hello.txt", true);
			
			byte[] words = new byte[1024];
			int len;
			while ((len = fis.read(words)) != -1) {
				fos.write(words, 0, len);
			}
			// 常见错误：（1）少写了一个字节过来 （2）多写了很多空格过来
			/*while (fis.read() != -1) {
				fis.read(words); // 读取文件
				fos.write(words, 0, words.length); // 写入文件
			}*/
			System.out.println("文件复制完成！");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 通常情况下，流的关闭顺序为：后打开的先关
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
