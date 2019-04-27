package com.flc.controller.master.master;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.flc.controller.base.BaseController;
import com.flc.entity.Page;
import com.flc.util.AppUtil;
import com.flc.util.ObjectExcelView;
import com.flc.util.PageData;
import com.flc.util.Jurisdiction;
import com.flc.util.Tools;
import com.flc.service.detail.detail.DetailManager;
import com.flc.service.master.master.MasterManager;

/** 
 * 说明：订单模块
 * 创建人：Shane
 * 创建时间：2019-1-27
 */
@Controller
@RequestMapping(value="/master")
public class MasterController extends BaseController {
	
	String menuUrl = "master/list.do"; //菜单地址(权限用)
	//订单操作
	@Resource(name="masterService")
	private MasterManager masterService;
	//订单详情操作
	@Resource(name="detailService")
	private DetailManager detailService;
	
	
	
	
	
	
	/**删除
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"删除Master");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		
		try {
			//删除该订单的详情信息
			detailService.deleteDetailByOrderId(pd);
			//删除订单
			masterService.delete(pd);
		} catch (Exception e) {
			// TODO: handle exception
		}
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改Master");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		masterService.edit(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"列表Master");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		//拼接日期
		String lastStart =  pd.get("lastStart")+":00";
		String endTime =  pd.get("lastEnd")+":00";
		
		//订单状态
		String ORDER_STATUS = pd.getString("ORDER_STATUS");
		
		
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		
		//开始时间
		if(null != pd.get("lastStart") && !"".equals(pd.get("lastStart"))){
			pd.put("lastStart", lastStart);
		}
		//结束时间
		if(null != pd.get("lastEnd") && !"".equals(pd.get("lastEnd"))){
			pd.put("lastEnd", endTime);
		}
		
		//订单状态
		if(null != ORDER_STATUS && !"".equals(ORDER_STATUS)){
			pd.put("ORDER_STATUS", ORDER_STATUS);
		}
		
		page.setPd(pd);
		List<PageData>	varList = masterService.list(page);	//列出Master列表
		mv.setViewName("master/master/master_list");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
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
		pd = masterService.findById(pd);	//根据ID读取
		mv.setViewName("master/master/master_edit");
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		return mv;
	}
	
	
	/**
	 * 查看详情
	 * @param binder
	 */
	@RequestMapping(value="/golookMore",produces = "text/plain;charset=utf-8")
	public ModelAndView lookMore() throws Exception{
		
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		List<PageData> details = detailService.findDetailListByOrderId(pd);	//根据ID读取
		mv.addObject("details", details);
		mv.setViewName("detail/detail/detail_edit");
		mv.addObject("ORDER_AMOUNT", pd.get("ORDER_AMOUNT"));
		mv.addObject("BUYER_PHONE", pd.get("BUYER_PHONE"));
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		return mv;
	}
	
	
	 /**批量删除
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"批量删除Master");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			masterService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, Jurisdiction.getUsername()+"导出Master到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("订单编号");	//1
		titles.add("预定用户名");	//2
		titles.add("预定手机号");	//3
		titles.add("预计到达时间");	//4
		titles.add("用户的微信openID");	//5
		titles.add("订单总金额");	//6
		titles.add("订单状态");	//7
		titles.add("创建时间");	//8
		titles.add("备注");	//9
		titles.add("创建人");	//10
		dataMap.put("titles", titles);
		List<PageData> varOList = masterService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("ORDER_ID"));	    //1
			vpd.put("var2", varOList.get(i).getString("BUYER_NAME"));	    //2
			vpd.put("var3", varOList.get(i).getString("BUYER_PHONE"));	    //3
			vpd.put("var4", varOList.get(i).get("BUYER_GOTIME").toString());	    //4
			vpd.put("var5", varOList.get(i).getString("BUYER_OPENID"));	    //5
			vpd.put("var6", varOList.get(i).get("ORDER_AMOUNT").toString());	    //6
			vpd.put("var7", varOList.get(i).get("ORDER_STATUS").toString());	//7
			vpd.put("var8", varOList.get(i).get("CREATE_TIME").toString());	    //8
			vpd.put("var9", varOList.get(i).getString("REMARK"));	    //9
			vpd.put("var10", varOList.get(i).getString("CREATE_BY"));	    //10
			varList.add(vpd);
		}
		dataMap.put("varList", varList);
		ObjectExcelView erv = new ObjectExcelView();
		mv = new ModelAndView(erv,dataMap);
		return mv;
	}
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
