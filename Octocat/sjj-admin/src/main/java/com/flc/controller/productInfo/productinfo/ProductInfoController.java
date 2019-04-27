package com.flc.controller.productInfo.productinfo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.flc.controller.base.BaseController;
import com.flc.entity.Page;
import com.flc.service.category.category.CategoryManager;
import com.flc.service.productInfo.productinfo.ProductInfoManager;
import com.flc.util.AppUtil;
import com.flc.util.Jurisdiction;
import com.flc.util.ObjectExcelView;
import com.flc.util.PageData;
import com.mysql.jdbc.Blob;

/** 
 * 说明：水饺详情模块
 * 创建人：Shane
 * 创建时间：2019-1-27
 */
@Controller
@RequestMapping(value="/productinfo")
public class ProductInfoController extends BaseController {
	/**
	 * 所有类型
	 */
	List<PageData>	categoryList=null;
	
	String menuUrl = "productinfo/list.do"; //菜单地址(权限用)
	@Resource(name="productinfoService")
	private ProductInfoManager productinfoService;
	/**
	 * 查出所有的类目名
	 */
	@Resource(name="categoryService")
	private CategoryManager categoryService;
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save(HttpServletRequest request,@RequestParam(value="PRODUCT_INFO",required=false)MultipartFile PRODUCT_INFO) throws Exception{
		String PRODUCT_NAME = request.getParameter("PRODUCT_NAME");
		String PRODUCT_PRICE =request.getParameter("PRODUCT_PRICE");
		String PRODUCT_DESC =request.getParameter("PRODUCT_DESC");
		String PRODUCT_STATUS =request.getParameter("PRODUCT_STATUS");
		
		int CATEGORY_TYPE =Integer.parseInt(request.getParameter("CATEGORY_TYPE"));
		String REMARK =request.getParameter("REMARK");
		
		//封装数据
		PageData pd = new PageData();
		pd.put("PRODUCT_NAME", PRODUCT_NAME);
		pd.put("PRODUCT_PRICE", PRODUCT_PRICE);
		pd.put("PRODUCT_DESC", PRODUCT_DESC);
		pd.put("PRODUCT_STATUS", PRODUCT_STATUS);
		pd.put("CATEGORY_TYPE", CATEGORY_TYPE);
		pd.put("REMARK", REMARK);
		
		
		//文件上传
				String path = request.getSession().getServletContext().getRealPath("static\\images");
				String fileName = PRODUCT_INFO.getOriginalFilename(); 
//		try {
//			String path = request.getSession().getServletContext().getRealPath("static\\myImg");
//	        String fileName = PRODUCT_INFO.getOriginalFilename();  
//	        File dir = new File(path,fileName);        
//	        if(!dir.exists()){
//	            dir.mkdirs();
//	        }
//	        //MultipartFile自带的解析方法
//	        PRODUCT_INFO.transferTo(dir);
//	        
//	        
//	        //封装图片信息、
//	        pd.put("PRODUCT_INFO","static\\myImg\\"+fileName);
//	        	
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}

		try {
            //获取输出流                                                                                      图存放的路径
            OutputStream os=new FileOutputStream(path+"\\"+fileName);
            //获取输入流 CommonsMultipartFile 中可以直接得到文件的流
            InputStream is=PRODUCT_INFO.getInputStream();
            byte[] data = new byte[] {};
            data = inputStreamToByte(is);//将文件保存到字节数组中 
            pd.put("PRODUCT_INFO",data);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }	
		
		
		logBefore(logger, Jurisdiction.getUsername()+"新增ProductInfo");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		
		pd.put("PRODUCT_ID", this.get32UUID());	//主键
		pd.put("CREATE_BY", Jurisdiction.getUsername());	//创建者
		pd.put("CREATE_TIME", new Date());	//创建者
		productinfoService.save(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"删除ProductInfo");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		productinfoService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(//MultipartFile PRODUCT_INFO,
			@RequestParam(value="PRODUCT_INFO",required=false)CommonsMultipartFile PRODUCT_INFO,@RequestParam("PRODUCT_NAME")String PRODUCT_NAME,@RequestParam("PRODUCT_PRICE")String PRODUCT_PRICE,@RequestParam("PRODUCT_DESC")String PRODUCT_DESC,@RequestParam("PRODUCT_STATUS")String PRODUCT_STATUS,@RequestParam("CATEGORY_TYPE")String CATEGORY_TYPE,@RequestParam("REMARK")String REMARK,@RequestParam("CREATE_BY")String CREATE_BY,@RequestParam("CREATE_TIME")String CREATE_TIME,@RequestParam("PRODUCT_ID")String PRODUCT_ID) throws Exception{
		HttpServletRequest request = this.getRequest();
		//封装数据
		PageData pd = new PageData();
		pd.put("PRODUCT_NAME", PRODUCT_NAME);
		pd.put("PRODUCT_PRICE", PRODUCT_PRICE);
		pd.put("PRODUCT_DESC", PRODUCT_DESC);
		pd.put("PRODUCT_STATUS", PRODUCT_STATUS);
		pd.put("CATEGORY_TYPE", CATEGORY_TYPE);
		pd.put("REMARK", REMARK);
		pd.put("CREATE_BY", CREATE_BY);
		pd.put("CREATE_TIME", CREATE_TIME);
		pd.put("PRODUCT_ID", PRODUCT_ID);
		
		
		//如果上传了图片则修改 
		if(PRODUCT_INFO!=null){
			//文件上传
			String path = request.getSession().getServletContext().getRealPath("static\\images");
			String fileName = PRODUCT_INFO.getOriginalFilename();  	
//			try {
//				
//				.out.println(path);
//		        
//		        File dir = new File(path,fileName);        
//		        if(!dir.exists()){
//		            dir.mkdirs();
//		        }
//		        //MultipartFile自带的解析方法
//		        PRODUCT_INFO.transferTo(dir);
//		        
//		        
//		        //封装图片信息
//		        pd.put("PRODUCT_INFO","static\\myImg\\"+fileName);
//		        	
//			} catch (Exception e) {
//				// TODO: handle exception
//				e.printStackTrace();
//			}
			try {
	            //获取输出流                                                                                      图存放的路径
	            OutputStream os=new FileOutputStream(path+"\\"+fileName);
	            //获取输入流 CommonsMultipartFile 中可以直接得到文件的流
	            InputStream is=PRODUCT_INFO.getInputStream();
	            byte[] data = new byte[] {};
	            data = inputStreamToByte(is);//将文件保存到字节数组中 
	            pd.put("PRODUCT_INFO",data);
	        } catch (FileNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }	
		}
		
		logBefore(logger, Jurisdiction.getUsername()+"修改ProductInfo");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		productinfoService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		
		return mv;
	}
	
	
	/**
	 * 
	 * 文件上传辅助
	 */
	private byte [] inputStreamToByte(InputStream is) throws IOException {
         ByteArrayOutputStream bAOutputStream = new ByteArrayOutputStream();
         int ch;
         while((ch = is.read() ) != -1){
            bAOutputStream.write(ch);
         }
         byte data [] =bAOutputStream.toByteArray();
         bAOutputStream.close();
         return data;
     }
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page) throws Exception{
		HttpServletRequest request = this.getRequest();
		
		logBefore(logger, Jurisdiction.getUsername()+"列表ProductInfo");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();//从当前请求中获取数据封装成map 
		
		//条件查询
		String keywords = pd.getString("keywords");//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		
		//类型匹配
		String category = pd.getString("CATEGORY_TYPE");//关键词检索条件
		if(null != category && !"".equals(category)){
			pd.put("category", category.trim());
		}
		
		
		
		page.setPd(pd);
		
		List<PageData>	varList = productinfoService.list(page);	//列出ProductInfo列表
		if(categoryList==null){
			categoryList= categoryService.listAll(null);
		}
		mv.addObject("categoryList", categoryList);
		request.getSession().setAttribute("categoryList", categoryList);
		
		mv.setViewName("productInfo/productinfo/productinfo_list");
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
		mv.setViewName("productInfo/productinfo/productinfo_edit");
		
		
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
		pd = productinfoService.findById(pd);	//根据ID读取
		mv.setViewName("productInfo/productinfo/productinfo_edit");
		mv.addObject("msg", "edit");
		//判断是否是修改
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
		logBefore(logger, Jurisdiction.getUsername()+"批量删除ProductInfo");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			productinfoService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, Jurisdiction.getUsername()+"导出ProductInfo到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("食物编号");	//1
		titles.add("食物名");		//2
		titles.add("食物价格");	//3
		titles.add("食物的描述");	//4
		titles.add("食物是否下架");	//6
		titles.add("食物类型编号");	//7
		titles.add("创建时间");	//8
		titles.add("备注信息");	//9
		titles.add("创建人");		//10
		dataMap.put("titles", titles);
		List<PageData> varOList = productinfoService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("PRODUCT_ID"));	    //1
			vpd.put("var2", varOList.get(i).getString("PRODUCT_NAME"));	    //2
			vpd.put("var3", varOList.get(i).get("PRODUCT_PRICE").toString());	    //3
			vpd.put("var4", varOList.get(i).getString("PRODUCT_DESC"));	    //4	    //5
			vpd.put("var6", varOList.get(i).get("PRODUCT_STATUS").toString());	//6
			vpd.put("var7", varOList.get(i).get("CATEGORY_TYPE").toString());	//7
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
	
	
	
	
	
	@RequestMapping("/getPhoto_Blob")
    public void getPhoto_Blob(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	String id = request.getParameter("id");
    	PageData pd = new PageData();
    	pd.put("id", id);
    	//这个我就不详细写了，就是通过传递的id查询你要显示的图片
    	Map resultMap = productinfoService.getPhoto_Blob(pd);
    	//blob就是你要显示的那张图片
    	byte[] blob = (byte[])resultMap.get("PRODUCT_INFO");
    	//将流输出到页面
    	try {
    		OutputStream out = response.getOutputStream();
        	out.write(blob);
        	out.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
}
