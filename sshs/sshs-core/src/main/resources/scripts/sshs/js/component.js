
$(document).ready(function() {
	$("select[type='select2']").select2({theme:"bootstrap",width: 'resolve'}); 
	$('input[type="checkbox"], input[type="radio"]').filter('[data-switch-init]').bootstrapSwitch();
	
	var tabDefaultBtn=$(".tab-button-default");
	var contentId=tabDefaultBtn.attr("contentId");
	var form = $("#"+contentId).serializeArray();
	var showElement ="";
	var allConditionElements =[];
	$(form).each(function() {
		var element = $("[name='" + this.name + "']");
		if(!element.attr("ignore") || element.attr("ignore") =="false"){
			if(showElement){
				showElement+=",\""+ this.name+"\"";
			}else{
				showElement+="\""+ this.name+"\"";
			}
		}
		allConditionElements.push({"name":this.name,"label":element.attr("label")});
	});
	tabDefaultBtn.attr("show-elements","["+showElement+"]");
	
	var demo2 = $('.customiseBox').doublebox({
		nonSelectedListLabel : '可选查询条件',
		selectedListLabel : '已选查询条件',
		preserveSelectionOnMove : 'moved',
		moveOnSelect : false,
		selectorMinimalHeight : 200,
		nonSelectedList :allConditionElements,
		optionValue : "name",
		optionText : "label",
		doubleMove : true,
	});

	$('.doublebox-container').find(".box1, .box2")
			.addClass("col-lg-5");
	$('.doublebox-container').find(".btn-box").addClass(
			"col-lg-1");
	$('.doublebox-container').find(".upBtn").hide();
	$('.doublebox-container').find(".downBtn").hide();
	
});
/**
 * 自定义查询条件tab切换处理
 * 
 * @returns
 */
$(function() {
	$('a[data-toggle="tab"]').on('shown.bs.tab', function(e) {
		// var activeTab = $(e.target).text();
		var array = $(e.target).attr("show-elements");
		if(!array){
			return;
		}
		if (typeof array === "string") {
			array = JSON.parse(array);
		}
		var formId = $(e.target).attr("contentId");
		var form = $("#"+formId).serializeArray();
		$(form).each(function() {
			var ec = $("[name='" + this.name + "-Container']");
			if (ec && ec.length > 0) {
				if (array.length<=0 || $.inArray(this.name, array) >= 0) {
					$("[name='" + this.name + "-Container']").show();
					$("[name='" + this.name + "']").attr("ignore",false);
				} else {
					$("[name='" + this.name + "-Container']").hide();
					$("[name='" + this.name + "']").attr("ignore",true);
				}
			}
		});
	});
});

/**
 * 添加自定义查询条件方法
 */
var _addCustomise = function(tabs,selects){
	var selection = selects.find("#bootstrap-duallistbox-selected-list_doublebox");
	var showElements=[];
	if(selection.is("select")){
	   var options = selection.children();
	   if(options.length<=0){
		   $.alert("必须选择查询条件！");
		   return;
	   }
	   for(var i=0;i<options.length;i++){
			 showElements.push(options[i].value);
		}
	}
	var name = $("#customisetabName").val();
	if(!name){
		 $.alert("名称不能为空！");
		 return;
	}
	$.ajax({
		type : "post",
		cache : false,
		async : false,
		dataType : "json",
		contentType : "application/json",
		url : "core/custmise/save.do",
		data : JSON.stringify({"customiseName":name,"fieldContents":JSON.stringify(showElements),"pageId":tabs.attr("pageId")}),
		success : function(result) {
			$.alert(result["code"] + "" + result["msg"]+",请刷新页面查看效果！");
			 $("#"+ selects.attr("dialog")).modal('hide');
		},
		error : function(resutl) {
			$.alert(result["code"] + "" + result["msg"]);
		}
	});
};

/**
 * 删除自定义查询条件方法
 */
var _delCustomise = function(customiseId,customiseName){
	$.confirm("确定要删除查询条件["+customiseName+"]吗？" ,function(ok){
		if(ok){
			$.ajax({
				type : "post",
				cache : false,
				async : false,
				dataType : "json",
				contentType : "application/json",
				url : "core/custmise/delete.do",
				data : JSON.stringify({"customiseId":customiseId}),
				success : function(result) {
					$.alert(result["code"] + "" + result["msg"]+",请刷新页面查看效果！");
					 $("#"+ selects.attr("dialog")).modal('hide');
				},
				error : function(resutl) {
					$.alert(result["code"] + "" + result["msg"]);
				}
			});
		}
	});	
};
/**
 * 数据字典项翻译-table渲染用
 */
var _DICTIONNARY={};
var _DictTranslate = function(value, row, index, name, format) {// 翻译table里的字典项目，已修改過bootstrapTable加入format參數
	var dict = _DICTIONNARY[format];
	if(dict){
		 for(var i in dict){  
		        if(dict[i]["key"]==value){  // item 表示Json串中的属性，如'name'
		            var val =dict[i][_locale["locale"]];// key所对应的value
		            if(!val){
		            	val = dict[i]["value"];
		            }
		            if(val){
		            	return val;
		            } else if(dict[i]["children"]){
		            	var cd = dict[i]["children"];
		            	for(var j in cd){  
		     		        if(cd[j]["key"]==value){ 
		     		            val =cd[j][_locale["locale"]];// key所对应的value
		     		            if(!val){
		     		            	val = cd[j]["value"];
		     		            }
		     		            if(val){
		     		            	return val;
		     		            }
		     		        }
		     		  }
		            }
		        }  
		    }  
	}
	return value; 
};

/**
 * 显示行号
 */
var _RowNumber = function(value, row, index,name,format) {
	return index+1; // 序号
};

/**
 * 日期格式化
 */
var _DateFormat = function(value, row, index,name,format) {
	return (new Date(value)).format(format); // 格式化table里的日期类字段，已修改過bootstrapTable
												// 加入format參數
};
Date.prototype.format = function(fmt) { // author: meizz
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds() // 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for (var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
};

/**
 * 日历空间初始化
 */
var _InitDatePicker=function(id,format,lang) {
	if(!lang){
		lang="zh_CN"
	}
	 var options = {
		        language:  lang,
		        weekStart: 1,
		        todayBtn:  1,
				autoclose: 1,
				todayHighlight: 1,
				startView: 2,
				minView: 2,
				forceParse: 1
		    };

	if (format && format.toLowerCase().indexOf("yy")>=0 && format.toLowerCase().indexOf("hh")>=0) {
		options["minView"]= 0;
	}
	if (format && format.toLowerCase().indexOf("yy")<0 && format.toLowerCase().indexOf("hh")>=0) {
		options["startView"]=1;
		options["minView"]= 0;
		options["maxView"]= 1;
	}
	$("#" + id ).datetimepicker(options);
}; 