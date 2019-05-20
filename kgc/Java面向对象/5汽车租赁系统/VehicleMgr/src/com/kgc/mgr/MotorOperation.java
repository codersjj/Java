package com.kgc.mgr;

import com.kgc.vehicle.Bus;
import com.kgc.vehicle.Car;
import com.kgc.vehicle.MotorVehicle;

//汽车业务类
public class MotorOperation {
	//汽车类型的数组（数组声明为父类类型）
	public MotorVehicle[] motors = new MotorVehicle[8];
	
	//初始化汽车信息
	public void init(){
		motors[0] = new Car("京N78654", "宝马", 800, "X6"); // 相当于MotorVehicle m = new Car(); 父类引用指向子类对象
		motors[1] = new Car("京u78888", "宝马", 600, "550i"); // 相当于MotorVehicle m = new Car(); 父类引用指向子类对象
		motors[2] = new Car("京J65432", "别克", 300, "林荫大道"); // 相当于MotorVehicle m = new Car(); 父类引用指向子类对象
		motors[3] = new Car("京N88964", "别克", 600, "GL8"); // 相当于MotorVehicle m = new Car(); 父类引用指向子类对象
		motors[4] = new Bus("京I33333", "金杯", 800, 16); // 相当于MotorVehicle m = new Bus(); 父类引用指向子类对象
		motors[5] = new Bus("京I22222", "金杯", 1500, 34); // 相当于MotorVehicle m = new Bus(); 父类引用指向子类对象
		motors[6] = new Bus("京I55555", "金龙", 800, 16); // 相当于MotorVehicle m = new Bus(); 父类引用指向子类对象
		motors[7] = new Bus("京I66666", "金龙", 1500, 34); // 相当于MotorVehicle m = new Bus(); 父类引用指向子类对象
	}
	
	//租车：根据用户提供的条件去汽车数组中查找相应车辆并返回
	//如果租赁的是客车，需要的条件：品牌、座位数，型号null
	//如果租赁的是轿车，需要的条件：品牌、型号，座位数0
	public MotorVehicle motorLeaseOut(String brand, String type, int seat){ // 多态，方法返回值是父类类型，但具体返回的是具体子类对象。简单工厂模式
		MotorVehicle motor = null;
		for(MotorVehicle mymotor : motors){ //增强型for，遍历数组，把motors这个数组里的每一个值取出来依次赋值给MotorVehicle类型的变量mymotor。
			if(mymotor instanceof Car){ //用instanceof判断类型
				Car car = (Car)mymotor; //向下转型
				if(car.getBrand().equals(brand) && car.getType().equals(type)){
					motor = car;
					break;
				}
			}else {
				Bus bus = (Bus)mymotor; //向下转型
				if(bus.getBrand().equals(brand) && bus.getSeatCount() == seat){
					motor = bus;
					break;
				}
			}
		}
		return motor;
	}
	
}
