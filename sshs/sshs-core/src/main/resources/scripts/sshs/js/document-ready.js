define(function(require) {
	var model = {};
	model.init = function(page) {
		/**
		 * 初始化select2组件
		 */
		$(page).find("select[type='select2']").select2({
			theme : "bootstrap",
			width : 'resolve'
		});

		/**
		 * 初始化bootstrapSwitch组件
		 */
		$(page).find('input[type="checkbox"], input[type="radio"]').filter(
				'[data-switch-init]').bootstrapSwitch();
		/**
		 * 初始化自定义查询条件组件
		 */
		var tabDefaultBtn = $(page).find(".tab-button-default");
		var contentId = tabDefaultBtn.attr("contentId");
		var form = $(page).find("#" + contentId).serializeArray();
		var showElement = "";
		var allConditionElements = [];
		$(form).each(function() {
			var element = $("[name='" + this.name + "']");
			if (!element.attr("ignore") || element.attr("ignore") == "false") {
				if (showElement) {
					showElement += ",\"" + this.name + "\"";
				} else {
					showElement += "\"" + this.name + "\"";
				}
			}
			allConditionElements.push({
				"name" : this.name,
				"label" : element.attr("label")
			});
		});
		tabDefaultBtn.attr("show-elements", "[" + showElement + "]");

		var demo2 = $(page).find('.customiseBox').doublebox({
			nonSelectedListLabel : '可选查询条件',
			selectedListLabel : '已选查询条件',
			preserveSelectionOnMove : 'moved',
			moveOnSelect : false,
			selectorMinimalHeight : 200,
			nonSelectedList : allConditionElements,
			optionValue : "name",
			optionText : "label",
			doubleMove : true,
		});

		$(page).find('.doublebox-container').find(".box1, .box2").addClass("col-lg-5");
		$(page).find('.doublebox-container').find(".btn-box").addClass("col-lg-1");
		$(page).find('.doublebox-container').find(".upBtn").hide();
		$(page).find('.doublebox-container').find(".downBtn").hide();
		
		
		/**
		 * 绑定自定义查询条件tab切换处理
		 * 
		 * @returns
		 */
		$(page).find('a[data-toggle="tab"]').on('shown.bs.tab', function(e) {
			// var activeTab = $(e.target).text();
			var array = $(e.target).attr("show-elements");
			if (!array) {
				return;
			}
			if (typeof array === "string") {
				array = JSON.parse(array);
			}
			var formId = $(e.target).attr("contentId");
			var form = $(page).find("#" + formId).serializeArray();
			$(form).each(function() {
				var ec = $(page).find("[name='" + this.name + "-Container']");
				if (ec && ec.length > 0) {
					if (array.length <= 0 || $.inArray(this.name, array) >= 0) {
						$(page).find("[name='" + this.name + "-Container']").show();
						$(page).find("[name='" + this.name + "']").attr("ignore", false);
					} else {
						$(page).find("[name='" + this.name + "-Container']").hide();
						$(page).find("[name='" + this.name + "']").attr("ignore", true);
					}
				}
			});
		});
	};
	return model;
});