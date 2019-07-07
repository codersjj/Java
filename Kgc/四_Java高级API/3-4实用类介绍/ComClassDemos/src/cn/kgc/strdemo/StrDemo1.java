package cn.kgc.strdemo;

// 字符串的方法：
// equalsIgnoreCase() 忽略大小写  
// toLowerCase() 转为小写  
// toUpperCase() 转为大写  
// trim() 去除字符串两端的空格
// concat() 字符串连接

public class StrDemo1 {
	public static void main(String[] args) {
		String str1 = "QI";
		String str2 = "qi";
		System.out.println(str1.equals(str2)); // false
		System.out.println(str1.equalsIgnoreCase(str2)); // true
		System.out.println(str1.toLowerCase().equals(str2.toLowerCase())); // true
		System.out.println(str1.toUpperCase().equals(str2.toUpperCase())); // true
		
		String s1 = "  Peppa  ";
		String s2 = "  Peppa  ";
		System.out.println(s1);
		System.out.println(s2.trim());
		
		int n = 7;
		System.out.println("数字" + n);
		String x = "你好！";
		String q = "Peppa!"; 
		System.out.println(x + q);
		System.out.println(x.concat(q));
	}
}
