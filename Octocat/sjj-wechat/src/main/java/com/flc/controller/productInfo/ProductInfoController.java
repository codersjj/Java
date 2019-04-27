package com.flc.controller.productInfo;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.flc.controller.base.BaseController;
import com.flc.controller.weixinuserinfo.GetOpenIDUtil;
import com.flc.entity.Page;
import com.flc.service.category.category.CategoryManager;
import com.flc.service.details.detail.DetailManager;
import com.flc.service.master.master.MasterManager;
import com.flc.service.productInfo.ProductInfoService;
import com.flc.service.seller.seller.SellerManager;
import com.flc.util.PageData;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/productInfo")
public class ProductInfoController extends BaseController {

	// 商品操作对象
	@Autowired
	ProductInfoService service;
	// 类目操作对象
	@Resource(name = "categoryService")
	private CategoryManager categoryService;
	
	//订单操作
	@Resource(name = "masterService")
	private MasterManager masterService;
	//商家信息
	@Resource
	private SellerManager sellerManager;
	
	//订单操作者
	@Autowired
	private DetailManager detailService;
	
	
	@Value("${wxappid}")
	private String appid;
	
	@Value("${wxsecret}")
	private String secret;

	// 初始化homePage页面
	@RequestMapping("/listAll")
	public ModelAndView listAll(@RequestParam(required = false) Page page,Map<String, Object> map,HttpSession session,HttpServletRequest request)
			throws Exception {

		ModelAndView mv = this.getModelAndView();
		
		//保存商家的一些信息
		List<PageData> sellers = sellerManager.listAll(null);
		PageData seller = sellers.get(0);
		mv.addObject("seller", seller);
		//将商家信息存入到session中
		request.getSession().setAttribute("seller", seller);
		
		//微信获取openId
		String code = request.getParameter("code");
        String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appid+"&secret="+secret+"&code="+code+"&grant_type=authorization_code";
		
        //第一次请求 获取access_token 和 openid
        String  oppid = new GetOpenIDUtil().doGet(requestUrl);
        JSONObject oppidObj =JSONObject.fromObject(oppid);
        String access_token = (String) oppidObj.get("access_token");
        String openid = (String) oppidObj.get("openid");
        String requestUrl2 = "https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+openid+"&lang=zh_CN";
        String userInfoStr = new GetOpenIDUtil().doGet(requestUrl2);
        JSONObject wxUserInfo =JSONObject.fromObject(userInfoStr); 
        //判断是否是从微信转进来 如果是则获取 如果不是 则不获取
        String openId;
        if(wxUserInfo.get("openid")!=null){
        	openId=wxUserInfo.get("openid").toString();
        	request.getSession().setAttribute("openId", openId);
        }
		// 页面数据类 一个map
		PageData pd = new PageData();
		pd = this.getPageData();// 从当前请求中获取页面信息封装成map

		String keywords = pd.getString("keywords");// 关键词检索条件
		if (null != keywords && !"".equals(keywords)) {
			pd.put("keywords", keywords.trim());
		}
		if (page != null) {
			page.setPd(pd);// 进行分页数据
		}
		
		// 所有数量 非模糊查询的数字
		String[] num = request.getParameterValues("nums");
		List<InfoUtil> infoUtil;// 用于存放有数量的商品信息
		List<PageData> productInfoList;// 存放查出的列表

		PageData pdCategory = new PageData();
		// 顶级分类初步显示到页面
		pdCategory.put("CATEGORY_FTYPE", 0);
		// 将所有类目显示出来
		List<PageData> categoryList = categoryService.findListByFTypeId(pdCategory);
		mv.addObject("categoryList", categoryList);

		// 用于存储不同类型下的所有商品
		List<List<PageData>> productLists = new ArrayList<List<PageData>>();
		List<PageData> pdTypes = new ArrayList<>();
		// 拿到所有类型名称集合
		for (PageData pageData : categoryList) {
			PageData pdCategoryName = new PageData();
			pdCategoryName.put("CATEGORY_NAME", pageData.get("CATEGORY_NAME"));
			pdTypes.add(pdCategoryName);
		}

		// 一个类型对应的商品集合
		// 存放所有类型对应的商品集合
		// 根据每个条件 去查
		for (int i = 0; i < pdTypes.size(); i++) {
			List<PageData> oneProductList = service.ListByOneTypeName(pdTypes.get(i));
			productLists.add(oneProductList);
		}

		
		// 商品名称模糊查询
		String searchFoodName = pd.getString("searchFoodName");
		// 模糊查询
		if (searchFoodName != null) {
			PageData pd1 = new PageData();
			pd1.put("searchFoodName", searchFoodName);
			productInfoList = service.searchByName(pd1);// 将页面数据进行传入进入数据提取
			//模糊查询的数据
			if(!searchFoodName.equals("")){
				mv.addObject("productInfoList", productInfoList);
			}
			//request.getSession().setAttribute("productInfoList", productInfoList);			
			
			String[] PRODUCT_ID = request.getParameterValues("PRODUCT_ID");
			//模糊查询对应的商品数量
			String[] infoIds = request.getParameterValues("nums");
			// 购物车信息存入
			infoUtil = new ArrayList<>();
			// 购物车信息存入
			if (num != null && num.length != 0) {
				for (int i = 0; i < infoIds.length; i++) {
					// 添加
					InfoUtil info = new InfoUtil();
					info.setNum(infoIds[i]);
					info.setInfoId(PRODUCT_ID[i]);
					infoUtil.add(info);
				}
			}

		} else {
			// 购物车返回数据
			String[] infoIds = request.getParameterValues("infoId");

			infoUtil = new ArrayList<>();
			if (num != null && num.length != 0) {
				for (int i = 0; i < num.length; i++) {
					// 添加
					InfoUtil info = new InfoUtil();
					info.setNum(num[i]);
					info.setInfoId(infoIds[i]);
					infoUtil.add(info);
				}

			}
			request.getSession().setAttribute("info", infoUtil);

		}


		// 用于在首页存放数量 按照分类放每个商品的数量 二维数组
		List<List<PageData>> pdnums = new ArrayList<List<PageData>>();
		for (int a = 0; a < productLists.size(); a++) {
			List<PageData> onePd = new ArrayList<>();
			// 测试
			for (int i = 0; i < productLists.get(a).size(); i++) {
				PageData pdnum = new PageData();
				pdnum.put("pdnum", 0);
				onePd.add(pdnum);
				for (int j = 0; j < infoUtil.size(); j++) {
					if (productLists.get(a).get(i).get("product_id").equals(infoUtil.get(j).getInfoId())) {
						pdnum.put("pdnum", infoUtil.get(j).getNum());
					}
				}
			}
			pdnums.add(onePd);
		}

		
		
		
		mv.addObject("pdTypes", pdTypes);
		request.getSession().setAttribute("pdTypes", pdTypes);
		mv.addObject("info", infoUtil);
		mv.addObject("pdnums", pdnums);
		request.getSession().setAttribute("pdnums", pdnums);
		mv.addObject("productInfoLists", productLists);
		request.getSession().setAttribute("productLists", productLists);
		mv.setViewName("homePage");
		return mv;
	}

	@RequestMapping("/goShopCart")
	public ModelAndView goShopCart(HttpServletRequest request) {
		
		
		String[] infoIds = request.getParameterValues("PRODUCT_ID");

		// 购物车商品名
		String[] names = request.getParameterValues("PRODUCT_NAME");
		// 购物车价格
		String[] price = request.getParameterValues("PRODUCT_PRICE");
		// 购物车对应的商品数量
		String[] num = request.getParameterValues("nums");

		// 存入信息
		List<InfoUtil> infoUtil = new ArrayList<>();
		for (int i = 0; i < num.length; i++) {
			if (num[i].equals("0") || num[i].equals("")) {
				//为空
			} else {
				// 添加
				InfoUtil info = new InfoUtil();
				info.setName(names[i]);
				info.setPrice(price[i]);
				info.setNum(num[i]);
				info.setInfoId(infoIds[i]);
				infoUtil.add(info);
			}
		}

		ModelAndView mv = new ModelAndView();
		mv.addObject("info", infoUtil);
		mv.setViewName("shoppingCart");
		request.getSession().setAttribute("info", infoUtil);
		return mv;
	}

	// 提交订单
	@RequestMapping("/yuyue")
	public ModelAndView submit() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("submit");
		return mv;
	}
	
	/**
	 * 获取图片流信息
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/getPhoto_Blob")
    public void getPhoto_Blob(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	String id = request.getParameter("id");
    	PageData pd = new PageData();
    	pd.put("id", id);
    	//这个我就不详细写了，就是通过传递的id查询你要显示的图片
    	Map resultMap = service.getPhoto_Blob(pd);
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
	
	
	
	
	/**
	 * 用户查看订单
	 * @throws Exception 
	 */
	@RequestMapping("/goMymaster")
	public String goMymaster(HttpServletRequest request,Model model,@RequestParam(value="timeName",required=false)String timeName) throws Exception{
		
		// 购物车对应的商品数量
		String[] num = request.getParameterValues("nums");
		//将我添加的食物id和食物数量保存到session中
		String[] infoIds = request.getParameterValues("PRODUCT_ID");
		
		if(num!=null&&infoIds!=null){
			// 存入信息
			List<InfoUtil> infoUtil = new ArrayList<>();
			for (int i = 0; i < num.length; i++) {
				if (num[i].equals("0") || num[i].equals("")) {
					//为空
				} else {
					// 添加
					InfoUtil info = new InfoUtil();
					info.setNum(num[i]);
					info.setInfoId(infoIds[i]);
					infoUtil.add(info);
				}
			}
			
			model.addAttribute("info", infoUtil);
			request.getSession().setAttribute("info", infoUtil);
		}
		
		
		String openId= (String) request.getSession().getAttribute("openId");
		//保存全部订单信息
		List<List<PageData>> allDetails = new ArrayList<List<PageData>>();
		//保存所有订单的时间集合
		List<String> times = new ArrayList<String>();
		//保存所有订单预计到达时间的集合
		List<String> gotimes = new ArrayList<String>();
		//保存所有订单号
		List<String> ods = new ArrayList<String>();
		//保存所有订单主键
		List<String> oderIds = new ArrayList<String>();
		//保存到达时间 小时：分钟
		List<String> goHourMins = new ArrayList<String>();
		//保存所有订单号
		List<String> sumPrices = new ArrayList<String>();
		//将我添加的食物id和食物数量保存到session中 OVER
		

		//要删除的开始
		/*PageData tiaojian = new PageData();
		tiaojian.put("openId", null);
		tiaojian.put("timeName",timeName);
		List<PageData> orderIds = masterService.findOrderIdsByOpenId(tiaojian);
		//根据订单号查询对应的
		for (PageData orderid : orderIds) {
			PageData pdOrders = new PageData();
			pdOrders.put("order_id", orderid.getString("order_id"));
			List<PageData> details = detailService.findDetailsByOrerid(pdOrders);
			allDetails.add(details);
			times.add(orderid.get("create_time").toString());
			ods.add(orderid.get("order_id").toString());
			sumPrices.add(orderid.get("order_amount").toString());
		}*/
		//要删除的结束
		
		
		
		if(openId!=null){
			PageData tiaojian = new PageData();
			tiaojian.put("openId", openId);
			tiaojian.put("timeName",timeName);
			List<PageData> orderIds = masterService.findOrderIdsByOpenId(tiaojian);
			//根据订单号查询对应的
			for (PageData orderid : orderIds) {
				PageData pdOrders = new PageData();
				pdOrders.put("order_id", orderid.getString("order_id"));
				List<PageData> details = detailService.findDetailsByOrerid(pdOrders);
				allDetails.add(details);
				String time = orderid.get("create_time").toString();;
				time = time.substring(0,time.lastIndexOf("."));
				times.add(time);
				gotimes.add(orderid.get("buyer_goTime").toString());
				String date = orderid.get("buyer_goTime").toString();
				date=date.substring(11,16);
				goHourMins.add(date);
				ods.add(orderid.get("order_num").toString());
				oderIds.add(orderid.get("order_id").toString());
				sumPrices.add(orderid.get("order_amount").toString());
			}
		}
		//将改订单的食物信息存入
		model.addAttribute("allDetails", allDetails);
		//订单时间
		model.addAttribute("times", times);
		//订单到达时间
		model.addAttribute("gotimes", gotimes);
		//订单编号
		model.addAttribute("ods", ods);
		//订单唯一主键
		model.addAttribute("oderIds", oderIds);
		//订单总金额
		model.addAttribute("sumPrices", sumPrices);
		//到达时间 时：分
		model.addAttribute("goHourMins", goHourMins);
		return "myMasters";
	}
	
	
	/**	
	 * 用户更改订单的删除状态 只是改变状态 订单本身不删除
	 * @throws Exception 
	 */
	@RequestMapping("goUpdateState")
	@ResponseBody
	public String goUpdateState(@RequestParam String orderid) throws Exception{
		int state = masterService.updateBuyerIsDelete(orderid);
		
		return state+"";
	}
	
	
	
	/**
	 * deleteAllState
	 * 用户更改订单的删除状态 只是改变状态 订单本身不删除
	 */
	@RequestMapping("deleteAllState")
	@ResponseBody
	public String deleteAllState(@RequestParam String openId) throws Exception{
		int state = masterService.deleteAllState(openId);
		System.out.println(state);
		return state+"";
	}
	
	/**
	 * 用户取消订单 条件 时间小于到店时间20分钟 则不可取消
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("cancelMaster")
	@ResponseBody
	public String cancelMaster(@RequestParam String orderId) throws Exception{
		//获取改订单预计到达时间
		String orderGoTime = masterService.findGoTimeByOrderId(orderId);
		//订单用户到达时间
		Date OrderBuyerGoTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(orderGoTime);
		//比较时间
		long[] timeResults = getDatePoor(OrderBuyerGoTime,new Date());
		//时间差
		long dayCha  = timeResults[0];
		long hourCha = timeResults[1];
		long minCha  = timeResults[2];
		
		try {
			//不可取消
			if(dayCha !=0 || hourCha<=0 || (minCha<20&&minCha>0)){
				return "-2";
			}
			//执行取消订单操作
			//1.删除详情
			int result = detailService.deleteDetailsByOrderId(orderId);
			//2.删除订单
			int cancelMasterResult = masterService.cancelMaster(orderId);
			return cancelMasterResult+"";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "-3";
		}
	}
	
	
	
	//日期比较
	public static long[] getDatePoor(Date endDate, Date nowDate) {

		long nd = 1000 * 24 * 60 * 60;//每天毫秒数

		long nh = 1000 * 60 * 60;//每小时毫秒数

		long nm = 1000 * 60;//每分钟毫秒数

		long diff = endDate.getTime() - nowDate.getTime(); // 获得两个时间的毫秒时间差异

		long day = diff / nd;   // 计算差多少天

		long hour = diff % nd / nh; // 计算差多少小时

		long min = diff % nd % nh / nm;  // 计算差多少分钟

		long[] timeInfos = new long[3];
		timeInfos[0] = day;
		timeInfos[1] = hour;
		timeInfos[2] = min;
		return timeInfos;

	}
}
