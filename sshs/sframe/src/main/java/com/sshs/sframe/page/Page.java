package com.sshs.sframe.page;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.sshs.sframe.util.ReflectHelper;

/**
 * 分页类
 * 
 * @author Suny 创建时间：2017年9月28日
 */
public class Page<T> {
	private int pageSize; // 每页显示记录数
	private int totalPage; // 总页数
	private int totalCount; // 总记录数
	private int currentPage; // 当前页
	private int currentRow; // 当前记录起始索引
	// bootstrap-table
	private int offset; // 当前记录起始索引
	private int limit; // 每页显示记录数
	private Map<String, Object> userdata = new HashMap<String, Object>();
	private String sort, order;
	private String orderBy;
	List<T> rows;
	Map<String, Object> variables = new HashMap<String, Object>();

	public Page() {
		/*
		 * try { // Integer.parseInt(Tools.readTxtFile(Const.PAGE));
		 * //this.pageSize = 10; //this.limit = 10; } catch (Exception e) {
		 * this.pageSize = 10; }
		 */
	}

	public void getPage(HttpServletRequest request) {
		this.limit = 10;
		Map<String, String[]> properties = request.getParameterMap();
		Iterator<Map.Entry<String, String[]>> entries = properties.entrySet().iterator();
		Map.Entry<String, String[]> entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if (null == valueObj) {
				value = "";
			} else if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;
				for (int i = 0; i < values.length; i++) {
					value = values[i] + ",";
				}
				value = value.substring(0, value.length() - 1);
			} else {
				value = valueObj.toString();
			}
			this.variables.put(name, value);
		}
	}

	public int getTotalPage() {
		if (limit == 0) {
			this.limit = 10;
		}
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getCurrentPage() {
		if (currentPage <= 0)
			currentPage = 1;
		if (currentPage > getTotalPage())
			currentPage = getTotalPage();
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentRow() {
		currentRow = offset;// (getDraw() - 1) * getPageSize();
		if (currentRow < 0)
			currentRow = 0;
		return currentRow;
	}

	public void setCurrentRow(int currentRow) {
		this.currentRow = currentRow;
	}

	public int getOffset() {
		return offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public Map<String, Object> getVariables() {
		return variables;
	}

	public void setVariables(Map<String, Object> variables) {
		this.variables = variables;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		if (StringUtils.isEmpty(order)) {
			order = "asc";
		}
		this.order = order;
	}

	public Map<String, Object> getUserdata() {
		return userdata;
	}

	public void setUserdata(Map<String, Object> userdata) {
		this.userdata = userdata;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		String orderName = "";
		String order = "";
		if (StringUtils.isNotEmpty(orderBy)) {
			if (orderBy.contains(" ")) {
				String[] orders = orderBy.split(" ");
				orderName = orders[0];
				order = orders[1];
				this.orderBy = ReflectHelper.getColumnName(orderName) + " " + order;
			} else {
				this.orderBy = orderBy;
			}
		} else {
			this.orderBy = orderBy;
		}
	}
}
