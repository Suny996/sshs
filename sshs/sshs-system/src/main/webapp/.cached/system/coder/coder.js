define(function(require) {
	var Model = function() {
		this.callParent();
	};

	Model.columnsDataBeforeRefresh = function(event) {
		var params = this.getContext().getRequestParameters();
		// 获取URL中的参数
		this.comp("columnsData").filters.setVars(params);
	};
	

	Model.button1Click = function(event) {
		var data = $.getElementsJson("coderForm");
		data.fields = $.getElementsJson("columnsData");

		$.ajax({
			url : "/system/coder",
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