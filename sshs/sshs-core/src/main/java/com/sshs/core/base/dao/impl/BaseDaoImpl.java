package com.sshs.core.base.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.sshs.core.base.dao.IBaseDao;
import com.sshs.core.page.Page;

/**
 * @author Suny 修改时间：2017、9、11
 */
@Repository("baseDao")
public abstract class BaseDaoImpl<T> implements IBaseDao<T> {
	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;

	/**
	 * 查找对象
	 * 
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void findForList(String sqlId, Page<T> page) throws Exception {
		page.setRows((List<T>) sqlSessionTemplate.selectList(sqlId, page));
	}

}
