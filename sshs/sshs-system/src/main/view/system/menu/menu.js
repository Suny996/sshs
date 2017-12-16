define(function(require) {
	var Model = {};
	Model.init = function() {
		$.loadData("system/menu/getMenu.do", {
				"menuId" : Model["params"]["menuId"]
			});
	}

	Model.save = function() {
		$.sendRequest("system/menu/save.do", $("#menuForm").getElementsJson());
	};
	return Model;
});