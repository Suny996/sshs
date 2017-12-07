/**
 * bootstrapTable ajax获取后台数据 paginator插件扩展，以增加通用性 author:Suny date:2017-10-10
 */
$.fn.extend({
	getElementsJson : function(parameter) {// 将form组件内的表单元素打包成json对象
		var params = $(this).serializeArray();
		if (!parameter) {
			parameter = {};
		}
		for (x in params) {
			parameter[params[x].name] = params[x].value;
		}
		return parameter;
	},
	loadData : function(params) {// bootstrapTable 扩展 初始化
		if (!params) {
			params = {};
		} else {
			params = {
				"variables" : params
			};
		}
		$(this).bootstrapTable("destroy");
		var columns;
		if (typeof $(this).data("columns") === 'string') {
			columns = JSON.parse($(this).data("columns").replace(/\'/g, "\""));
		} else {
			columns = $(this).data("columns");
		}
		if (typeof params === 'string') {
			params = JSON.parse(params);
		}
		$(this).bootstrapTable({
			url : $(this).data("url") ? $(this).data("url") : "", // 请求后台的URL（*）
			method : $(this).data("method") ? $(this).data("method") : 'post', // 请求方式（*）
			toolbar : '#toolbar', // 工具按钮用哪个容器
			striped : $(this).data("striped") ? $(this).data("striped") : false, // 是否显示行间隔色
			cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			pagination : true, // 是否显示分页（*）
			sortable : true, // 是否启用排序
			sortName : $(this).data("sortName"), // 初始化的时候排序的字段
			sortOrder : $(this).data("sortOrder"), // 排序方式
			queryParams : params, // 传递参数（*）
			sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
			contentType : "application/json", // "application/x-www-form-urlencoded",
												// //请求数据内容格式 默认是
												// application/json
												// 自己根据格式自行服务端处理
			dataType : "json", // 期待返回数据类型
			pageNumber : 1, // 初始化加载第一页，默认第一页
			pageSize : $(this).data("pageSize") ? $(this).data("pageSize") : 10, // 每页的记录行数（*）
			pageList : $(this).data("pageList") ? $(this).data("pageList"):[ 5,10,25,50,100], // 可供选择的每页的行数（*）
			// search : true, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
			// strictSearch : true,
			dataField : "rows",
			totalField : "totalCount",
			showColumns : true, // 是否显示所有的列
			showRefresh : true, // 是否显示刷新按钮
			minimumCountColumns : 2, // 最少允许的列数
			clickToSelect : true, // 是否启用点击选中行
			// height : 500, //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
			uniqueId : "ID", // 每一行的唯一标识，一般为主键列
			showToggle : $(this).data("showToggle") ? $(this).data("showToggle") : true, // 是否显示详细视图和列表视图的切换按钮
			// cardView : false, //是否显示详细视图
			// detailView : false, //是否显示父子表
			columns : columns
		});
	},
	showPage:function(url,param){// 发送页面请求，替换到当前对象中
		if(_locale["locale"] && url.indexOf("locale=")<0){
			if(url.indexOf("?")<0){
				url=url+"?locale="+_locale["locale"];
			}else{
				url=url+"&locale="+_locale["locale"];
			}
		}
		$(this).load(url,$.extend({"_pageType":"body"}, param));
	}
});

$.extend({
	showPage:function(url,param,target){
			if(target){
				$(target).showPage(url,param);
			}else{
				$("body").showPage(url,param);
			}
		}
	});

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

$(document).ready(function() { $("select").select2({theme:"bootstrap",width: 'resolve'}); });
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
