package com.flc.service.productInfo.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flc.dao.DaoSupport;

import com.flc.service.productInfo.ProductInfoService;
import com.flc.util.PageData;

@Service("productinfoService")
public class ProductInfoServiceImpl implements ProductInfoService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	/**
	 * 查询所有
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		 return (List<PageData>) dao.findForList("ProductInfoMapper.listAll", pd);
	}

	@Override
	@SuppressWarnings("unchecked")
	public PageData findById(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (PageData)dao.findForObject("ProductInfoMapper.findById", pd);
	}

	/**
	 * 模糊查询
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> searchByName(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("service:pd值为；"+pd);
		return (List<PageData>) dao.findForList("ProductInfoMapper.searchByName", pd);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> ListByOneTypeName(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		
		return (List<PageData>) dao.findForList("ProductInfoMapper.ListByOneTypeName", pd);
	}

	@Override
	public Map getPhoto_Blob(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (Map) dao.findForObject("ProductInfoMapper.getPhoto_Blob", pd);
	}

}
