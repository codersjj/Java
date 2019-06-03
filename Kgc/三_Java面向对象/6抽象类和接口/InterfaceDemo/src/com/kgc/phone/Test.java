package com.kgc.phone;

public class Test {
	public static void main(String[] args) {
		CommonPhone common = new CommonPhone("索尼爱立信", "G520");
		common.call();
		common.sendMess();
		common.playVideo("崇拜");
		common.showInfo();
		
		System.out.println("*******");
		
		SmartPhone smart = new SmartPhone("华为", "P30");
		smart.call();
		smart.sendMess();
		smart.playVideo("知否");
		smart.netWork();
		smart.takePhotos();
		smart.showInfo();
	}

}
