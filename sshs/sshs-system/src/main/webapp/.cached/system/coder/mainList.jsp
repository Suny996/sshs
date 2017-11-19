<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<base href="http://localhost:8080/system/">
<!-- require -->
<script type="text/javascript" src="scripts/require/require.js"></script>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script type="text/javascript" src="scripts/jquery/jquery.min.js"></script>


<!-- Bootstrap -->
<link href="scripts/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript"
	src="scripts/bootstrap/js/bootstrap.min.js"></script>


<!-- bootstrap-table -->
<link href="scripts/bootstrap-table/src/bootstrap-table.css"
	rel="stylesheet">
<!-- bootstrap-table -->
<script src="scripts/bootstrap-table/src/bootstrap-table.js"></script>
<script
	src="scripts/bootstrap-table/src/locale/bootstrap-table-zh-CN.js"></script>

<!-- bootstrap-select -->
<link rel="stylesheet"
	href="scripts/bootstrap-select/dist/css/bootstrap-select.css">
<!-- bootstrap-select -->
<script src="scripts/bootstrap-select/dist/js/bootstrap-select.js"></script>


<!-- bootstrap-datetimepicker -->
<link rel="stylesheet"
	href="scripts/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css">
<!-- bootstrap-datetimepicker -->
<script type="text/javascript"
	src="scripts/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"
	charset="UTF-8"></script>
<script type="text/javascript"
	src="scripts/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"
	charset="UTF-8"></script>

<!-- bootstrap paginator -->
<script type="text/javascript"
	src="scripts/bootstrap-paginator/build/bootstrap-paginator.min.js"></script>

<!-- Jquery.easyUI -->
<!-- <link rel="stylesheet" type="text/css"
	href="scripts/jquery-easyui/themes/bootstrap/easyui.css">

<link rel="stylesheet" type="text/css"
	href="scripts/jquery-easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="scripts/jquery-easyui/demo/demo.css">
<script type="text/javascript" src="scripts/jquery-easyui/jquery.easyui.min.js"></script>
 -->


<!-- dataTables -->
<!-- <link rel="stylesheet" type="text/css"
	href="scripts/dataTables/media/css/dataTables.bootstrap4.css">
<script type="text/javascript"
	src="scripts/dataTables/media/js/jquery.dataTables.js"></script>
<script type="text/javascript"
	src="scripts/dataTables/media/js/dataTables.bootstrap4.js"></script> -->


<!-- 自定义js ，扩展翻页table组件 -->
<script type="text/javascript" src="scripts/js/bootstrap-table-ext.js"></script>

</head>
<body>
	<script type="text/javascript" src="/system/system/coder/mainList.js.dw"></script><form action="" method="post" xid="queryForm" style="height: 44px;" name="queryForm">
<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4 form-inline">
<div class="form-group">
<label for="" class="control-label"	style="white-space:nowrap;">表名:</label>
<div class="input-group">
<input type="text" class="form-control" id="" name="tableName" placeholder="表名"></div></div>
</div>
<div class="col-xs-12 col-sm-12 col-md-6 col-lg-4 form-inline">
<div class="form-group">
<label for="" class="control-label"	style="white-space:nowrap;">表描述:</label>
<div class="input-group">
<input type="text" class="form-control" id="" name="tableComment" placeholder="表描述"></div></div>
</div>
<select name="coderFlag" bind-options="Dict.getDictItems(&quot;YN&quot;)" bind-optionsvalue="dictCode" bind-optionslabel="dictName" bind-optionscaption="全部">
</select>
<a class="btn btn-default" label="查询" name="btnQuery" icon="linear linear-magnifier" onclick="btnQueryClick">
<i xid="i1" class="linear linear-magnifier">
</i>
<span xid="span1">
查询</span>
</a>
<a class="btn btn-default" label="重置" name="btnReset" icon="linear linear-cross">
<i xid="i2" class="linear linear-cross">
</i>
<span xid="span8">
重置</span>
</a>
</form>
<table id="mainListTable" class="table table-bordered" data-list="{sex:[{m:男},{f:女}]}" data-url="<%=basePath%>user/getUserList.do" data-striped="false">
<thead>
<tr>
<th data-field="tableName">
表名</th>
<th data-field="tableComment">
表描述</th>
<th data-field="coderFlag" data-formatter="getList">
是否已生成代码</th>
<th data-field="handle" data-formatter="formateDate">
操作</th>
</tr>
</thead>
</table>
<div component="$UI/system/components/justep/pagerBar/pagerBar" class="x-pagerbar container-fluid" xid="pagerBar1" data="tableListData">
<div class="row" xid="div3">
<div class="col-sm-3" xid="div4">
<div class="x-pagerbar-length" xid="div5">
<label component="$UI/system/components/justep/pagerLimitSelect/pagerLimitSelect" class="x-pagerlimitselect" xid="pagerLimitSelect1">
<span xid="span2">
显示</span>
<select component="$UI/system/components/justep/select/select" class="form-control input-sm" xid="select2">
<option value="5" xid="default1">
5</option>
<option value="10" xid="default2">
10</option>
<option value="20" xid="default3">
20</option>
<option value="50" xid="default4">
50</option>
<option value="100" xid="default5">
100</option>
</select>
<span xid="span3">
条</span>
</label>
</div>
</div>
<div class="col-sm-3" xid="div6">
<div class="x-pagerbar-info" xid="div7">
当前显示0条，共0条</div>
</div>
<div class="col-sm-6" xid="div8">
<div class="x-pagerbar-pagination" xid="div9">
<ul class="pagination" component="$UI/system/components/bootstrap/pagination/pagination" xid="pagination1">
<li class="prev" xid="li1">
<a href="#" xid="a1">
<span aria-hidden="true" xid="span4">
«</span>
<span class="sr-only" xid="span5">
Previous</span>
</a>
</li>
<li class="next" xid="li2">
<a href="#" xid="a2">
<span aria-hidden="true" xid="span6">
»</span>
<span class="sr-only" xid="span7">
Next</span>
</a>
</li>
</ul>
</div>
</div>
</div>
</div>

</body>

</html>

