package cn.kgc.collectionsdemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionsDemo2 {
	public static void main(String[] args) {
		List<Student> list = new ArrayList<Student>();
		
		Student s1 = new Student(1, "张三", "男");
		Student s2 = new Student(2, "Jack", "男");
		Student s3 = new Student(3, "小花", "女");
		Student s4 = new Student(8, "小兰", "女");
		
		list.add(s1);
		list.add(s2);
		list.add(s3);
		list.add(s4);
		
		// 升序排序
		Collections.sort(list);
		for(Student stu : list){
			System.out.println(stu.getNumber() + "-" + stu.getName() + "-" + stu.getSex());
		}
		System.out.println("*******");
		
	}
}
