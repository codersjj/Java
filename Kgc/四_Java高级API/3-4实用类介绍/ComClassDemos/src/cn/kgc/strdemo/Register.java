package cn.kgc.strdemo;

import java.util.Scanner;

public class Register {
	public static void main(String[] args) {
		// 字符串常用方法：length() 注意其与数组length属性的区别
		// equals()：比较两个字符串内容是否相同
/*		Scanner input = new Scanner(System.in);
		System.out.print("请输入用户名：");
		String userName = input.next();
		System.out.print("请输入密码：");
		String pwd = input.next();
		
		if(pwd.length() < 6){
			System.out.println("登录密码不足6位，请重新输入！");
		}else{
			if(userName.equals("Qi") && pwd.equals("123456")){
				System.out.println("登录成功！");
			}else{
				System.out.println("用户名或密码错误！");
			}
		}
*/		
		
		// 常见面试题：（1）equals()与== （2）创建了几个字符串对象
		// String重写了equals()，变成了比较两个字符串的内容；而String的==依然是比较两个字符串是否为同一对象
		String s1 = "hello";
		String s2 = "hello";
		System.out.println(s1.equals(s2)); // true
		System.out.println(s1 == s2); // true
		// 创建了1个对象
		
		String s3 = "hello";
		String s4 = new String("hello");
		System.out.println(s3.equals(s4)); // true
		System.out.println(s3 == s4); // false
		// 创建了2个对象
		
		String s9 = "hello";
		String s10 = new String("Hello");
		System.out.println(s9.equals(s10)); // false
		System.out.println(s9 == s10); // false
		// 创建了3个对象
		
		String s5 = new String("hello");
		String s6 = new String("hello");
		System.out.println(s5.equals(s6)); // true
		System.out.println(s5 == s6); // false
		// 创建了3个对象
		
		String s7 = new String("hello");
		String s8 = new String("HELLO");
		System.out.println(s7.equals(s8)); // false
		System.out.println(s7 == s8); // false
		// 创建了4个对象
		
	}
}
