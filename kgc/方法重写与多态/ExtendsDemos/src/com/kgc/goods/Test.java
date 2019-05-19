package com.kgc.goods;

public class Test {
	public static void main(String[] args) {
		Factory factory = new Factory();
		Goods goods = factory.getGoods("TV");
		goods.printPrice();
	}
}
