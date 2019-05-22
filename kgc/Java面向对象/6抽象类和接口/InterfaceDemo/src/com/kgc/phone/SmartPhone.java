package com.kgc.phone;

public class SmartPhone extends HandSet implements TakePhoto, Networking, Playing {

	public SmartPhone(){}
	
	public SmartPhone(String brand, String type){
		super(brand, type);
	}
	
	public void sendMess() {
		System.out.println("发送文字+图片+视频的信息");
	}

	public void call() {
		System.out.println("视频通话");
	}

	public void playVideo(String name) {
		System.out.println("播放视频《"+ name +"》");
	}

	public void netWork() {
		System.out.println("上网");
	}

	public void takePhotos() {
		System.out.println("拍照");
	}

}
