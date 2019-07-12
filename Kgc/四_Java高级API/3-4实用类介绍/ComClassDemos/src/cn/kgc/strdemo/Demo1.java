package cn.kgc.strdemo;

import java.util.Scanner;

public class Demo1 {
	public static void main(String[] args) {
/*		// String --> StringBuffer
		StringBuffer s1 = new StringBuffer("Hello");
		s1.append(" Peppa!");
		System.out.println(s1); // Hello Peppa!
		// StringBuffer --> String
		String s = s1.toString();
		System.out.println(s); // Hello Peppa!
		
		s1.insert(5, "!"); // Hello! Peppa!
		System.out.println(s1);
*/		
		Scanner input = new Scanner(System.in);
		System.out.println("ÇëÊäÈëÒ»´®Êý×Ö£º");
		String num = input.next();
		StringBuffer strBufNum = new StringBuffer(num);
		for (int i = strBufNum.length() - 3; i > 0; i = i - 3) {
			strBufNum.insert(i, ",");
		}
		System.out.println(strBufNum);
	}

}
