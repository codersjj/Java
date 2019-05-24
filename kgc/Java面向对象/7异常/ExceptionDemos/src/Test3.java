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
			return;
		}catch(InputMismatchException e){ 
			System.err.println("出现了错误操作！");
			e.printStackTrace();
			// 退出JVM   exit()只要传一个非零的int类型值即可
//			System.exit(7);
			return;
		}finally{
			System.out.println("感谢使用本程序！");
		}
	}
}

//	对于try-catch-finally，try块中若有return，执行到return时不会直接退出方法，而会先执行finally，之后再执行return，退出方法。
//	运行结果：
//		请输入被除数：7
//		请输入除数：2
//		7/2=3
//		感谢使用本程序！
//	总结：不管return出现在try块中还是catch块中，遇到return时，都会先去执行finally块，再来执行return跳出方法。
//		只有一种情况下不会执行finally，即System.exit(1)退出Java虚拟机。