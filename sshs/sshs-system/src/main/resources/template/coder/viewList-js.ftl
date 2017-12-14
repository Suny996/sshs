define(function(require) {
	var Model = {};

	Model.btnQueryClick = function(event) {
		$("#${coder.className}ListTable").loadData($("#queryForm").getElementsJson());
	};
	//进入修改页面
	Model.showEditPage = function(row) {
	 	$.showPage("system/coder/coder.w", {
			"${coder.idName}" : row["${coder.idName}"]
		});
	}; 
	return Model;
});