import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

// 使用字节输出流往文件中写内容
public class FileOutputStreamTest {
	public static void main(String[] args){
		FileOutputStream fos = null;
		try {
			// 创建输出流FileOutputStream对象
			fos = new FileOutputStream("d:\\JJSha\\hello.txt");
			// 定义想要写出去的内容
			String str = "爱Java";
			// 需要把字符串转换为字节数组
			byte[] words = str.getBytes();
			// 从数组words的第0个位置开始写，写出去的长度为整个数组的长度（有多少个字节都写出去）
			fos.write(words, 0, words.length);
			System.out.println("hello文件已更新！");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
