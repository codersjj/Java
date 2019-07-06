package cn.kgc.randomdemo;

import java.util.Random;

public class RandomDemo {
	public static void main(String[] args) {
		// 种子 每次都不一样
		// 不同的种子构造的Random对象生成的随机数是不同的（使用不同的种子可以通过Random()或Random(时间的毫秒)来生成）
		Random random = new Random();
		Random random2 = new Random();
		System.out.println(random.nextInt(10));
		System.out.println(random2.nextInt(10));
		// 相同的种子构造的Random对象生成的随机数是一样的
		Random random3 = new Random(27);
		Random random4 = new Random(27);
		System.out.println(random3.nextInt(10));
		System.out.println(random4.nextInt(10));
		/*for(int i = 0; i < 10; i++){
			//以int为例
			// 不带参数，随机生成一个int类型的整数（-2^31~2^31-1）
			System.out.println(random.nextInt());
			// 带参数，随机生成一个范围在[0,该整数)之间的整数
			System.out.println(random.nextInt(10));
		}*/
	}
}
