define(function(require) {
	var Model = {};

	Model.btnQueryClick = function(event) {
		$("#${coder.classDeclare}ListTable").loadTableData($("#query${coder.className}Form").getElementsJson());
	};
	//进入修改页面
	Model.showEditPage = function(row) {
	 	$.showPage("${coder.modelName}/${coder.functionName}/${coder.classDeclare}.w", {
			"${coder.idName}" : row["${coder.idName}"]
		});
	}; 
	return Model;
});