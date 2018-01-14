define(function(require) {
	var Model = {};
	Model.init = function() {
	    if(Model["params"]["${coder.idName}"]){
			$.loadData("${coder.modelName}/${coder.functionName}/${coder.classDeclare}.do", {
					"${coder.idName}" : Model["params"]["${coder.idName}"]
				});
		}
	}

	Model.save = function() {
		$.save("${coder.modelName}/${coder.functionName}/${coder.classDeclare}.do", $("#${coder.classDeclare}Form").getElementsJson());
	};
	return Model;
});