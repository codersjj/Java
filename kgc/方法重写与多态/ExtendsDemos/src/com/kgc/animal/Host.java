package com.kgc.animal;
//主人类
public class Host {
	//赠送动物：将父类类型作为方法的返回值
	public Animal sendAnimal1(String type){
		Animal animal = null;
		if(type.equals("cat")){
			animal = new Cat(); //也可以写成return new Cat();
		}else if(type.equals("dog")){
			animal = new Dog(); //也可以写成return new Dog();
		}else{
			animal = new Duck(); //也可以写成return new Duck();
		}
		return animal;
	}
	
}
