define(function(require) {
	// 测试jquery-ui
	var text = require('text');
	var $ = require('jquery');
	alert("aaaaaaaaa:" + text);
	$("#btn2").click(function(){
		alert("btn2 clicked!"+$);
	});
	var Model = function() {
		this.callParent();
	};
	Model.test1 = function(event) {
		alert("index:" + event + "||" + text);
		text.test2(event);
	};
	return Model;
});