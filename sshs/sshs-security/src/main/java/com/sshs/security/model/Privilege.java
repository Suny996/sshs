package com.sshs.security.model;

import java.io.Serializable;

import javax.persistence.Table;

import org.apache.ibatis.type.Alias;

/**
 * 系统管理->系统管理-用户表bean User类
 * 
 * @author Suny
 * @date 2018/01/09
 */
@Alias("Privilege")
@Table(name = "SYS_PRIVILEGE")
public class Privilege implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String url;

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}
}
