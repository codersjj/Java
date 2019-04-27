package com.flc.controller.readProperties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取配置文件类
 * @author 小诚
 *
 */
public class ReadProperties {
	
	public static String getValue(String key){
		InputStream in = ReadProperties.class.getClassLoader().getResourceAsStream("com/flc/controller/properties/weixin.properties");
				Properties prop = new Properties();  
				try {
					prop.load(in);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
		return prop.getProperty(key);  
	}
	
	
	
}
