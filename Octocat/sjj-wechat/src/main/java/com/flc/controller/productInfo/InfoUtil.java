package com.flc.controller.productInfo;

import java.io.Serializable;

/**
 * 工具类 保存所有有数量的商品信息 模拟购物车
 * @author 小诚
 *
 */
public class InfoUtil implements Serializable{
	private String infoId;
	public String getInfoId() {
		return infoId;
	}
	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}
	private String name;
	private String price;
	private String num;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	
}
