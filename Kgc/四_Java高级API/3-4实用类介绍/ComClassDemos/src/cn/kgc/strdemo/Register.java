package cn.kgc.strdemo;

import java.util.Scanner;

public class Register {
	public static void main(String[] args) {
		// 字符串常用方法：length() 注意其与数组length属性的区别
		Scanner input = new Scanner(System.in);
		System.out.print("请输入用户名：");
		String userName = input.next();
		System.out.print("请输入密码：");
		String pwd = input.next();
		
		if(pwd.length() < 6){
			System.out.println("注册密码不足6位，请重新输入！");
		}else{
			System.out.println("注册成功！");
		}
		
	}
}
