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
<link href="sframe/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- bootstrap-table -->
<link href="sframe/bootstrap-table/src/bootstrap-table.css" rel="stylesheet">
<!-- bootstrap-select -->
<link rel="stylesheet" href="sframe/bootstrap-select/dist/css/bootstrap-select.css">

<!-- bootstrap-datetimepicker -->
<link rel="stylesheet" href="sframe/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css">


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script type="text/javascript" src="sframe/jquery/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script type="text/javascript"
	src="sframe/bootstrap/js/bootstrap.min.js"></script>
<!-- bootstrap paginator -->
<script type="text/javascript"
	src="sframe/bootstrap-paginator/build/bootstrap-paginator.min.js"></script>

<!-- Jquery.easyUI -->
<!-- <link rel="stylesheet" type="text/css"
	href="sframe/jquery-easyui/themes/bootstrap/easyui.css">

<link rel="stylesheet" type="text/css"
	href="sframe/jquery-easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="sframe/jquery-easyui/demo/demo.css">
<script type="text/javascript" src="sframe/jquery-easyui/jquery.easyui.min.js"></script>
 -->


<!-- dataTables -->
<!-- <link rel="stylesheet" type="text/css"
	href="sframe/dataTables/media/css/dataTables.bootstrap4.css">
<script type="text/javascript"
	src="sframe/dataTables/media/js/jquery.dataTables.js"></script>
<script type="text/javascript"
	src="sframe/dataTables/media/js/dataTables.bootstrap4.js"></script> -->

<!-- bootstrap-table -->
<script src="sframe/bootstrap-table/src/bootstrap-table.js"></script>
<script src="sframe/bootstrap-table/src/locale/bootstrap-table-zh-CN.js"></script>
<!-- bootstrap-select -->
<script src="sframe/bootstrap-select/dist/js/bootstrap-select.js"></script>
<!-- bootstrap-datetimepicker -->
<script type="text/javascript" src="sframe/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="sframe/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript" src="sframe/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>

<!-- 自定义js ，扩展翻页table组件 -->
<script type="text/javascript"
	src="sframe/js/bootstrap-table-ext.js"></script>
	
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->