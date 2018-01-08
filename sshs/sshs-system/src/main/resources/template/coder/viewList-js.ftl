define(function(require) {
	var Model = {};
    Model.init = function() {
		$("#${coder.classDeclare}ListTable").bootstrapTable({toolbar : '#${coder.classDeclare}toolbar',showRefresh : true,showToggle:true});
	}
	Model.btnQueryClick = function(event) {
		$("#${coder.classDeclare}ListTable").loadTableData($("#query${coder.className}Form").getElementsJson());
	};
	// 进入添加页面
	Model.add = function() {
		$.showPage("${coder.modelName}/${coder.functionName}/${coder.classDeclare}.w", {});
	};
	//进入修改页面
	Model.showEditPage = function(row) {
	 	$.showPage("${coder.modelName}/${coder.functionName}/${coder.classDeclare}.w", {
			"${coder.idName}" : row["${coder.idName}"]
		});
	}; 
	return Model;
});