package com.kgc.phone;

//普通手机类
public class CommonPhone extends HandSet implements Playing {

	public CommonPhone(){}
	
	public CommonPhone(String brand, String type){
		super(brand, type);
	}
	
	@Override
	public void playVideo(String name) {
		// TODO Auto-generated method stub
		System.out.println("播放音频：《"+ name +"》");
	}

	@Override
	public void sendMess() {
		// TODO Auto-generated method stub
		System.out.println("发送文字短信");
	}

	@Override
	public void call() {
		// TODO Auto-generated method stub
		System.out.println("语音通话");
	}
	
}
