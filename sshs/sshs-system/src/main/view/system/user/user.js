define(function(require) {
	var Model = {};
	Model.init = function() {
		$.loadData("system/user/getUser.do", {
				"userId" : Model["params"]["userId"]
			});
	}

	Model.save = function() {
		$.sendRequest("system/user/save.do", $("#userForm").getElementsJson());
	};
	return Model;
});