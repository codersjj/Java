package com.flc.controller.details.detail;

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
import com.flc.util.Tools;
import com.flc.service.details.detail.DetailManager;

/** 
 * 说明：订单详情
 * 创建人：Shane
 * 修改时间：2019年1月27日
 */
@Controller
@RequestMapping(value="/detail")
public class DetailController extends BaseController {
	
	String menuUrl = "detail/list.do"; //菜单地址(权限用)
	@Resource(name="detailService")
	private DetailManager detailService;
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("DETAIL_ID", this.get32UUID());	//主键
		pd.put("DETAIL_ID", "");	//订单详情ID
		pd.put("ORDER_ID", "");	//订单ID外键
		pd.put("PRODUCT_ID", "");	//商品ID外键
		pd.put("PRODUCT_NAME", "");	//商品名称
		pd.put("PRODUCT_QUANTITY", "0");	//商品数量
		pd.put("CREATE_TIME", "");	//创建时间
		pd.put("REMARK", "");	//备注信息
		pd.put("CREATE_BY", "");	//创建人
		detailService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
}
