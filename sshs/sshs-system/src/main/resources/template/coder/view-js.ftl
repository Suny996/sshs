define(function(require) {
	var Model = {};
	Model.init = function() {
		$("#${coder.className}ListTable").loadData({			
		});
	}

	Model.save = function() {
		var data = $("${coder.className}Form").getElementsJson();
		$.sendRequest("system/coder/edit.do", data);
	};
	return Model;
});