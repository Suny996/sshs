define(function(require) {
	var Model = {};
	Model.init = function() {
		$("#columnListTable").loadData({
			"tableName" : Model["params"]["tableName"]
		});
	}

	Model.runCoder = function() {
		var data = $("#coderForm").getElementsJson({
			"fields" : $('#columnListTable').getDataEdited(true)
		});
		$.sendRequest("system/coder/runCoder.do", data);
	};
	return Model;
});