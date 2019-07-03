package cn.kgc.collectionsdemo;

public class Student implements Comparable {
	// 学号  姓名  性别
	private int number;
	private String name;
	private String sex;
	
	// 无参的构造方法
	public Student(){} 
	// 带参的构造方法
	public Student(String name, String sex) {
		super();
		this.name = name;
		this.sex = sex;
	}
	public Student(int number, String name, String sex) {
		super();
		this.number = number;
		this.name = name;
		this.sex = sex;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	// 编写一个比较规则：让当前学员对象（this）和传过来的学员对象（o）进行比较
	// 以学员学号为比较规则
	public int compareTo(Object o) {
		Student stu = (Student)o;
		if(this.number == stu.number){
			return 0;
		}else if(this.number > stu.number){
			return 1;
		}else{
			return -1;
		}
	}
	
	
}
