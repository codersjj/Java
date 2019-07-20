import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

// 序列化和反序列化学生对象
public class SerialStuDemo {
	public static void main(String[] args) {
		Student stu = new Student("小虎", 17, "男", "123456");
		
		// 对象输出流
		ObjectOutputStream oos = null;
		FileOutputStream fos = null;
		
		// 对象输入流
		ObjectInputStream ois = null;
		FileInputStream fis = null;
		try {
			// 序列化
			fos = new FileOutputStream("D:\\JJSha\\student.txt");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(stu);
			
			// 反序列化
			fis = new FileInputStream("D:\\JJSha\\student.txt");
			ois = new ObjectInputStream(fis);
			Student stu1 = (Student)ois.readObject();
			System.out.println("反序列化后的信息：" + stu1.getName() + "-" + stu1.getAge() + "-" + stu1.getGender() + "-" + stu1.getPassword());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
				fos.close();
				
				ois.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
}
