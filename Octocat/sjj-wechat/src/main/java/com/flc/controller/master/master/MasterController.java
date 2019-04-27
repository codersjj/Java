package com.flc.controller.master.master;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.flc.controller.base.BaseController;
import com.flc.service.details.detail.DetailManager;
import com.flc.service.master.master.MasterManager;
import com.flc.service.productInfo.ProductInfoService;
import com.flc.service.seller.seller.SellerManager;
import com.flc.util.PageData;

/** 
 * 说明：master
 * 创建人：Shane
 * 修改时间：2019年1月27日
 */
@Controller
@RequestMapping(value="/master")
public class MasterController extends BaseController {
	//商品操作类
	@Autowired
	ProductInfoService proService;
	//订单详情操作对象
	@Resource(name="detailService")
	private DetailManager detailService;
	
	@Resource(name="masterService")
	private MasterManager masterService;
	
	//商家信息查询操作对象
	@Resource(name="sellerService")
	private SellerManager sellerService;
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/saveDingDan")
	public ModelAndView save(HttpServletRequest request) throws Exception{
		
		PageData pd = new PageData();
		//获取参数
		pd = this.getPageData();
		ModelAndView mv = this.getModelAndView();
		//进行数据库订单的添加
		StringBuffer sb = new StringBuffer();
		//根据日期生成订单时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sb.append(sdf.format(new Date()).toString()+" ");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		sb.append(pd.get("hour")+":"+pd.getString("minute"));
		Date date = sdf2.parse(sb.toString()+":00");
		pd.put("BUYER_GOTIME", date);//预计到达时间
		pd.put("ORDER_STATUS", 0);//订单状态 默认0 是否完成订单 初始化为0 未完成
		pd.put("ORDER_AMOUNT", Double.parseDouble(pd.getString("sumPrice")));
		pd.put("ORDER_ID", this.get32UUID());//主键
		pd.put("CREATE_TIME", new Date());
		pd.put("BUYER_OPENID", request.getSession().getAttribute("openId"));

		//年 日期 分 秒 随机4位
		Date date1 = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String orderNum = dateFormat.format(date1)+(int)((Math.random()*9+1)*1000);
		pd.put("ORDER_NUM", orderNum);//订单编号
		masterService.save(pd);//进行订单添加操作
		
		
		//获取值
		String sumPrice = this.getRequest().getParameter("sumPrice");
		//购物车中的食物ID
		String[] ids = this.getRequest().getParameterValues("infoId");
		String[] nums = this.getRequest().getParameterValues("nums");
		
		//购物车每个食物对应的数量
		
		for (int i = 0; i < nums.length; i++) {
			//添加订单中详情信息
			PageData pd3 = new PageData();
			pd3.put("DETAIL_ID", this.get32UUID());
			pd3.put("ORDER_ID", pd.get("ORDER_ID"));
			//商品ID外键由订单生成 故不用赋值
			pd3.put("PRODUCT_ID", ids[i]);
			
			pd3.put("PRODUCT_QUANTITY", nums[i]);//商品数量
			//用于查询出指定的食物名 以及单价
			PageData pd1 = new PageData();
			pd1.put("PRODUCT_ID", ids[i]);
			//根据上商品ID查出的对应的商品
			PageData pd2 = proService.findById(pd1);
			pd3.put("PRODUCT_ID", ids[i]);
			pd3.put("PRODUCT_NAME", pd2.get("PRODUCT_NAME"));
			pd3.put("PRODUCT_PRICE", pd2.get("PRODUCT_PRICE"));
			//备注和创建人不用赋值 可以为空
			//添加
			detailService.save(pd3);
		}
		mv.setViewName("ok");
		return mv;
	}
	
	//开始预定的页面
	@RequestMapping(value="/goDownDan")
	public String goDownDan(Model model) throws Exception{
		//model.addAttribute("sumPrice", sumPrice);
		//获取当前系统时间
		Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		model.addAttribute("hour", hour);
		model.addAttribute("minute", minute);
		
		
		
		
		String sumPrice = this.getRequest().getParameter("sumPrice");
		//购物车中的食物ID
		String[] ids = this.getRequest().getParameterValues("infoId");
		//购物车每个食物对应的数量
		String[] nums = this.getRequest().getParameterValues("nums");
		//总金额
		model.addAttribute("sumPrice", sumPrice);
		model.addAttribute("ids", ids);
		model.addAttribute("nums", nums);
		
		PageData pd = (PageData) this.getRequest().getSession().getAttribute("seller");
		String yingYeStarthour = (String) pd.get("OPENSTART");
		//拿到营业开始小时数
		if(yingYeStarthour.substring(0,1).equals("0")){
			yingYeStarthour=yingYeStarthour.substring(1,2);
		}else{
			yingYeStarthour=yingYeStarthour.substring(0,2);
		}
		
		String yingYeEndhour = (String) pd.get("OPENEND");
		//拿到营业结束小时数
		if(yingYeEndhour.substring(0,1).equals("0")){
			yingYeEndhour=yingYeEndhour.substring(1,2);
		}else{
			yingYeEndhour=yingYeEndhour.substring(0,2);
		}
		//保存营业开始时间和营业结束时间去选择用户的选择小时
		model.addAttribute("yingYeStarthour", yingYeStarthour);
		model.addAttribute("yingYeEndhour", yingYeEndhour);
		return "submit";
	}
	
	//显示预定成功页面
	@RequestMapping(value="/ok")
	public String goOk(){
		return "ok";
				
	}
	
	
	
}
