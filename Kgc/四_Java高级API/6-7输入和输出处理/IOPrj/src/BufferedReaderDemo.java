import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class BufferedReaderDemo {
	public static void main(String[] args) {
		Reader fr = null;
		FileInputStream fis = null;
		BufferedReader br = null;
		try {
			fis = new FileInputStream("D:/JJSha/text.txt"); 
			fr = new InputStreamReader(fis, "GBK");
			br = new BufferedReader(fr);
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
