package com.kgc.usb;

//USB接口
public interface Usb {
	//服务   功能
	public void service(); // 接口中的所有方法默认都是public abstract，不写也可以。
	
	//接口是什么？
	//1、概念性的接口：系统能对外提供的所有服务（功能）都叫接口（比如上面的service()）
	//2、实在的接口：用关键字interface修饰的就叫接口（比如上面的Usb）
}
