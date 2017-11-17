/**
 * 查询
 */
function query() {
	//alert($("#userFormQuery").getElementsJson());
	$("#DataGridTable2").loadData($("#userFormQuery").getElementsJson());
}

function getList(value, row, index) {
	var pkListSex = {
		m : "男",
		f : "女"
	};
	return pkListSex[value]; //value + "|" + row + "|" + index;
}

function formateDate(value, row, index) {
	return (new Date(value)).format("yyyy/MM/dd");
}

/**
 * 删除
 */
function del(id) {
	$.get("<%=basePath%>user/delUser.do?id=" + id, function(data) {
		if ("success" == data.result) {
			alert("删除成功");
			window.location.reload();
		} else {
			alert("删除失败");
		}
	});
}