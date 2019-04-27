package com.flc.service.master.master;

import java.util.List;
import com.flc.entity.Page;
import com.flc.util.PageData;

/** 
 * 说明： master接口
 * 创建人：Shane
 * 修改时间：2019年1月27日
 * @version
 */
public interface MasterManager{

	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception;
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception;
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception;
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> list(Page page)throws Exception;
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	public List<PageData> listAll(PageData pd)throws Exception;
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception;
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception;

	public List<PageData> findOrderIdsByOpenId(PageData pd)throws Exception;

	/**
	 * 修改订单状态
	 * @param orderid
	 * @return
	 * @throws Exception
	 */
	public int updateBuyerIsDelete(String orderid) throws Exception;

	/**
	 * 修改改用户的所有订单状态
	 * @param orderid
	 * @return
	 * @throws Exception
	 */
	public int deleteAllState(String openId) throws Exception;

	
	/**
	 * 根据订单号 查询订单信息
	 * @param orderId
	 * @return
	 */
	public String findGoTimeByOrderId(String orderId) throws Exception;

	/**
	 * 取消订单（用户取消即删除订单）
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	public int cancelMaster(String orderId) throws Exception;
	
}

