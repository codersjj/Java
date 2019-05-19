package com.kgc.goods;

//工厂类：根据用户提供的条件返回相应商品
public class Factory {
	public Goods getGoods(String type){
		Goods goods = null;
		if(type.equals("TV")){
			goods = new Tvs();
		}else{
			goods = new Foods();
		}
		return goods;
	}
}
