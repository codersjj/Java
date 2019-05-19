package com.kgc.demo;

public class Test {
	public static void main(String[] args) {
		//绑定机制：静态绑定   动态绑定
		//实例方法（动态绑定机制）   在调用时遵循的是Java里的动态绑定机制   与引用变量实际引用的对象绑定，调用重写后的方法，由运行时的JVM决定。
		//静态方法（静态绑定机制）   与引用变量所声明的类型绑定，实际上在编译阶段就做了绑定。
		//成员变量（包括静态变量和实例变量（即非静态变量，普通的变量），静态绑定机制）   与引用变量所声明的类型绑定，在编译阶段就做了绑定。
		
		Father f = new Son(); //假设Father为父类，Son为子类
		System.out.println(f.var); //假设var为实例变量，属于成员变量，打印的值属于静态绑定机制，和类型绑定，结果由父类Father里的值确定。
		System.out.println(f.staticVar); //假设staticVar为静态变量，属于成员变量，打印的值属于静态绑定机制，和类型绑定，结果由父类Father里的值确定
		f.m1(); //m1为实例方法，属于动态绑定机制，和对象绑定，结果为调用子类里被重写以后的m1方法。
		f.staticM2(); //staticM2为静态方法，属于静态绑定机制，和类型绑定，结果为调用父类Father里的静态方法staticM2。
	}
}
