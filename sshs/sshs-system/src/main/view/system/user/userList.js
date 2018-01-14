define(function(require) {
	var Model = {};
    Model.init = function() {
		$("#userListTable").bootstrapTable({toolbar : '#usertoolbar',showRefresh : true,showToggle:true});
	}
	Model.btnQueryClick = function(event) {
		$("#userListTable").loadTableData($("#queryUserForm").getElementsJson());
	};
	// 进入添加页面
	Model.add = function() {
		$.showPage("system/user/user.w", {});
	};
	//进入修改页面
	Model.showEditPage = function(row) {
	 	$.showPage("system/user/user.w", {
			"userId" : row["userId"]
		});
	}; 
	return Model;
});