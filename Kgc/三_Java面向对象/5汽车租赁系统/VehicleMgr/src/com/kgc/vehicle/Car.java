package com.kgc.vehicle;

//轿车类
public class Car extends MotorVehicle {
	//型号
	private String type;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Car() {} //无参构造方法

	public Car(String vehicleId, String brand, int perRent, String type) { //带参构造方法，参数为继承来的3个父类的属性和一个自己的属性。
		super(vehicleId, brand, perRent); //调用父类的带参构造方法
		this.type = type;
	}

	// 重写父类的计算租金方法，根据自己的计算租金规则来重写。
	public float calcRent(int days) {
		float price = this.getPerRent() * days;
		if(days > 7 && days <= 30){
			price *= 0.9f; // 0.9默认是double类型，double类型 * float类型 = double类型，而double类型不能赋值给float类型，因为double范围更大。所以这里加上“f”，将小数转换为float类型。
		}else if(days > 30 && days <= 150){
			price *= 0.8f; 
		}else if(days > 150){
			price *= 0.7f; 
		}
		return price;
	}

}
