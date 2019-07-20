import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

// 将“D:/JJSha/peppa.jpg”复制到“D:/JJSha/newPic”文件夹下
public class CopyPic {
	public static void main(String[] args) {
		// 输入流
		DataInputStream dis = null;
		FileInputStream fis = null;
		
		// 输出流
		DataOutputStream dos = null;
		FileOutputStream fos = null;		
		try {
			fis = new FileInputStream("D:/JJSha/peppa.jpg");
			dis = new DataInputStream(fis);
			
			fos = new FileOutputStream("D:/JJSha/newPic/newPeppa.jpg");
			dos = new DataOutputStream(fos);
			
			int temp;
			while ((temp = dis.read()) != -1) {
				dos.write(temp);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				dos.close();
				fos.close();
				dis.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
				
	}
}
