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
	Model.runCoder = function(tableName) {
		$("#content").load("system/coder/coder.w",{"tableName":tableName});
	}
	Model.generateCode = function(value, row, index) {
		return [
				"<button class='btn btn-link icon-laptop btnUpdate' onclick='javascript:Model.runCoder(\""
						+ row["tableName"] + "\")'>代码生成</button>",
				"<button class='btn btn-link linear linear-cross btnDel'>代码刪除</button>" ]
				.join('')
	}
	/*
	 * Model.dataTablesListCellRender = function(event) { if (event.colName ==
	 * "handle") {// 显示按钮 event.html = "<button class='btn btn-link icon-laptop
	 * btnUpdate'>代碼生成</button><button class='btn btn-link linear linear-cross
	 * btnDel'>代碼刪除</button>"; } };
	 * 
	 * Model.prototype.dataTablesListRowClick = function(event) { var domButton =
	 * $(event.domEvent.target);// 获取点击的元素 if (domButton.hasClass("btnUpdate"))
	 * {// 判断是否包含某个样式 justep.Util.hint(event.rowID + "代碼生成" +
	 * event.row.val("tableName")); justep.Shell.showPage({ url :
	 * "$UI/pamm/system/coder/coder.w", title : "coder", tableName :
	 * event.row.val("tableName") }); } else if (domButton.hasClass("btnDel")) {
	 * justep.Util.hint("代碼刪除"); } };
	 */

	return Model;
});