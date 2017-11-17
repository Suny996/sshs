package com.sshs.sframe.base.service;

import com.sshs.sframe.page.Page;

public interface IBaseService<T> {
	public int save(T object) throws Exception;

	public int update(T object) throws Exception;

	public int delete(T object) throws Exception;

	Page<T> findForPageList(String sqlId, Page<T> page) throws Exception;
}
