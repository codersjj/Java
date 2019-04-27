package com.flc.service.category.category.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.flc.dao.DaoSupport;
import com.flc.entity.Page;
import com.flc.util.PageData;
import com.flc.service.category.category.CategoryManager;

/** 
 * 说明： 类目模块
 * 创建人：Shane
 * 修改时间：2019年1月27日
 * @version
 */
@Service("categoryService")
public class CategoryService implements CategoryManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	

	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("CategoryMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("CategoryMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("CategoryMapper.findById", pd);
	}
	

	/**通过父类型获取数据
	 * @param pd
	 * @throws Exception 
	 */
	@Override
	public List<PageData> findListByFTypeId(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (List<PageData>) dao.findForList("CategoryMapper.findListByFTypeId",pd);
	}

	@Override
	public void deleteAll(String[] ArrayDATA_IDS) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}

