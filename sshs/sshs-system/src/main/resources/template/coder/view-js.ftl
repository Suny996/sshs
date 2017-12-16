define(function(require) {
	var Model = {};
	Model.init = function() {
		$.loadData("${coder.modelName}/${coder.functionName}/get${coder.className}.do", {
				"${coder.idName}" : Model["params"]["${coder.idName}"]
			});
	}

	Model.save = function() {
		$.sendRequest("${coder.modelName}/${coder.functionName}/save.do", $("#${coder.classDeclare}Form").getElementsJson());
	};
	return Model;
});