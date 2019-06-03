import java.util.Scanner;

/*
 * 传统方式来处理异常
 */
public class Test1 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("请输入被除数：");
		int num1 = in.nextInt();
		System.out.print("请输入除数：");
		int num2 = 0;
		if(in.hasNextInt()){ // hasNextInt()用来判断用户在控制台输入的是否为整数，若是，返回true，若不是，返回false。
			//录入的是整数
			num2 = in.nextInt();
			if(num2 == 0){
				System.err.println("输入的除数是0，程序退出！"); // err指error，代表错误的流   输出样式会变红色，比较醒目
				System.exit(1); //程序退出JVM（传的参数只要是非0的整数，就表示退出JVM）
			}else{
				System.out.println(num1 + "/" + num2 + "=" + num1 / num2);
				System.out.println("感谢使用本程序！");
			}
		}else{
			//录入的不是整数
			System.err.println("录入的除数不是整数，程序退出");
			System.exit(1); //退出JVM（传的参数只要是非0的整数，就表示退出JVM）
		}
	}
}
