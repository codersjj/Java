package com.flc.service.details.detail.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.flc.dao.DaoSupport;
import com.flc.entity.Page;
import com.flc.util.PageData;
import com.flc.service.details.detail.DetailManager;

/** 
 * 说明： 订单详情
 * 创建人：Shane
 * 修改时间：2019年1月27日
 * @version
 */
@Service("detailService")
public class DetailService implements DetailManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("DetailMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("DetailMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("DetailMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("DetailMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("DetailMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("DetailMapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("DetailMapper.deleteAll", ArrayDATA_IDS);
	}

	/**
	 * 根据id找到对应订单
	 */
	@Override
	public List<PageData> findDetailsByOrerid(PageData orderid) throws Exception {
		// TODO Auto-generated method stub
		return (List<PageData>)dao.findForList("DetailMapper.findDetailsByOrerid", orderid);
	}

	@Override
	public int deleteDetailsByOrderId(String orderId) throws Exception {
		// TODO Auto-generated method stub
		return (int)dao.delete("DetailMapper.deleteDetailsByOrderId", orderId);
	}
	
}

