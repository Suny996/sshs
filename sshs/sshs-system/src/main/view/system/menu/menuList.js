define(function(require) {
	var Model = {};

	Model.btnQueryClick = function(event) {
		$("#menuListTable").loadTableData($("#queryMenuForm").getElementsJson());
	};
	//进入修改页面
	Model.showEditPage = function(row) {
	 	$.showPage("system/menu/menu.w", {
			"menuId" : row["menuId"]
		});
	}; 
	return Model;
});