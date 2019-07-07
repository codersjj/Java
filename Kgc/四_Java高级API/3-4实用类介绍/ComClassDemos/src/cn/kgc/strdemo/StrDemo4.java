package cn.kgc.strdemo;

import java.util.Scanner;

// 字符串截取：indexOf()  lastIndexOf()  substring(begin)  substring(begin, end) 
public class StrDemo4 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("---欢迎进入作业提交系统---");
		System.out.print("请输入Java文件名：");
		String name = input.next();
		System.out.print("请输入你的邮箱：");
		String email = input.next();
		
		// 检查文件名：必须以.java为后缀
		boolean fileFlag = false;
		int index = name.indexOf(".");
		if(index != -1 && index != 0 && name.substring(index).equals(".java")){
			// 文件名正确
			fileFlag = true;
		}else{
			System.out.println("文件名无效！");
		}
		
		// 检查邮箱：@  .
		boolean emailFlag = false;
		int index1 = email.indexOf('@');
		int index2 = email.indexOf('.');
		if(index1 != -1 && index2 != -1 && index1 < index2){
			// 邮箱格式正确
			emailFlag = true;
		}else{
			System.out.println("邮箱名无效！");
		}
		
		if(fileFlag && emailFlag){
			System.out.println("作业提交成功！");
		}else{
			System.out.println("作业提交失败！");
		}
	}
}
