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
			// 退出JVM   exit()只要传一个非零的int类型值即可
			System.exit(7);
		}finally{
			System.out.println("感谢使用本程序！");
		}
	}
}

//	加上finally后不执行的唯一情况是在finally块前退出了JVM。
//	运行结果：
//		请输入被除数：100
//		请输入除数：q
//		出现了错误操作！
//		java.util.InputMismatchException
//			at java.util.Scanner.throwFor(Scanner.java:909)
//			at java.util.Scanner.next(Scanner.java:1530)
//			at java.util.Scanner.nextInt(Scanner.java:2160)
//			at java.util.Scanner.nextInt(Scanner.java:2119)
//			at Test3.main(Test3.java:12)