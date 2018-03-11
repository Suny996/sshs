/**
 * layui 插件扩展，以增加通用性
 * 
 * @author:Suny
 * @date:2018-3-10
 */
$.fn
		.extend({
			layuiLoad : function(param,comtype) {
				if(!comtype){
					comtype=$(this).data("type");
				}
				if(comtype=="nav"){
					$(this).renderNav($(this).getData($(this).data("url"),param), $(this).data("distype"));
				}
				layui.use('element', function() { //温馨提示：多次调用use并不会重复加载element.js，Layui内部有做模块cache处理。
					var element = layui.element;
					element.render('nav'); //重新对导航进行渲染。注：layui 2.1.6 版本新增
				});
			},
			/**
			 * layui 扩展 渲染nav
			 */
			renderNav : function(data, distype) {
				if(!data){
					return;
				}
				var classType = "";
				if (distype == "tree") {
					classType = "layui-nav-tree";
				}
				if (distype == "side") {
					classType = "layui-nav-tree layui-nav-side";
				}
				var ulHtml = '<ul class="layui-nav ' + classType + '">';
				for (var i = 0; i < data.length; i++) {
					if (data[i].spread) {
						ulHtml += '<li class="layui-nav-item layui-nav-itemed">';
					} else {
						ulHtml += '<li class="layui-nav-item">';
					}
					if (data[i].children !== undefined
							&& data[i].children !== null
							&& data[i].children.length > 0) {
						ulHtml += '<a href="javascript:;">';
						if (data[i].icon !== undefined && data[i].icon !== '') {
							if (data[i].icon.indexOf('fa-') !== -1) {
								ulHtml += '<i class="fa ' + data[i].icon
										+ '" aria-hidden="true" data-icon="'
										+ data[i].icon + '"></i>';
							} else {
								ulHtml += '<i class="layui-icon" data-icon="'
										+ data[i].icon + '">' + data[i].icon
										+ '</i>';
							}
						}
						ulHtml += '<cite>' + data[i].title + '</cite>'
						ulHtml += '</a>';
						ulHtml += '<dl class="layui-nav-child">'
						for (var j = 0; j < data[i].children.length; j++) {
							ulHtml += '<dd title="' + data[i].children[j].title
									+ '">';
							
							var dataUrl = data[i].children[j].href ;
							if(!dataUrl){
								dataUrl="javascript:;";
							}
							ulHtml += '<a href="' + dataUrl + '">';
							
							if (data[i].children[j].icon !== undefined
									&& data[i].children[j].icon !== '') {
								if (data[i].children[j].icon.indexOf('fa-') !== -1) {
									ulHtml += '<i class="fa '
											+ data[i].children[j].icon
											+ '" data-icon="'
											+ data[i].children[j].icon
											+ '" aria-hidden="true"></i>';
								} else {
									ulHtml += '<i class="layui-icon" data-icon="'
											+ data[i].children[j].icon
											+ '">'
											+ data[i].children[j].icon + '</i>';
								}
							}
							ulHtml += '<cite>' + data[i].children[j].title
									+ '</cite>';
							ulHtml += '</a>';
							ulHtml += '</dd>';
						}
						ulHtml += '</dl>';
					} else {
						var dataUrl = data[i].href;
						if(!dataUrl){
							dataUrl="javascript:;";
						}
						ulHtml += '<a href="' + dataUrl + '">';
						
						if (data[i].icon !== undefined && data[i].icon !== '') {
							if (data[i].icon.indexOf('fa-') !== -1) {
								ulHtml += '<i class="fa ' + data[i].icon
										+ '" aria-hidden="true" data-icon="'
										+ data[i].icon + '">&nbsp;</i>';
							} else {
								ulHtml += '<i class="'+ data[i].icon + '">'
										+ '</i>';
							}
						}
						ulHtml += '<cite>' + data[i].title + '</cite>'
						ulHtml += '</a>';
					}
					ulHtml += '</li>';
				}
				ulHtml += '</ul>';

				this.html(ulHtml);
			}
		});
