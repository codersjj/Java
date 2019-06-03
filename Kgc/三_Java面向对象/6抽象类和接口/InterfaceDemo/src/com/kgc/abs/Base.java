package com.kgc.abs;

//父类
public abstract class Base { // 一个文件里只能有一个public类，而且这个public类的名字必须和文件名一样。
	public Base() {
		System.out.println("父类的无参构造方法");
	}
	
	public static void main(String[] args) { // main方法要写在公共的类里
		Base b = new Sub(); // 父类引用指向子类对象
	}
}

// 子类
class Sub extends Base { // 一个文件里可以有多个普通的class，
	public Sub() {
		System.out.println("子类的无参构造方法"); // 执行有继承关系的构造方法时，会先执行父类的构造方法，再执行子类的构造方法。
	}
}