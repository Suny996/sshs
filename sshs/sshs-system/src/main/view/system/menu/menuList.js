define(function(require) {
	var Model = {};
    Model.init = function() {
		//$("#menuListTable").bootstrapTable({toolbar : '#menutoolbar',showRefresh : true,showToggle:true});
	}
	Model.btnQueryClick = function(event) {
		$("#menuListTable").loadTableData($("#queryMenuForm").getElementsJson());
	};
	// 进入添加页面
	Model.add = function() {
		$.showPage("system/menu/menuEdit.w", {});
	};
	//进入修改页面
	Model.edit = function(row) {
	 	$.showPage("system/menu/menuEdit.w", {
			"menuCode" : row["menuCode"]
		});
	}; 
	return Model;
});