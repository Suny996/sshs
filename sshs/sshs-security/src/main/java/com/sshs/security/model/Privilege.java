package com.sshs.security.model;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

/**
 * 系统管理->系统管理-权限表bean Privilege类
 * 
 * @author Suny
 * @date 2018/01/09
 */
@Alias("Privilege")
// @Table(name = "SEC_PRIVILEGE")
public class Privilege implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	// private String url;

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	/*public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}*/
}
