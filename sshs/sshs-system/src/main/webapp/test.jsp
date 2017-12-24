<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<base href="http://localhost:8080/system/">

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script type="text/javascript"
	src="scripts/jquery-3.2.1/jquery-3.2.1.js">
	
</script>

<!-- Bootstrap -->
<link href="scripts/bootstrap-3.3.7/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script type="text/javascript"
	src="scripts/bootstrap-3.3.7/dist/js/bootstrap.min.js"></script>

<!-- bootstrap-table -->
<link href="scripts/bootstrap-table/src/bootstrap-table.css"
	rel="stylesheet">
<!-- bootstrap-table -->
<script type="text/javascript"
	src="scripts/bootstrap-table/src/bootstrap-table.js"></script>
<script type="text/javascript"
	src="scripts/bootstrap-table/src/locale/bootstrap-table-zh_CN.js"></script>

<!-- select2 bootstrap-->
<link rel="stylesheet"
	href="scripts/select2-master/dist/css/select2.css">
<link rel="stylesheet"
	href="scripts/select2-bootstrap-theme-master/dist/select2-bootstrap.css">
<!-- select2 -->
<script type="text/javascript"
	src="scripts/select2-master/dist/js/select2-sshs.js"></script>
<script type="text/javascript"
	src="scripts/select2-master/dist/js/i18n/zh_CN.js"></script>

<!-- bootstrap-datetimepicker -->
<link rel="stylesheet"
	href="scripts/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css">
<!-- bootstrap-datetimepicker -->
<script type="text/javascript"
	src="scripts/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"
	charset="UTF-8"></script>
<script type="text/javascript"
	src="scripts/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh_CN.js"
	charset="UTF-8"></script>

<!-- bootstrap paginator -->
<script type="text/javascript"
	src="scripts/bootstrap-paginator/build/bootstrap-paginator.min.js"></script>

<!-- bootstrap-switch -->
<link rel="stylesheet"
	href="scripts/bootstrap-switch-master/dist/css/bootstrap3/bootstrap-switch.min.css">
<script
	src="scripts/bootstrap-switch-master/dist/js/bootstrap-switch.min.js"></script>

<script type="text/javascript" src="scripts/bootbox-master/bootbox.js"></script>


<!-- 引用justep的样式表 -->
<link href="scripts/justep/comp.min.css" rel="stylesheet">
<!-- 自定义css样式 -->
<link rel="stylesheet" type="text/css"
	href="scripts/sshs/css/component.css">
<!-- 自定义js ，扩展翻页table组件等 -->
<!-- <script type="text/javascript" src="scripts/sshs/js/component.js"></script> -->

<!-- require -->
<script type="text/javascript" src="scripts/requirejs/require.js"></script>


</head>
<body>
	<div class="col-lg-4">
	
		<div class="input-group"><label  class="x-label x-right control-label"	style="white-space:nowrap;">表名:</label>
			<div class="input-group-btn">
				<button type="button" 
					class="btn btn-default dropdown-toggle" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false">
					<span id="aaaaabtn" style="padding-right:3px;">等于</span><span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li><a href="javascript:$('#aaaaabtn').text(' 等于');">等于</a></li>
					<li><a href="javascript:$('#aaaaabtn').text('不等于');">不等于</a></li>
				</ul>
			</div>
			<!-- /btn-group -->
			<input type="text" class="form-control" aria-label="...">
		</div>
		<!-- /input-group -->
	</div>
	<!-- /.col-lg-6 -->

	<div class="col-lg-6">
		<div class="input-group">
			<div class="input-group-btn">
				<button type="button" class="btn btn-default dropdown-toggle"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					Action <span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li><a href="#">Action</a></li>
					<li><a href="#">Another action</a></li>
					<li><a href="#">Something else here</a></li>
					<li role="separator" class="divider"></li>
					<li><a href="#">Separated link</a></li>
				</ul>
			</div>
			<!-- /btn-group -->
			<input type="text" class="form-control" aria-label="...">
		</div>
		<!-- /input-group -->
	</div>
	<!-- /.col-lg-6 -->
</body>

</html>


<script type="text/javascript">
	var _locale = {};
	_locale["locale"] = "zh_CN";
	//bootbox.setLocale("zh_CN");
</script>
<script type="text/javascript">
	function aaaa() {
		alert($('#aaaaabtn').text());
		$('#aaaaabtn').text('不等于');
	}
</script>