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
		if(in.hasNextInt()){
			//录入的是整数
			num2 = in.nextInt();
			if(num2 == 0){
				System.out.println("输入的除数是0，程序退出！");
				System.exit(1); //退出JVM（传的参数只要是非0的整数，就表示退出JVM）
			}else{
				System.out.println(num1 + "/" + num2 + "=" + num1 / num2);
				System.out.println("感谢使用本程序！");
			}
		}else{
			//录入的不是整数
			System.out.println("录入的除数不是整数，程序退出");
			System.exit(1); //退出JVM（传的参数只要是非0的整数，就表示退出JVM）
		}
	}
}
