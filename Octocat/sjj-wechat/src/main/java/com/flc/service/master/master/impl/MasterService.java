package com.flc.service.master.master.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.flc.dao.DaoSupport;
import com.flc.entity.Page;
import com.flc.util.PageData;
import com.flc.service.master.master.MasterManager;

/** 
 * 说明： master
 * 创建人：Shane
 * 修改时间：2019年1月27日
 * @version
 */
@Service("masterService")
public class MasterService implements MasterManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("MasterMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("MasterMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("MasterMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("MasterMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("MasterMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("MasterMapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("MasterMapper.deleteAll", ArrayDATA_IDS);
	}

	@Override
	public List<PageData> findOrderIdsByOpenId(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (List<PageData>)dao.findForList("MasterMapper.findOrderIdsByOpenId", pd);
	}

	@Override
	public int updateBuyerIsDelete(String orderid) throws Exception {
		// TODO Auto-generated method stub
		return (int) dao.update("MasterMapper.updateBuyerIsDelete", orderid);
	}

	@Override
	public int deleteAllState(String openId) throws Exception {
		// TODO Auto-generated method stub
		return (int) dao.update("MasterMapper.deleteAllState",openId);
	}

	
	@Override
	public String findGoTimeByOrderId(String orderId) throws Exception {
		// TODO Auto-generated method stub
		return (String) dao.findForObject("MasterMapper.findGoTimeByOrderId",orderId);
	}

	@Override
	public int cancelMaster(String orderId) throws Exception {
		// TODO Auto-generated method stub
		return (int) dao.delete("MasterMapper.cancelMaster",orderId);
	}
	
}

