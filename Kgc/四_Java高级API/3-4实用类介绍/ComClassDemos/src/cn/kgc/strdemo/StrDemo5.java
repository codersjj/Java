package cn.kgc.strdemo;

// String的所有操作并不影响字符串本身，它影响的只是字符串的一个副本。
public class StrDemo5 {
	public static void main(String[] args) {
		String s = "I love Qi!";
		String newStr = s.substring(2, 6);
		System.out.println(newStr);
		System.out.println(s);
	}
}
