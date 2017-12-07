package com.sshs.system.dictionary.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.ibatis.type.Alias;

/**
 * 数据字典
 * 
 * @author Suny
 * 
 */
@Alias("Dictionary")
@Table(name = "SYS_DICTIONARY")
public class Dictionary implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "UUID")
	private String dictId;
	private String parentId;
	private String dictType;
	private String dictCode;
	private String dictName;
	private String dictDesc;
	private String status;
	private double sortNo;
	private String module;
	private String legalOrg;
	private String isSystem;
	private Date crtDate;
	@Transient
	private List<Dictionary> children = new ArrayList<Dictionary>();
	@Transient
	private List<DictionaryI18n> i18ns = new ArrayList<DictionaryI18n>();

	public String getDictId() {
		return dictId;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getDictType() {
		return dictType;
	}

	public void setDictType(String dictType) {
		this.dictType = dictType;
	}

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getDictDesc() {
		return dictDesc;
	}

	public void setDictDesc(String dictDesc) {
		this.dictDesc = dictDesc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getSortNo() {
		return sortNo;
	}

	public void setSortNo(double sortNo) {
		this.sortNo = sortNo;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getLegalOrg() {
		return legalOrg;
	}

	public void setLegalOrg(String legalOrg) {
		this.legalOrg = legalOrg;
	}

	public String getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(String isSystem) {
		this.isSystem = isSystem;
	}

	public Date getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}

	public List<Dictionary> getChildren() {
		return children;
	}

	public void addChild(Dictionary dictionary) {
		if (children == null) {
			children = new ArrayList<Dictionary>();
		}
		children.add(dictionary);
	}

	public List<DictionaryI18n> getI18ns() {
		return i18ns;
	}

	public void addI18n(DictionaryI18n i18n) {
		if (i18ns == null) {
			i18ns = new ArrayList<DictionaryI18n>();
		}
		i18ns.add(i18n);
	}
}
