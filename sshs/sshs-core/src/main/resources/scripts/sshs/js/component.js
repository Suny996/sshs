
$(document).ready(function() {
	$("select").select2({theme:"bootstrap",width: 'resolve'}); 
	$('input[type="checkbox"], input[type="radio"]').filter('[data-switch-init]').bootstrapSwitch();
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
 * 单元格渲染
 */
var _EditorRender = function(value, row, index,name,format) {
	var editor= $(format);
	editor.attr("index",index);
	editor.attr("field",name);
	if(editor.is('input') && editor.attr("type")==="text"){
		editor.attr("value",value);
	}
	if(editor.is('select')){
		var options= editor.filter("option");
		options.each(function(option){
			if(option.val()==value){
				options.attr("selected",true);
			}
		});
	}
	if(editor.is('button')){
		 editor.attr("onclick","javascript:"+editor.attr("action")+"("+JSON.stringify(row)+");");
	}
	return editor.get(0).outerHTML;
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