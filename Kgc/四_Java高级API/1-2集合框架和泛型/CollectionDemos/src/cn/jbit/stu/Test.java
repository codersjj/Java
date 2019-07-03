package cn.jbit.stu;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		Map<String, Student> students = new HashMap<String, Student>();
		Student s1 = new Student("张三", "男");
		Student s2 = new Student("李四", "男");
		Student s3 = new Student("王五", "女");
		// 键-值对：学员的英文名字-学员
		// Map里put进来的值可以是任何Object类型。
		students.put("Jack", s1);
		students.put("Tom", s2);
		students.put("Kitty", s3);
		
		// 根据特定key获取相应value
		System.out.print("请输入您要寻找学员的英文名字：");
		Scanner input = new Scanner(System.in);
		String key = input.next();
		if(students.containsKey(key)){
			Student stu = students.get(key);
			System.out.print("您要找的学员是：" + stu.getName() + "-" + stu.getSex());
		}else{
			System.out.println("对不起，没有您要寻找的学员！");
		}
		
	}
}
