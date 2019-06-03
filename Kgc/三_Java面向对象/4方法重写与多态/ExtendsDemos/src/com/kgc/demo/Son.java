package com.kgc.demo;

public class Son extends Father {
	int var = 90;
	static int staticVar = 100;
	
	public void m1(){
		System.out.println("son类的普通 方法m1");
	}
	
	public static void staticM2(){
		System.out.println("son类的静态方法staticM2");
	}
}
