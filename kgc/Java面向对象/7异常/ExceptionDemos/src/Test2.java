import java.util.Scanner;

//使用try-catch处理异常
//第一种情况：try部分的代码正常（不会产生异常），程序运行时执行完try部分后会跳过catch部分的代码，直接执行后面的代码。
//第二种情况：try部分的代码出现异常（比如下面的代码）
public class Test2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("请输入被除数：");
		try{
			int num1 = in.nextInt();
			System.out.print("请输入除数：");
			int num2 = in.nextInt();
			System.out.println(num1 + "/" + num2 + "=" + num1 / num2);
		}catch(Exception e){ // Java里所有的异常都是Exception类型的   后面的异常对象名随便取（这里取名为e）
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

