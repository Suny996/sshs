package com.sshs.core.plugin.dialect;

/**
 * An SQL dialect for DB2.
 * 
 * @author Suny
 */
public class DB2Dialect extends Dialect {

	@Override
	public String getLowercaseFunction() {
		return "lcase";
	}

	@Override
	public boolean supportsSequences() {
		return true;
	}

	@Override
	public boolean supportsPooledSequences() {
		return true;
	}

	@Override
	public boolean supportsLimit() {
		return true;
	}

	@Override
	public boolean supportsVariableLimit() {
		return false;
	}

	public String getLimitString(String sql, int offset, int limit) {
		int startOfSelect = sql.toLowerCase().indexOf("select");

		StringBuffer pagingSelect = new StringBuffer(sql.length() + 100).append(sql.substring(0, startOfSelect)).append("select * from ( select ").append(getRowNumber(sql));
		if (super.hasDistinct(sql)) {
			pagingSelect.append(" row_.* from ( ").append(sql.substring(startOfSelect, getOrderByIdex(sql))).append(" ) as row_");
		} else {
			pagingSelect.append(sql.substring(startOfSelect + 6, getOrderByIdex(sql)));
		}
		pagingSelect.append(" ) as temp_ where rownumber_ ");

		pagingSelect.append(" >= " + (offset + 1) + "  fetch first " + (limit - offset) + " rows only");

		return pagingSelect.toString();
	}

	@Override
	public boolean useMaxForLimit() {
		return true;
	}

	@Override
	public boolean supportsUnionAll() {
		return true;
	}

	@Override
	public boolean supportsCurrentTimestampSelection() {
		return true;
	}
}
