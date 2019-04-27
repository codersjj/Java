package com.flc.controller.weixinuserinfo;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;


/**
 * xml解析类 消息处理类
 * @author Shane
 *
 */
public class XMLUtil {
	public static final String MESSAGE_TEXT = "text";
	public static final String MESSAGE_IMAGE = "image";
    public static final String MESSAGE_VOICE = "voice";
    public static final String MESSAGE_VIDEO = "video";
    public static final String MESSAGE_LINK = "link";
    public static final String MESSAGE_LOCATION = "location";
    public static final String MESSAGE_EVENT = "event";

    public static final String EVENT_SUB = "subscribe";
    public static final String EVENT_UNSUB = "unsubscribe";
    public static final String EVENT_CLICK = "CLICK";
    public static final String EVENT_VIEW = "VIEW";
	
	
	/**
     * 解析微信发来的xml数据请求，并将结果以Map格式返回
     */
    public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {

    	
        Map<String, String> map = new HashMap<String, String>();
        //从request中取得输入流
        InputStream inputStream = request.getInputStream();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();
        // 遍历所有子节点，将内容封装至map中
        for (Element e : elementList) {
           // System.out.println(e.getName() + "|" + e.getText());
            map.put(e.getName(), e.getText());
        }
        // 释放资源
        inputStream.close();
        return map;
    }

    
    /**
     * 构造文本消息
     *
     * @param map  封装了解析结果的Map
     * @param content 文本消息内容
     * @return 文本消息XML字符串
     */
    private static String buildTextMessage(Map<String, String> map, String content) {
        //发送方帐号  用户openID
        String fromUserName = map.get("FromUserName");
        
        //接收方微信号
        String toUserName = map.get("ToUserName");
        String time = new Date().toString();
        /**
         * 文本消息的XML数据格式
         * <xml>
         *     <ToUserName><![CDATA[ToUser]]></ToUserName>
         *     <FromUserName><![CDATA[FromUser]]></FromUserName>
         *     <CreateTime><![CDATA[Text]]></CreateTime>
         *     <Content><![CDATA[文本消息]]></Content>
         *     <MsgId>12345</MsgId>
         * </xml>
         */
        return String.format(
                "<xml>" +
                        "<ToUserName><![CDATA[%s]]></ToUserName>" +
                        "<FromUserName><![CDATA[%s]]></FromUserName>" +
                        "<CreateTime>%s</CreateTime>" +
                        "<MsgType><![CDATA[text]]></MsgType>" +
                        "<Content><![CDATA[%s]]></Content>" +
                 "</xml>",
                fromUserName, toUserName, time, content);
    }

    
    /**
     * 构造图片消息
     *
     * @param map     封装了解析结果的Map
     * @param mediaId 通过素材管理接口上传多媒体文件得到的id
     * @return 图片消息XML字符串
     */
    private static String buildImageMessage(Map<String, String> map, String mediaId) {
        //发送方帐号
        String fromUserName = map.get("FromUserName");
        //接收方微信号
        String toUserName = map.get("ToUserName");

        String time = String.valueOf(new Date().getTime());
        /**
         * 图片消息XML数据格式
         *
         * <xml>
         *     <ToUserName><![CDATA[toUser]]></ToUserName>
         *     <FromUserName><![CDATA[fromUser]]></FromUserName>
         *     <CreateTime>12345678</CreateTime>
         *     <MsgType><![CDATA[image]]></MsgType>
         *     <Image></Image><MediaId><![CDATA[media_id]]></MediaId></Image>
         * </xml>
         */
        return String.format(
                "<xml>" +
                        "<ToUserName><![CDATA[%s]]></ToUserName>" +
                        "<FromUserName><![CDATA[%s]]></FromUserName>" +
                        "<CreateTime>%s</CreateTime>" +
                        "<MsgType><![CDATA[image]]></MsgType>" +
                        "<Image>" +
                        "   <MediaId><![CDATA[%s]]></MediaId>" +
                        "</Image>" +
                        "</xml>",
                fromUserName, toUserName, time, mediaId);
    }
    
    
    
    
    
    /**
     * 接收到消息后处理回复
     * 返回封装好的xml格式数据给用户
     */
    public static String handleMessage(Map<String, String> map) {
        //响应消息
        String responseMessage = "";
        //用户发送的消息内容
        String content = map.get("Content");
        //消息类型
        String msgType = map.get("MsgType").toString().toLowerCase();
        if (msgType.equals("text")) {//发送消息文本
            switch (content) {
                case "1":
                    String msgText = "Hello！我是章鱼猫，我有猫的脑袋、章鱼的身体哦！";
                    responseMessage = buildTextMessage(map, msgText);
                    break;
                case "2":
                    msgText = "我是一只超萌的吉祥物(✪ω✪)";
                    responseMessage = buildTextMessage(map, msgText);
                    break;
                default:
                    msgText = XMLUtil.menuText();
                    responseMessage = buildTextMessage(map, msgText);
                    break;
            }
        }
        else if (msgType.equals("event")) {//首次关注
        	 String msgText = XMLUtil.menuText();
             responseMessage = buildTextMessage(map, msgText);
        } 
        else if (msgType.equals("image")) {
        	String imgMediaId = "1MSZoSCHW_C4vp2jbAc4zVtyiFm3q39mVhl1bhFPJJ2wqYFkiDe67zYYT-Cnn_dL";
            responseMessage = buildImageMessage(map, imgMediaId);
        }
        //返回响应消息
        return responseMessage;
    }

    
    //默认回复的信息
    public static String menuText(){
        StringBuffer sb = new StringBuffer();
        sb.append("	你关注，\n");
        sb.append("	或者不关注，\n");
        sb.append("	章鱼猫都在这里!\n");
        sb.append(" 说好的一辈子，\n");
        sb.append(" 少一秒，\n");
        sb.append("	都不算!\n\n");
        sb.append("	1: 猜猜我是谁\n\n");
        sb.append("	2: 悄悄告诉你\n\n");
        sb.append("	其他: 每天都要开心哦！\n\n");
        return sb.toString();       
    }
   
}
