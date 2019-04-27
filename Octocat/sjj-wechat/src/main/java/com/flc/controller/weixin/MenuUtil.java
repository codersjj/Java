package com.flc.controller.weixin;

import java.io.IOException;
import java.nio.charset.UnsupportedCharsetException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.flc.controller.readProperties.ReadProperties;

import net.sf.json.JSONObject;




/**
 * 菜单工具类（组装微信菜单）
 * @author Shane
 *
 */
public class MenuUtil {
	/*private static final String APPID = "wxd0731696da495ea7";
	private static final String APPSECRET = "778eaa949885f371b637afeb99180b3d";*/
	private static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
 
	/**
	 * 组装菜单
	 */
	public static WeiXinMenu initMenu(){
		WeiXinMenu menu = new WeiXinMenu();
		//创建一个
		ViewButton viewbutton1 = new ViewButton();
		viewbutton1.setName("Octocat点餐");
		viewbutton1.setType("view");
		//按钮访问的路径
		viewbutton1.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?"
		+ "appid=wxd0731696da495ea7&redirect_uri="+ReadProperties.getValue("url")+"/sjj-wechat/productInfo/listAll&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect");
		//放入按钮数组
		menu.setButton(new Button[]{viewbutton1});
		return menu;
	}
	
	/**
	 * 创建菜单
	 * @param token
	 * @param menu
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static int createMenu(String token, String menu) throws IOException,Exception {
		int result = 0;
		String url = CREATE_MENU_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObject = doPostStr(url, menu);
		if (jsonObject != null) {
			result = jsonObject.getInt("errcode");
		}
		return result; 	
	}
	
	
	@SuppressWarnings("deprecation")
	private static JSONObject doPostStr(String url, String outStr) throws ClientProtocolException, IOException{
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		JSONObject jsonObject = null;
		try {
			httpPost.setEntity(new StringEntity(outStr, "UTF-8"));
			HttpResponse response = httpClient.execute(httpPost);
			String result = EntityUtils.toString(response.getEntity(),"UTF-8");
			jsonObject = JSONObject.fromObject(result);
		} catch (UnsupportedCharsetException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
	
	
}
