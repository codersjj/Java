//import java.util.Scanner;

//不对异常进行处理
public class Test0 {
	public static void main(String[] args) {
		/*Scanner in = new Scanner(System.in);
		System.out.print("请输入被除数：");
		int num1 = in.nextInt();
		System.out.print("请输入除数：");
		int num2 = in.nextInt();
		System.out.println(num1 + "/" + num2 + "=" + num1 / num2);
		System.out.println("感谢使用本程序！");*/
		try{
			int[] nums = new int[2];
			System.out.println(nums[7]);
		}catch(ArrayIndexOutOfBoundsException e){
			e.printStackTrace();
			System.out.println("*******");
			System.out.println(e.getMessage());
		}
//		运行结果：
//			java.lang.ArrayIndexOutOfBoundsException: 7
//				at Test0.main(Test0.java:15)
//			*******
//			7
//		可以看到，getMessage()获得的是冒号后面的内容。
	}
}
