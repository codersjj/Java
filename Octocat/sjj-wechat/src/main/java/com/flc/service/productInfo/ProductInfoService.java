package com.flc.service.productInfo;

import java.util.List;
import java.util.Map;

import com.flc.util.PageData;

public interface ProductInfoService {
	public List<PageData> listAll(PageData pd)throws Exception;
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception;
	
	
	/**"
	 * 根据姓名查询
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> searchByName(PageData pd)throws Exception;


	/**
	 * 根据类型名称查出对应的集合
	 * @param pageData
	 * @return
	 * @throws Exception 
	 */
	public List<PageData> ListByOneTypeName(PageData pageData) throws Exception;

	public Map getPhoto_Blob(PageData pd) throws Exception;

	
}
