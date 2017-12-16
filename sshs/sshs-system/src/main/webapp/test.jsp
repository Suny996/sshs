<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<base href="http://localhost:8080/system/">

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script type="text/javascript" src="scripts/jquery/jquery.js">
	
</script>

<!-- Bootstrap -->
<link href="scripts/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript"
	src="scripts/bootstrap/js/bootstrap.min.js"></script>

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
<script type="text/javascript" src="scripts/sshs/js/component.js"></script>

<!-- require -->
<script type="text/javascript" src="scripts/requirejs/require.js"></script>


</head>
<body>
	<form id="coderForm">
		<div
			class="appearance x-label-edit x-label30 col-xs-12 col-sm-12 col-md-6 col-lg-4">
			<label for="870e9678a4e84a19b5e3a5da1fb7e448"
				class="x-label x-right control-label" style="white-space: nowrap;">描述:</label>
			<input type="text" class="x-edit form-control "
				id="870e9678a4e84a19b5e3a5da1fb7e448" name="title" placeholder="描述"
				type="text" name="title" />
		</div>
		<div
			class="appearance x-label-edit x-label30 col-xs-12 col-sm-12 col-md-6 col-lg-4">
			<label for="c835a893e27e4074b9ce68714b251c7e"
				class="x-label x-right control-label" style="white-space: nowrap;">模块名:</label>
			<input type="text" class="x-edit form-control "
				id="c835a893e27e4074b9ce68714b251c7e" name="modelName"
				placeholder="模块名" type="text" name="modelName" />
		</div>
		<div
			class="appearance x-label-edit x-label30 col-xs-12 col-sm-12 col-md-6 col-lg-4">
			<label for="3b7c86ac55274969a796fa7639d82796"
				class="x-label x-right control-label" style="white-space: nowrap;">模块名(中文):</label>
			<input type="text" class="x-edit form-control "
				id="3b7c86ac55274969a796fa7639d82796" name="modelNameCn"
				placeholder="模块名(中文)" name="modelNameCn" />
		</div>
		<div
			class="appearance x-label-edit x-label30 col-xs-12 col-sm-12 col-md-6 col-lg-4">
			<label for="0765d952390b42d8810a161517f1335b"
				class="x-label x-right control-label" style="white-space: nowrap;">功能名称:</label>
			<input type="text" class="x-edit form-control "
				id="0765d952390b42d8810a161517f1335b" name="functionName"
				placeholder="功能名称" name="functionName" />
		</div>
	</form>
	<div
		class="appearance x-button-edit col-xs-12 col-sm-12 col-md-6 col-lg-4">
		<button class="x-btn btn btn-primary"
			id="f6585f037a2c48c9af924d5911274671"
			onclick="javascript:Model.runCoder();" accessKey="c" 
			value="generate" >
			<i class="linear linear-highlight"> </i>生成代码
		</button>
		<button type="reset" class="x-btn btn btn-danger"
			id="3cd6c2b3d55546459659fd05c925ceb9" accessKey="r" type="reset">
			<i class="linear linear-cross"> </i>重置
		</button>
	</div>

</body>

</html>


<script type="text/javascript">
	var _locale = {};
	_locale["locale"] = "zh_CN";
	bootbox.setLocale("zh_CN");
</script>
<script type="text/javascript">
	var Model={runCoder:function(){
		var data = $("#coderForm").getElementsJson({
			//"fields" : $('#columnListTable').getDataEdited(true)
		});
		data["field"]="";//
		alert(JSON.stringify($('#columnListTable').getDataEdited(false)));
		$.sendRequest("system/coder/runCoder.do", data);
	}};
	/* require([ "/system/system/coder/coder.js.dw" ], function(model) {
		model["params"] = {
			"tableName" : "SYS_MENU"
		};
		if (typeof model.init === 'function') {
			//model.init.apply(null);
		}
		Model = model;
	});  */
</script>