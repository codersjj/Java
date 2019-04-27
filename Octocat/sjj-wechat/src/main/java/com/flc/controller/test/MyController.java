package com.flc.controller.test;

import java.io.IOException;

import com.flc.controller.readProperties.ReadProperties;
import com.flc.controller.weixin.MenuUtil;

import net.sf.json.JSONObject;


public class MyController {
	public static void main(String[] args) throws IOException, Exception {
		//微信菜单验证
		String menu = JSONObject.fromObject(MenuUtil.initMenu()).toString();
		//测试验证token是否成功 菜单生成	
		System.out.println(menu);
		int result = MenuUtil.createMenu(ReadProperties.getValue("access_token"), menu);
		System.out.println(result);
		
		
	}
}
