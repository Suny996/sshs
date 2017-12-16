define(function(require) {
	var Model = {};
	Model.init = function() {
		$("#columnListTable").loadTableData({
			"tableName" : Model["params"]["tableName"]
		});
	}

	Model.runCoder = function() {
		var data = $("#coderForm").getElementsJson({
			"fields" : $('#columnListTable').getTableData()
		});
		$.sendRequest("system/coder/runCoder.do", data);
	};
	return Model;
});