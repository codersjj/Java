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
//			return;
		}catch(InputMismatchException e){ 
			System.err.println("输入必须为整数！");
			e.printStackTrace();
			// 退出JVM   exit()只要传一个非零的int类型值即可
//			System.exit(7);
//			return;
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
}

// 注意：不同的块（{...}）里可以出现同名的（局部）变量。
// 多重catch的顺序为先写子类，后写父类。
// 尽量去捕捉精确的异常，以便有精确的提示。