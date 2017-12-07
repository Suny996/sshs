package com.sshs.system.coder.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sshs.core.base.service.impl.BaseServiceImpl;
import com.sshs.core.page.Page;
import com.sshs.system.coder.dao.CoderDao;
import com.sshs.system.coder.model.Coder;
import com.sshs.system.coder.model.Column;
import com.sshs.system.coder.service.ICoderService;

/**
 * 类名称：CreateCodeService 代码生成器
 * 
 * @author Suny
 * @date 2015年11月24日
 * @version 1.0
 */
@Service("coderService")
public class CoderServiceImpl extends BaseServiceImpl<Coder> implements ICoderService {
	@Resource(name = "coderDao")
	private CoderDao coderDao;

	/**
	 * 新增
	 * 
	 * @param coder
	 * @throws Exception
	 */
	@Override
	public int save(Coder coder) throws Exception {
		// 新增一条数据
		return coderDao.insert(coder);
		// coderDao.save(coder);
	}

	/**
	 * 删除
	 * 
	 * @param pd
	 * @throws Exception
	 */
	@Override
	public int delete(String coderId) throws Exception {
		return coderDao.delete(coderId);
	}

	/**
	 * 通过id获取数据
	 * 
	 * @param pd
	 * @throws Exception
	 */
	@Override
	public Coder findById(String coderId) throws Exception {
		return coderDao.findById(coderId);
	}

	/**
	 * 列表(主表)
	 * 
	 * @param page
	 * @throws Exception
	 */
	@Override
	public Page<Column> findColumnForList(Page<Column> page) throws Exception {
		page.setRows(coderDao.findColumnAll(page.getVariables()));
		return page;
	}

}
