package com.kgc.goods;

//工厂类：根据用户提供的条件返回相应商品
//设计模式：简单工厂模式（Simple Factory），就是由一个工厂类根据客户传入的参数，决定创建哪一类产品。
//工厂类   抽象产品类   具体产品类   客户
public class Factory {
	public static Goods getGoods(String type){
		Goods goods = null;
		if(type.equals("TV")){
			goods = new Tvs();
		}else{
			goods = new Foods();
		}
		return goods;
	}
}
