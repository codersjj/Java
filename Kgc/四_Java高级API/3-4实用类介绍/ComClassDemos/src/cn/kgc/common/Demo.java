package cn.kgc.common;

public class Demo {
	public static void main(String[] args) {
		// 包装类的构造方法：（1）把基本数据类型变为包装类（2）把字符串变为包装类
		// 所有包装类都有的构造方法：将与之对应的基本数据类型作为参数
		int i = 3;
		Integer i1 = new Integer(i);
		double d = 7.8;
		Double d1 = new Double(d);
		Boolean b1 = new Boolean(true);
		Character c1 = new Character('a');
		
		System.out.println(i1 + "\t" + d1 + "\t" + b1 + "\t" + c1);
		
		// 除了Character之外的包装类都有的构造方法：将字符串作为参数
		Integer i2 = new Integer("2");
		Double d2 = new Double("2.7");
//		Boolean b2 = new Boolean("true");
//		Boolean b2 = new Boolean("TrUe");
		Boolean b2 = new Boolean("love");
//		Character c2 = new Character("a"); // 编译错误
		
		System.out.print(i2 + "\t" + d2 + "\t" + b2);
	}
}
