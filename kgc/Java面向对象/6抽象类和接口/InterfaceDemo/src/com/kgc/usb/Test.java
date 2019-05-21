package com.kgc.usb;

public class Test {
	public static void main(String[] args) {
		Usb fan = new UsbFan(); // 定义一个接口类型的变量，Usb为接口，UsbFan()为接口的实现类，可以理解为父类引用指向子类对象。
		fan.service(); // 多态   动态绑定（会根据具体的子类是什么类型，去调用对应的重写以后的方法，而不是调用父类的方法）
		
		Usb disk = new UsbDisk();
		disk.service();
	}
}
