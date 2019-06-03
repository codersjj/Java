package com.kgc.usb;

//USB风扇
public class UsbFan implements Usb {
	//服务   功能
	public void service() {
		System.out.println("插在USB口上，电扇开始转.......");
	}
}
