package com.kgc.vehicle;

//客车类
public class Bus extends MotorVehicle {
	//座位数
	private int seatCount;
	
	public int getSeatCount() {
		return seatCount;
	}

	public void setSeatCount(int seatCount) {
		this.seatCount = seatCount;
	}

	public Bus() {} //无参构造方法

	public Bus(String vehicleId, String brand, int perRent, int seatCount) { //带参构造方法，参数为继承来的3个父类的属性和一个自己的属性。
		super(vehicleId, brand, perRent); //调用父类的带参构造方法
		this.seatCount = seatCount;
	}

	// 重写父类的计算租金方法，根据自己的计算租金规则来重写。
	public float calcRent(int days) {
		float price = this.getPerRent() * days;
		if(days >= 3 && days < 7){
			price *= 0.9f; // 0.9默认是double类型，double类型 * float类型 = double类型，而double类型不能赋值给float类型，因为double范围更大。所以这里加上“f”，将小数转换为float类型。
		}else if(days >=7 && days < 30){
			price *= 0.8f; 
		}else if(days >= 30 && days < 150){
			price *= 0.7f; 
		}else if(days >= 150){
			price *= 0.6f; 
		}
		return price;
	}

}
