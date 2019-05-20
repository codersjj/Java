package com.kgc.vehicle;

//汽车类
public abstract class MotorVehicle {
	//车牌号   品牌   日租金
	private String vehicleId;
	private String brand;
	private int perRent;
	
	public MotorVehicle(){	// 无参构造方法
		
	}
	
	//右击-->Source（Alt+Shift+S）-->Generate Constructor using Fields 根据属性生成构造器（构造方法）
	public MotorVehicle(String vehicleId, String brand, int perRent) { // 带参构造方法
//		super(); //调用父类的无参构造
		this.vehicleId = vehicleId;
		this.brand = brand;
		this.perRent = perRent;
	}

	//右击-->Source（Alt+Shift+S）-->Generate Getters and Setters-->Select All-->OK
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getPerRent() {
		return perRent;
	}
	public void setPerRent(int perRent) {
		this.perRent = perRent;
	}
	
	//计算租金（抽象方法）
	public abstract float calcRent(int days);
	
	
}
