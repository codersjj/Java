import java.util.InputMismatchException;
import java.util.Scanner;


public class Test4 {
	//一个方法体中抛出异常后如何通知调用者？
	//方式1：调用者继续声明异常
	public static void main(String[] args) throws ArithmeticException, InputMismatchException { // main方法这里把异常抛给JVM处理
		Test4 t = new Test4();
		t.divide();
	}
	
	//求两个数的除法运算，声明该方法的两种异常
	public void divide() throws ArithmeticException, InputMismatchException{
		Scanner in = new Scanner(System.in);
		System.out.print("请输入被除数：");
		int num1 = in.nextInt();
		System.out.print("请输入除数：");
		int num2 = in.nextInt();
		System.out.println(num1 + "/" + num2 + "=" + num1 / num2);
	}
	
}
