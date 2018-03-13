define(function(require) {
	var Model = {};
	Model.init = function() {
	    if(Model["params"]["menuCode"]){
			$.loadData("system/menu/menu.do", {
					"menuCode" : Model["params"]["menuCode"]
				});
		}
	}

	Model.save = function() {
		$.save("system/menu/menu.do", $("#menuForm").getElementsJson());
	};
	return Model;
});