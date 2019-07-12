package cn.kgc.strdemo;

public class Demo1 {
	public static void main(String[] args) {
		// String --> StringBuffer
		StringBuffer s1 = new StringBuffer("Hello");
		s1.append(" Peppa!");
		System.out.println(s1); // Hello Peppa!
		// StringBuffer --> String
		String s = s1.toString();
		System.out.println(s); // Hello Peppa!
		
		s1.insert(5, "!"); // Hello! Peppa!
		System.out.println(s1);
	}

}
