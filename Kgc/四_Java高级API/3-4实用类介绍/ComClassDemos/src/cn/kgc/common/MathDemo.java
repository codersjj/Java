package cn.kgc.common;

import java.util.Scanner;

public class MathDemo {
	public static void main(String[] args) {
//		System.out.println((int)(Math.random() * 10)); // Math.random()随机生成[0,1)之间的浮点数
		
		// 会员随机抽奖：会员号百位数等于计算机随机生成的数字时中奖
		Scanner input = new Scanner(System.in);
		System.out.print("请输入一个四位数的会员号：");
		int num;
		if(input.hasNextInt()){
			num = input.nextInt();			
		}else{
			System.out.println("请输入整数！");
			return;
		}
		if(num < 1000 || num > 9999){
			System.out.println("请输入4位整数！");
			return;
		}
		int baiwei = num / 100 % 10;
		int random = (int)(Math.random() * 10);
		System.out.println(random);
		if(baiwei == random){
			System.out.println("恭喜您！中奖了！");
		}else{
			System.out.println("很抱歉，您未中奖！");
		}
	}
}
