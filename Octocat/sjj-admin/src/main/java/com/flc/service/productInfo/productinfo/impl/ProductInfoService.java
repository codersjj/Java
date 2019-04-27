package com.flc.service.productInfo.productinfo.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.flc.dao.DaoSupport;
import com.flc.entity.Page;
import com.flc.util.PageData;
import com.flc.service.productInfo.productinfo.ProductInfoManager;

/** 
 * 说明： 水饺详情模块
 * 创建人：Shane
 * 创建时间：2019-1-27
 * @version
 */
@Service("productinfoService")
public class ProductInfoService implements ProductInfoManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("ProductInfoMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("ProductInfoMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("ProductInfoMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	//忽略警告
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page pd)throws Exception{
		return (List<PageData>)dao.findForList("ProductInfoMapper.datalistPage", pd);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("ProductInfoMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("ProductInfoMapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("ProductInfoMapper.deleteAll", ArrayDATA_IDS);
	}

	/**
	 * 根据id获取图片信息
	 * @throws Exception 
	 */
	@Override
	public Map getPhoto_Blob(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		
		return (Map) dao.findForObject("ProductInfoMapper.getPhoto_Blob", pd);
	}
	
}

