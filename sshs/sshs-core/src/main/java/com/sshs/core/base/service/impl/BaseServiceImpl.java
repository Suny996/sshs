package com.sshs.core.base.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.sshs.constant.Global;
import com.sshs.core.base.service.IBaseService;
import com.sshs.core.page.Page;

import tk.mybatis.mapper.common.Mapper;

/**
 * 基础服务类
 * 
 * @author Suny
 * @date 2017-10-20
 * 
 */
public abstract class BaseServiceImpl<T> implements IBaseService<T> {
	@Autowired
	private Mapper<T> dao;
	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;

	/**
	 * 
	 * @return
	 */
	public Mapper<T> getDao() {
		return this.dao;
	}

	/**
	 * 新增
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@Override
	public int save(T model) throws Exception {
		setCrtProperties(model);
		return dao.insert(model);
	}

	/**
	 * 修改
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@Override
	public int update(T model) throws Exception {
		setUpdProperties(model);
		return getDao().updateByPrimaryKey(model);
	}

	/**
	 * 删除
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@Override
	public int delete(T model) throws Exception {
		return dao.delete(model);
	}

	/**
	 * 根据主键删除
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public int delete(String id) throws Exception {
		return dao.deleteByPrimaryKey(id);
	}

	/**
	 * 根据主键批量删除
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@Override
	public int delete(List<String> ids) throws Exception {
		int i = 0;
		for (String id : ids) {
			i += dao.deleteByPrimaryKey(id);
		}
		return i;
	}

	/**
	 * 根据主键查询单笔记录
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public T getById(String id) throws Exception {
		return dao.selectByPrimaryKey(id);
	}

	/**
	 * 公共分页查询方法
	 * 
	 * @param sqlId
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Page<T> findForPageList(String sqlId, Page<T> page) throws Exception {
		page.setRows((List<T>) sqlSessionTemplate.selectList(sqlId, page));
		return page;
	}

	/**
	 * 
	 * @param object
	 */
	private void setCrtProperties(T object) {
		try {
			Class<?> clazz = object.getClass();
			Method setCrtUserCode = clazz.getDeclaredMethod("setCrtUserCode", String.class);
			if (setCrtUserCode != null) {
				setCrtUserCode.invoke(object, "admin");
			}
			Method setCrtOrgCode = clazz.getDeclaredMethod("setCrtOrgCode", String.class);
			if (setCrtOrgCode != null) {
				setCrtOrgCode.invoke(object, "admin");
			}
			Method setCrtDate = clazz.getDeclaredMethod("setCrtDate", java.util.Date.class);
			if (setCrtDate != null) {
				setCrtDate.invoke(object, new Date());
			}
			// 处理法人行问题，全省权限时不设置法人行号
			String authType = "01";
			if (authType != null && !authType.contains(Global.AUTH_LEVEL_TOP)) {
				Method getLegalOrg = clazz.getDeclaredMethod("getLegalOrg");
				if (getLegalOrg != null && getLegalOrg.invoke(object) == null) {
					Method setLegalOrg = clazz.getDeclaredMethod("setLegalOrg", String.class);
					if (setLegalOrg != null) {
						setLegalOrg.invoke(object, "admin");
					}
				}
			}

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param object
	 */
	private void setUpdProperties(T object) {
		try {

			Class<?> clazz = object.getClass();

			Method setUpdUserCode = clazz.getDeclaredMethod("setUpdUserCode", String.class);
			if (setUpdUserCode != null) {
				setUpdUserCode.invoke(object, "admin");
			}
			Method setUpdOrgCode = clazz.getDeclaredMethod("setUpdOrgCode", String.class);
			if (setUpdOrgCode != null) {
				setUpdOrgCode.invoke(object, "admin");
			}
			Method setUpdDate = clazz.getDeclaredMethod("setUpdDate", java.util.Date.class);
			if (setUpdDate != null) {
				setUpdDate.invoke(object, new Date());
			}
			Method setModUserCode = clazz.getDeclaredMethod("setModUserCode", String.class);
			if (setModUserCode != null) {
				setModUserCode.invoke(object, "admin");
			}
			Method setModOrgCode = clazz.getDeclaredMethod("setModOrgCode", String.class);
			if (setModOrgCode != null) {
				setModOrgCode.invoke(object, "admin");
			}
			Method setModDate = clazz.getDeclaredMethod("setModDate", java.util.Date.class);
			if (setModDate != null) {
				setModDate.invoke(object, new Date());
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
