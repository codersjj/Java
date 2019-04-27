package com.flc.controller.weixin;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flc.controller.weixinuserinfo.XMLUtil;


/**
 * 验证token路径
 * @author Shane
 *
 */
@Controller
@RequestMapping("/weixin")
public class WeixinToken {
	//验证token
	@RequestMapping( value="yanzhengToken" , method = RequestMethod.GET)
    @ResponseBody
    public String verifyWXToken(HttpServletRequest request) throws AesException {
        String msgSignature = request.getParameter("signature");
        String msgTimestamp = request.getParameter("timestamp");
        String msgNonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        if (WXPublicUtils.verifyUrl(msgSignature, msgTimestamp, msgNonce)) {
            return echostr;
        }
        return null;
    }

	
	/**
	 * 订阅和交互进入
	 * @param request
	 * @return
	 * @throws AesException
	 */
	@RequestMapping(value = "/yanzhengToken", method = RequestMethod.POST)
    public void wxCore(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
        request.setCharacterEncoding("UTF-8");
         response.setCharacterEncoding("UTF-8");
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        
        String openId = null;
        String responseMessage="";
        
        
        try {
            //解析微信发来的请求,将解析后的结果封装成Map返回
            Map<String, String> map = XMLUtil.parseXml(request);
            String toUserName = map.get("ToUserName");
            String fromUserName = map.get("FromUserName");
            String msgType = map.get("MsgType");
            String content = map.get("Content");    
            
            
            //用户的openId
            //String openId = map.get("FromUserName");
            //存储用户的openId
            request.getSession().setAttribute("openId", openId);
            responseMessage = XMLUtil.handleMessage(map);
            if (responseMessage.equals("")) {
                responseMessage = "未正确响应";
            } 
            
            
            
            if(XMLUtil.MESSAGE_EVENT.equals(msgType)){
                String eventType = map.get("Event");
                //若是关注事件  subscribe
                if(XMLUtil.EVENT_SUB.equals(eventType)){
                    String mycontent = XMLUtil.menuText();
                    responseMessage = XMLUtil.handleMessage(map);
                }
            } 
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("发生异常：" + e.getMessage());
            responseMessage = "未正确响应";
        }
        //发送响应消息
        response.getWriter().println(responseMessage);
    }

}
