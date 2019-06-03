package com.kgc.mgr;

import java.util.Scanner;

import com.kgc.vehicle.MotorVehicle;

//汽车租赁管理类：测试类
public class RentMgr {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		MotorOperation motoMgr = new MotorOperation();
		//租赁公司首先需要购置汽车
		motoMgr.init();
		System.out.println("*******欢迎光临租赁公司*******");
		System.out.println("1.轿车\t2.客车");
		System.out.println("请选择您要租赁的汽车类型：");
		int motoType = input.nextInt();
		//客户租车的条件
		String brand = ""; //品牌
		String type = ""; //型号
		int seat = 0; //座位数
		//收集用户条件
		if(motoType == 1){
			//租赁轿车
			System.out.println("请选择您要租赁的轿车品牌：1.别克\t2.宝马");
			int choose = input.nextInt();
			if(choose == 1){
				brand = "别克";
				System.out.println("请选择您要租赁的汽车型号：1.林荫大道\t2.GL8");
				type = (input.nextInt() == 1) ? "林荫大道" : "GL8";
			}else if(choose == 2){ //这里不写else，而写成else if是为了后期便于扩展。
				brand = "宝马";
				System.out.println("请选择您要租赁的汽车型号：1.X6\t2.550i");
				type = (input.nextInt() == 1) ? "X6" : "550i";
			}
		}else if(motoType == 2){
			//租赁客车
			type = ""; //客车没有型号
			System.out.println("请选择您要租赁的客车品牌：1.金杯\t2.金龙");
			brand = (input.nextInt() == 1) ? "金杯" : "金龙";
			System.out.println("请选择您要租赁的客车座位数：1.16座\t2.34座");
			seat = (input.nextInt() == 1) ? 16 : 34;
		}
		//租车
		MotorVehicle motor = motoMgr.motorLeaseOut(brand, type, seat);
		System.out.println("请输入您的租赁天数：");
		int days = input.nextInt();
		float money = motor.calcRent(days); // 多态   动态绑定（虽然motor是父类类型，但会根据具体子类对象去调用重写以后的相应方法）
		System.out.println("租车成功，请按照如下车牌号提车：" + motor.getVehicleId());
		System.out.println("您需要支付：" + money + "元");
	}

}
