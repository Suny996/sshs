package com.sshs.core.base.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import tk.mybatis.mapper.common.Mapper;

import com.sshs.core.base.service.IBaseService;
import com.sshs.core.page.Page;

public abstract class BaseServiceImpl<T> implements IBaseService<T> {
	@Autowired
	private Mapper<T> dao;
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
	public Page<T> findForPageList(String sqlId, Page<T> page) throws Exception {
		page.setRows((List<T>) sqlSessionTemplate.selectList(sqlId, page));
		return page;
	}

	protected Mapper<T> getDao() {
		return this.dao;
	}

	public int save(T object) throws Exception {
		setCrtProperties(object);
		return dao.insert(object);
	}

	public int update(T object) throws Exception {
		setUpdProperties(object);
		return getDao().updateByPrimaryKey(object);
	}

	public int delete(T object) throws Exception {
		return getDao().delete(object);
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
				setCrtUserCode.invoke(object, "admin");// SystemUtil.getCurrentUserInfo().getEmpNo());
			}
			Method setCrtOrgCode = clazz.getDeclaredMethod("setCrtOrgCode", String.class);
			if (setCrtOrgCode != null) {
				setCrtOrgCode.invoke(object, "admin");// SystemUtil.getCurrentUserInfo().getOrgCode());
			}
			Method setCrtDate = clazz.getDeclaredMethod("setCrtDate", java.util.Date.class);
			if (setCrtDate != null) {
				setCrtDate.invoke(object, new Date());
			}
			// 处理法人行问题，全省权限时不设置法人行号
			String authType = "01";// SystemUtil.getCurrentUserInfo().getAuthDataType();
			if (authType != null && !authType.contains(",A,")) {
				Method getLegalOrg = clazz.getDeclaredMethod("getLegalOrg");
				if (getLegalOrg != null && getLegalOrg.invoke(object) == null) {
					Method setLegalOrg = clazz.getDeclaredMethod("setLegalOrg", String.class);
					if (setLegalOrg != null) {
						setLegalOrg.invoke(object, "admin");// SystemUtil.getCurrentUserInfo().getLegalOrgCode());
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
				setUpdUserCode.invoke(object, "admin");// SystemUtil.getCurrentUserInfo().getEmpNo());
			}
			Method setUpdOrgCode = clazz.getDeclaredMethod("setUpdOrgCode", String.class);
			if (setUpdOrgCode != null) {
				setUpdOrgCode.invoke(object, "admin");// SystemUtil.getCurrentUserInfo().getOrgCode());
			}
			Method setUpdDate = clazz.getDeclaredMethod("setUpdDate", java.util.Date.class);
			if (setUpdDate != null) {
				setUpdDate.invoke(object, new Date());
			}
			Method setModUserCode = clazz.getDeclaredMethod("setModUserCode", String.class);
			if (setModUserCode != null) {
				setModUserCode.invoke(object, "admin");// SystemUtil.getCurrentUserInfo().getEmpNo());
			}
			Method setModOrgCode = clazz.getDeclaredMethod("setModOrgCode", String.class);
			if (setModOrgCode != null) {
				setModOrgCode.invoke(object, "admin");// SystemUtil.getCurrentUserInfo().getOrgCode());
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
