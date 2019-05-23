import java.util.Scanner;

//使用try-catch处理异常
//第一种情况：try部分的代码正常（不会产生异常），程序运行时执行完try部分后会跳过catch部分的代码，直接执行后面的代码。
//第二种情况：try部分的代码出现异常，且与catch后的异常类型匹配（比如下面的代码运行时输入的除数为0），程序运行时会进入catch部分，执行完catch部分后还会执行后面的代码。
//第三种情况：try部分的代码出现异常，但与catch后的异常类型不匹配（比如下面的代码运行时输入的除数为字母），程序运行时不会进入catch部分，而会在异常处中断运行，不会执行try-catch块后的代码段。
public class Test2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("请输入被除数：");
		try{
			int num1 = in.nextInt();
			System.out.print("请输入除数：");
			int num2 = in.nextInt();
			System.out.println(num1 + "/" + num2 + "=" + num1 / num2);
		}catch(ArithmeticException e){ 
			System.err.println("出现了错误操作！");
			//异常对象e   printStackTrace()：打印异常堆栈信息
			e.printStackTrace();
		}
		System.out.println("感谢使用本程序！");
	}
}

// java中所有的异常都是Exception（父类）
// 除数为0的异常：ArithmeticException（子类）
// 输入的格式不正确：InputMismatchException（子类）
// catch后面的类型如果是Exception类型，优点是可以抓住所有异常，缺点是抓住的异常不具体、不精准。

//下面是运行测试过程：
//测试第二种情况：
//	请输入被除数：100
//	请输入除数：0
//	出现了错误操作！
//	感谢使用本程序！
//	java.lang.ArithmeticException: / by zero
//		at Test2.main(Test2.java:14)
//
//测试第三种情况：
//	请输入被除数：100
//	请输入除数：Z
//	Exception in thread "main" java.util.InputMismatchException
//		at java.util.Scanner.throwFor(Scanner.java:909)
//		at java.util.Scanner.next(Scanner.java:1530)
//		at java.util.Scanner.nextInt(Scanner.java:2160)
//		at java.util.Scanner.nextInt(Scanner.java:2119)
//		at Test2.main(Test2.java:14)
