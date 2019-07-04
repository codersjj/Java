package cn.kgc.common;

public class Demo1 {
	public static void main(String[] args) {
		// 包装类 --> 基本数据类型   ***Value()
		Integer i = new Integer(2);
		int j = i.intValue();
		System.out.println(j);
		
		Boolean b = new Boolean(true);
		boolean b1 = b.booleanValue();
		System.out.println(b1);
		
		// 基本数据类型 --> 字符串   （1）toString() （2）+ ""   （方法2可能更常用）
		int num = 8;
		String strNum = Integer.toString(num);
		String strNum2 = num + "";
		System.out.println(strNum + "---" + strNum2);
		
		// 字符串 --> 基本数据类型   parse***()   Character类型除外   （重点记忆）
		String s = "27";
		int num2 = Integer.parseInt(s);
		boolean boolNum = Boolean.parseBoolean(s);
		boolean boolNum2 = Boolean.parseBoolean("true");
		System.out.println(num2 + "---" + boolNum + "---" + boolNum2);
	}
}
