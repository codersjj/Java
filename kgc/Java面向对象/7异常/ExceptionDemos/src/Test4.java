import java.util.InputMismatchException;
import java.util.Scanner;


public class Test4 {
	//一个方法体中抛出异常后如何通知调用者？
	//方式2：调用者处理异常
	public static void main(String[] args) { 
		Test4 t = new Test4();
		try{
			t.divide();
		}catch(InputMismatchException e){ 
			System.err.println("输入必须为整数！");
			e.printStackTrace();
		}catch(ArithmeticException e){
			System.err.println("除数不能为0！");
			e.printStackTrace();
		}catch(Exception e){
			System.out.println("出现了其它错误操作！");
			e.printStackTrace();
		}finally{
			System.out.println("感谢使用本程序！");
		}
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
