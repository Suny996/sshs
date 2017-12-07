<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<base href="http://localhost:8080/system/">

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- <script type="text/javascript" src="scripts/jquery/jquery.js">
	
</script> -->

<!-- Bootstrap -->
<!--<link href="scripts/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript"
	src="scripts/bootstrap/js/bootstrap.min.js"></script>

  -->
<!-- bootstrap-select -->
<!-- <link rel="stylesheet"
	href="scripts/bootstrap-select/dist/css/bootstrap-select.css"> -->
<!-- bootstrap-select -->
<!-- <script type="text/javascript"
	src="scripts/bootstrap-select/dist/js/bootstrap-select.js"></script> -->
<!-- <script type="text/javascript"
	src="scripts/bootstrap-select/dist/js/i18n/defaults-zh_CN.js"></script> -->

<!-- select2 bootstrap-->
<link rel="stylesheet"
	href="scripts/select2-master/dist/css/select2.css">
<link rel="stylesheet"
	href="scripts/select2-bootstrap-theme-master/dist/select2-bootstrap.css">
<!-- select2 -->
<!-- <script type="text/javascript"
	src="scripts/select2-master/dist/js/select2-sshs.js"></script>
<script type="text/javascript"
	src="scripts/select2-master/dist/js/i18n/zh_CN.js"></script> -->

<!-- bootstrap-datetimepicker -->
<link rel="stylesheet"
	href="scripts/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css">
<!-- bootstrap-datetimepicker -->
<!-- <script type="text/javascript"
	src="scripts/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"
	charset="UTF-8"></script>
<script type="text/javascript"
	src="scripts/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh_CN.js"
	charset="UTF-8"></script> -->

<!-- bootstrap paginator -->
<!-- <script type="text/javascript"
	src="scripts/bootstrap-paginator/build/bootstrap-paginator.min.js"></script> -->

<!-- Jquery.easyUI -->
<!-- <link rel="stylesheet" type="text/css"
	href="scripts/jquery-easyui/themes/bootstrap/easyui.css">

<link rel="stylesheet" type="text/css"
	href="scripts/jquery-easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="scripts/jquery-easyui/demo/demo.css">
<script type="text/javascript"
	src="scripts/jquery-easyui/jquery.easyui.min.js"></script> -->

<!-- dataTables -->
<!-- <link rel="stylesheet" type="text/css"
	href="scripts/dataTables/media/css/dataTables.bootstrap4.css">
<script type="text/javascript"
	src="scripts/dataTables/media/js/jquery.dataTables.js"></script>
<script type="text/javascript"
	src="scripts/dataTables/media/js/dataTables.bootstrap4.js"></script> -->

<!-- 引用justep的样式表 -->
<link href="scripts/justep/comp.min.css" rel="stylesheet">
<!-- 自定义css样式 -->
<link rel="stylesheet" type="text/css"
	href="scripts/sshs/css/component.css">
<!-- 自定义js ，扩展翻页table组件等 -->
<!-- <script type="text/javascript" src="scripts/sshs/js/component.js"></script> -->

<!-- require -->
<!-- <script type="text/javascript" src="scripts/requirejs/require.js"></script> -->

<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<link href="scripts/bootstrap-switch-master/docs/css/highlight.css"
	rel="stylesheet">
<link
	href="https://unpkg.com/bootstrap-switch/dist/css/bootstrap3/bootstrap-switch.css"
	rel="stylesheet">
<!-- <link href="https://getbootstrap.com/assets/css/docs.min.css"
	rel="stylesheet">
<link href="scripts/bootstrap-switch-master/docs/css/main.css"
	rel="stylesheet"> -->
</head>
<body>
	<div class="switch" data-on="info" data-off="success">
		<input type="checkbox" checked />
	</div>

	<input id="switch-onText" type="checkbox" checked="checked"
		data-on="primary" data-off="info" data-on-text="Yes"
		data-switch-init="true">
	<button onclick="run();">alert</button>
	</div>
</body>
</html>
<!-- <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script> -->
<script type="text/javascript" src="scripts/jquery/jquery.js"></script>
<!-- Bootstrap -->
<script type="text/javascript"
	src="scripts/bootstrap/js/bootstrap.min.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="scripts/bootstrap-switch-master/docs/js/highlight.js"></script>
<!-- <script src="https://unpkg.com/bootstrap-switch"></script> -->
<script
	src="scripts/bootstrap-switch-master/dist/js/bootstrap-switch.min.js"></script>
<script type="text/javascript" src="scripts/bootbox-master/bootbox.js"></script>
<!-- <script src="scripts/bootstrap-switch-master/docs/js/main.js"></script> -->
<script type="text/javascript">
	var _locale = {};
	_locale["locale"] = "zh_CN";
</script>
<script type="text/javascript">
	$('input[type="checkbox"], input[type="radio"]').filter(
			'[data-switch-init]').bootstrapSwitch();
	bootbox.setLocale("zh_CN");
	var run = function() {
/* 		bootbox
				.confirm({
					title : "测试?",
					message : "Do you want to activate the Deathstar now? This cannot be undone.",
					callback : function(result) {
						console.log('This was logged in the callback: '
								+ result);
					}
				}); */

		alert(bootbox.confirm("测试?"));
	}
</script>