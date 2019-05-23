//import java.util.Scanner;

//不对异常进行处理
public class Test0 {
	public static void main(String[] args) {
		/*Scanner in = new Scanner(System.in);
		System.out.print("请输入被除数：");
		int num1 = in.nextInt();
		System.out.print("请输入除数：");
		int num2 = in.nextInt();
		System.out.println(num1 + "/" + num2 + "=" + num1 / num2);
		System.out.println("感谢使用本程序！");*/
		String s = null;
		System.out.println(s.equals("qq"));
//		运行结果：
//			Exception in thread "main" java.lang.NullPointerException
//				at Test0.main(Test0.java:14)
//		分析：空指针异常。一般在某一对象被赋空值（null）后，再去操作这个对象，就可能发生空指针异常。
//			某一对象只是声明而没有new时，此对象就是空的（null）。
//			还有一些对象是需要外界给它赋值的，如果外界没有赋值给它，它就是null的，也会出现空指针异常。

	}
}
