define(function(require) {
	// var $ = require("jquery");
	// require("$UI/aframe/aframe");// add by Suny 2017-10-25
	// var justep = require("$UI/system/lib/justep");

	var Model = function() {
		this.callParent();
	};

	Model.btnQueryClick = function(event) {
		$("#mainListTable").loadData($("#queryForm").getElementsJson());
		// this.comp('tableListData').refreshData();
	};
	Model.showRunPage = function(tableName) {
		$.showPage("system/coder/coder.w",{"tableName":tableName});
	}
	Model.handleRender = function(value, row, index) {
		return [
				"<button class='btn btn-link icon-laptop btnUpdate' onclick='javascript:Model.showRunPage(\""
						+ row["tableName"] + "\")'>代码生成</button>",
				"<button class='btn btn-link linear linear-cross btnDel'>代码刪除</button>" ]
				.join('')
	}

	return Model;
});