define(function(require) {
	var Model = {};
    Model.init = function() {
		$("#orgListTable").bootstrapTable({toolbar : '#orgtoolbar',showRefresh : true,showToggle:true});
	}
	Model.btnQueryClick = function(event) {
		$("#orgListTable").loadTableData($("#queryOrgForm").getElementsJson());
	};
	// 进入添加页面
	Model.add = function() {
		$.showPage("system/org/orgEdit.w", {});
	};
	//进入修改页面
	Model.edit = function(row) {
	 	$.showPage("system/org/orgEdit.w", {
			"orgId" : row["orgId"]
		});
	}; 
	return Model;
});