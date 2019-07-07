package cn.kgc.strdemo;

// 字符串截取：indexOf()  lastIndexOf()  substring(begin)  substring(begin, end) 
public class StrDemo3 {
	public static void main(String[] args) {
		String s = "I love Peppa! ok";
		System.out.println(s.indexOf('o')); // 3
		System.out.println(s.lastIndexOf("o")); // 14
		System.out.println(s.indexOf("q")); // -1
		
		System.out.println(s.substring(7)); // 截取时，包含起始位置。
		System.out.println(s.substring(7, 11)); // 截取时，包含起始位置，不包括结束位置。（起始位置~结束位置-1）
	}
}
