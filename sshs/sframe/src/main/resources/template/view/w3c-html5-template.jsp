<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="/ahTags" prefix="ah"%> 
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<base href="<%=basePath%>">
<!-- Bootstrap -->
<link href="aframe/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- bootstrap-table -->
<link href="aframe/bootstrap-table/src/bootstrap-table.css" rel="stylesheet">
<!-- bootstrap-select -->
<link rel="stylesheet" href="aframe/bootstrap-select/dist/css/bootstrap-select.css">

<!-- bootstrap-datetimepicker -->
<link rel="stylesheet" href="aframe/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css">


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script type="text/javascript" src="aframe/jquery/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script type="text/javascript"
	src="aframe/bootstrap/js/bootstrap.min.js"></script>
<!-- bootstrap paginator -->
<script type="text/javascript"
	src="aframe/bootstrap-paginator/build/bootstrap-paginator.min.js"></script>

<!-- Jquery.easyUI -->
<!-- <link rel="stylesheet" type="text/css"
	href="aframe/jquery-easyui/themes/bootstrap/easyui.css">

<link rel="stylesheet" type="text/css"
	href="aframe/jquery-easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="aframe/jquery-easyui/demo/demo.css">
<script type="text/javascript" src="aframe/jquery-easyui/jquery.easyui.min.js"></script>
 -->


<!-- dataTables -->
<!-- <link rel="stylesheet" type="text/css"
	href="aframe/dataTables/media/css/dataTables.bootstrap4.css">
<script type="text/javascript"
	src="aframe/dataTables/media/js/jquery.dataTables.js"></script>
<script type="text/javascript"
	src="aframe/dataTables/media/js/dataTables.bootstrap4.js"></script> -->

<!-- bootstrap-table -->
<script src="aframe/bootstrap-table/src/bootstrap-table.js"></script>
<script src="aframe/bootstrap-table/src/locale/bootstrap-table-zh-CN.js"></script>
<!-- bootstrap-select -->
<script src="aframe/bootstrap-select/dist/js/bootstrap-select.js"></script>
<!-- bootstrap-datetimepicker -->
<script type="text/javascript" src="aframe/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="aframe/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript" src="aframe/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>

<!-- 自定义js ，扩展翻页table组件 -->
<script type="text/javascript"
	src="aframe/js/bootstrap-table-ext.js"></script>
 ${_PageHeader}
</head>
${_PageBody}
</html>
${_PageFooter} ${outputException}
 