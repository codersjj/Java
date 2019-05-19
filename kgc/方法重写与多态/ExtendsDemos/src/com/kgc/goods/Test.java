package com.kgc.goods;

public class Test {
	public static void main(String[] args) {
		Goods goods = Factory.getGoods("TV");
		goods.printPrice();
	}
}
