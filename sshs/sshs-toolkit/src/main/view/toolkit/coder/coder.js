define(function(require) {
	var Model = {};
	Model.init = function() {
		//alert(JSON.stringify(Model["params"]));
		$("#coderForm").putJsonElements(Model["params"]);
		if(!Model["params"]["tableName"] && !Model["params"]["coderId"]){
			$.alert("coder.quryKeyParamsIsNull");
			return;
		}
		$("#columnListTable").loadTableData({
			"tableName" : Model["params"]["tableName"],
			"coderId" : Model["params"]["coderId"]
		});
	}

	Model.runCoder = function() {
		var data = $("#coderForm").getElementsJson({
			"fields" : $('#columnListTable').getTableData()
		});
		$.sendRequest("toolkit/coder/runCoder.do", data);
	};
	return Model;
});