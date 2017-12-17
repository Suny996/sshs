/**
 * jquery扩展，以增加通用性
 * 
 * @author:Suny
 * @date:2017-10-10
 */
$.fn.extend({
	/**
	 * 将form组件内的表单元素打包成json对象
	 * 
	 * @param {parameter}
	 *            需扩展的json对象
	 * @method getElementsJson
	 */
	getElementsJson : function(parameter) {
		var params = $(this).serializeJson();
		if (!parameter) {
			parameter = {};
		}
		return $.extend(parameter, params);
	},
	/**
	 * 将form里面的内容序列化成json 相同的checkbox用分号拼接起来
	 * 
	 * @method serializeJson
	 */
	serializeJson : function() {
		var serializeObj = {};
		var array = this.serializeArray();
		$(array).each(function() {
			var value = null;
			if (this.value) {
				value = this.value;
			}
			if (serializeObj[this.name]) {
				serializeObj[this.name] += ';' + value;
			} else {
				serializeObj[this.name] = value;
			}
		});
		return serializeObj;
	},
	/**
	 * 将json对象赋值给form
	 * 
	 * @param {data}
	 *            需要给form赋值的json对象
	 * @method putJsonElements
	 */
	putJsonElements : function(data) {
		var obj = data;
		var key, value, tagName, type, arr;
		for (x in obj) {
			key = x;
			value = obj[x];
			$("[name='" + key + "'],[name='" + key + "[]']").each(function() {
				tagName = $(this)[0].tagName;
				type = $(this).attr('type');
				if (tagName == 'INPUT') {
					if (type == 'radio') {
						$(this).attr('checked', $(this).val() == value);
					} else if (type == 'checkbox') {
						arr = value.split(',');
						for (var i = 0; i < arr.length; i++) {
							if ($(this).val() == arr[i]) {
								$(this).attr('checked', true);
								break;
							}
						}
					} else {
						$(this).val(value);
					}
				} else if (tagName == 'SELECT' || tagName == 'TEXTAREA') {
					$(this).val(value);
				}

			});
		}
	},
	/**
	 * 显示指定页面
	 */
	showPage : function(url, param) {
		$(this).load(url, $.extend({
			"_pageType" : "body"
		}, param));
	},
	/**
	 * 从后台获取数据，并回填到页面
	 */
	loadData : function(url, param) {
		$.getJSON(url, param, function(data) {
			$(this).putJsonElements(data);
		});
	}
});

$.extend({
	/**
	 * 显示指定页面
	 */
	showPage : function(url, param, target) {
		if (target) {
			target.showPage(url, param);
		} else {
			$("div[type='page']").showPage(url, param);
			// $("body").showPage(url, param);
		}
	},
	/**
	 * 从后台获取数据，并回填到页面
	 */
	loadData : function(url, param, target) {
		if (target) {
			target.loadData(url, param);
		} else {
			$("body").loadData(url, param);
		}
	},
	/**
	 * 发送数据请求到后台，并执行对应服务
	 */
	sendRequest : function(url, data) {
		$.ajax({
			type : "post",
			cache : false,
			async : false,
			dataType : "json",
			contentType : "application/json",
			url : url,
			data : JSON.stringify(data),
			success : function(result) {
				$.alert(result["code"] + "" + result["msg"]);
			},
			error : function(resutl) {
				$.alert(result["code"] + "" + result["msg"]);
			}
		});
	},
	alert : function(msg) {
		bootbox.alert(msg);
	},
	confirm : function(msg, callback) {
		bootbox.confirm(msg, callback);
	}
});
