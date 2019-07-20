import java.io.Serializable;

// 学生类
public class Student implements Serializable {
	private String name;
	private int age;
	private String gender;
	private transient String password; // 加上transient关键字后，可以不被序列化或反序列化（用来屏蔽某些敏感字段的序列化） 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Student(String name, int age, String gender) {
		this.name = name;
		this.age = age;
		this.gender = gender;
	}
	public Student(String name, int age, String gender, String password) {
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.password = password;
	}
	
}
