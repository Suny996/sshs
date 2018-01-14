define(function(require) {
	var Model = {};
	Model.init = function() {
	    if(Model["params"]["orgId"]){
			$.loadData("system/org/org.do", {
					"orgId" : Model["params"]["orgId"]
				});
		}
	}

	Model.save = function() {
		$.save("system/org/org.do", $("#orgForm").getElementsJson());
	};
	return Model;
});