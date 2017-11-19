define(function(require) {
	// 测试jquery-ui
	var $ = require('jquery');
	var Model = {};
	Model.test2 = function(event) {
		alert("test2:" + event+"||"+$);
		$.ajax("bbb.xx");
	};
	return Model;
});