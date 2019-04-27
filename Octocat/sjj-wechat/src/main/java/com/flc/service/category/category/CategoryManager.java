package com.flc.service.category.category;

import java.util.List;
import com.flc.entity.Page;
import com.flc.util.PageData;

/** 
 * 说明： 类目模块接口
 * 创建人：Shane
 * 修改时间：2019年1月27日
 * @version
 */
public interface CategoryManager{

	
	

	
	
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
	
	/**根据夫类型查出旗下子类型
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public List<PageData> findListByFTypeId(PageData pd) throws Exception;
	
}

