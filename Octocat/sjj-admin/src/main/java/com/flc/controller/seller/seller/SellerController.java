package com.flc.controller.seller.seller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.flc.controller.base.BaseController;
import com.flc.entity.Page;
import com.flc.util.AppUtil;
import com.flc.util.ObjectExcelView;
import com.flc.util.PageData;
import com.flc.util.Jurisdiction;
import com.flc.util.Tools;
import com.google.gson.Gson;
import com.flc.service.seller.seller.SellerManager;

/** 
 * 说明：商家管理
 * 创建人：Shane
 * 创建时间：2019-01-27
 */
@Controller
@RequestMapping(value="/seller")
public class SellerController extends BaseController {
	
	String menuUrl = "seller/list.do"; //菜单地址(权限用)
	@Resource(name="sellerService")
	private SellerManager sellerService;
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save(HttpServletRequest request) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增Seller");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		
		
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("ID", this.get32UUID());	//商家编号 主键
		pd.put("CREATE_TIME", new Date());	//创建时间
		pd.put("REMARK", "");	//备注信息
		pd.put("CREATE_BY", Jurisdiction.getUsername());	//创建人
		
		String startHour = request.getParameter("startHour");
		String startMins = request.getParameter("startMins");
		pd.put("OPENSTART",startHour+":"+startMins);
		
		String endHour = request.getParameter("endHour");
		String endMins = request.getParameter("endMins");
		pd.put("OPENEND", endHour+":"+endMins);
		
		sellerService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**删除
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"删除Seller");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		sellerService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(HttpServletRequest request) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改Seller");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		String startHour = request.getParameter("startHour");
		String startMins = request.getParameter("startMins");
		pd.put("OPENSTART",startHour+":"+startMins);
		
		String endHour = request.getParameter("endHour");
		String endMins = request.getParameter("endMins");
		pd.put("OPENEND", endHour+":"+endMins);
		
		sellerService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Seller");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		page.setPd(pd);
		List<PageData>	varList = sellerService.list(page);	//列出Seller列表
		mv.setViewName("seller/seller/seller_list");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	/**去新增页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("seller/seller/seller_edit");
		mv.addObject("msg", "save");
		mv.addObject("pd", pd);
		return mv;
	}	
	
	 /**去修改页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = sellerService.findById(pd);	//根据ID读取
		mv.setViewName("seller/seller/seller_edit");
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		//开始时间
		String startTime = pd.getString("OPENSTART");
		//结束时间
		String endTime = pd.getString("OPENEND");
		//获取开始小时数
		String startHour = startTime.substring(0,2);
		//获取开始分钟数
		String startMin = startTime.substring(3,5);
		//获取结束小时数
		String endHour = endTime.substring(0,2);
		//获取结束分钟数
		String endMin = endTime.substring(3,5);
		
		
		mv.addObject("startHour", startHour);
		mv.addObject("startMin", startMin);
		mv.addObject("endHour", endHour);
		mv.addObject("endMin", endMin); 
		return mv;
	}	
	
	 /**批量删除
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"批量删除Seller");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			sellerService.deleteAll(ArrayDATA_IDS);
			pd.put("msg", "ok");
		}else{
			pd.put("msg", "no");
		}
		pdList.add(pd);
		map.put("list", pdList);
		return AppUtil.returnObject(pd, map);
	}
	
	 /**导出到excel
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"导出Seller到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("商家编号");	//1
		titles.add("商家店名");	//2
		titles.add("地址");	//3
		titles.add("商家公告");	//4
		titles.add("创建时间");	//5
		titles.add("备注信息");	//6
		titles.add("创建人");	//7
		titles.add("营业开始时间");	//8
		titles.add("营业结束时间");	//9
		dataMap.put("titles", titles);
		List<PageData> varOList = sellerService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("ID"));	    //1
			vpd.put("var2", varOList.get(i).getString("NAME"));	    //2
			vpd.put("var3", varOList.get(i).getString("ADDRESS"));	    //3
			vpd.put("var4", varOList.get(i).getString("ANNOUNCEMENT"));	    //4
			vpd.put("var5", varOList.get(i).getString("CREATE_TIME"));	    //5
			vpd.put("var6", varOList.get(i).getString("REMARK"));	    //6
			vpd.put("var7", varOList.get(i).getString("CREATE_BY"));	    //7
			vpd.put("var8", varOList.get(i).getString("OPENSTART"));	    //8
			vpd.put("var9", varOList.get(i).getString("OPENEND"));	    //9
			varList.add(vpd);
		}
		dataMap.put("varList", varList);
		ObjectExcelView erv = new ObjectExcelView();
		mv = new ModelAndView(erv,dataMap);
		return mv;
	}
	
	
	
	
	
	
	/**
	 * 修改营业状态
	 * @param binder
	 * @throws Exception 
	 */
	@RequestMapping("/updateIsOpen")
	@ResponseBody
	public String updateIsOpen(@RequestParam String Id,@RequestParam String isOpen) throws Exception{
		PageData pd = new PageData();
		pd.put("Id", Id);
		pd.put("isOpen", isOpen);
		int result = sellerService.updateIsOpen(pd);
		return new Gson().toJson(result);
	}
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
