import java.util.InputMismatchException;
import java.util.Scanner;


public class Test3 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("请输入被除数：");
		try{
			int num1 = in.nextInt();
			System.out.print("请输入除数：");
			int num2 = in.nextInt();
			System.out.println(num1 + "/" + num2 + "=" + num1 / num2);
		}catch(InputMismatchException e){ 
			System.err.println("出现了错误操作！");
			e.printStackTrace();
		}finally{
			System.out.println("感谢使用本程序！");
		}
	}
}

//	加上finally后，不管发不发生异常，程序都会执行finally块。不执行的唯一情况是在finally块前退出了JVM。
//	运行结果：
//		请输入被除数：100
//		请输入除数：0
//		感谢使用本程序！
//		Exception in thread "main" java.lang.ArithmeticException: / by zero
//			at Test3.main(Test3.java:13)
