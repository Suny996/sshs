package com.sshs.portal.nav.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Transient;

import org.apache.ibatis.type.Alias;

/**
 * 系统管理->系统管理-菜单表bean Menu类
 * 
 * @author Suny
 * @date 2017/12/24
 */
@Alias("Nav")
public class Nav implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 导航码
	 */
	private String code;
	/**
	 * 标题
	 */
	private String title;

	/**
	 * 图标
	 */
	private String icon;

	/**
	 * 链接
	 */
	private String href;

	/**
	 * 打开标志
	 */
	private boolean spread;

	@Transient
	private List<Nav> children;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public boolean isSpread() {
		return spread;
	}

	public void setSpread(boolean spread) {
		this.spread = spread;
	}

	public List<Nav> getChildren() {
		return children;
	}

	public void setChildren(List<Nav> children) {
		this.children = children;
	}

	public void addChild(Nav nav) {
		if (this.children == null) {
			this.children = new ArrayList<Nav>();
		}
		this.children.add(nav);
	}
}