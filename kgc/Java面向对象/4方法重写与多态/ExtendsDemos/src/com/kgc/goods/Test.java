package com.kgc.goods;

public class Test {
	public static void main(String[] args) {
		Goods goods = Factory.getGoods("foods");
		goods.printPrice();
		Goods.m1();
	}
}
