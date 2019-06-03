package com.kgc.usb;

//U盘
public class UsbDisk implements Usb {

	@Override
	public void service() {
		System.out.println("插上U盘，开始传输数据.......");
	}

}
