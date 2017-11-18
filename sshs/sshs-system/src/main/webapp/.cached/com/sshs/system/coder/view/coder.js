define(function(require) {
	var $ = require("jquery");
	require("$UI/aframe/aframe");// add by Suny 2017-10-25
	var justep = require("$UI/system/lib/justep");
	var baas = justep.Baas;
	var Model = function() {
		this.callParent();
	};

	Model.prototype.columnsDataBeforeRefresh = function(event) {
		var params = this.getContext().getRequestParameters();
		// 获取URL中的参数
		this.comp("columnsData").filters.setVars(params);
	};

	Model.prototype.button1Click = function(event) {
		var data = $.getElementsJson("coderForm");
		data.fields = this.comp("columnsData").toJson({
			format : 'simple',
			//excludeCols : [ "addFlag", "modifyFlag" ]
		}).rows;

		baas.sendRequest({
			url : "${Sys.baasServer}/coder",
			action : "runCoder.do",
			params : data,
			async : true,
			success : function(data1) {
				alert(data1["code"]+data1["message"]);
			}
		});
	};

	return Model;
});