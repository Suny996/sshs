<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/sframe/jsp/head.jsp"%>
<title>用户列表</title>
<script type="text/javascript"
	src="sample/sample1/allUser.js"></script>
</head>

<body>
	<h6>
		<a href="<%=basePath%>user/toAddUser.do">添加用户</a>
	</h6>

	<div class="table-responsive">
		<form action="" name="userFormQuery" id="userFormQuery" class="form-horizontal">
			<div class="container-fluid">
				<div class="row">
					<div class="col-xs-12 col-sm-6 col-md-3">
						<div class="form-group">
							<label for="inputUserName1" class="col-sm-2 control-label"
								style="white-space:nowrap;">姓名：</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="inputUserName1"
									name="userName" placeholder="姓名">
							</div>
						</div>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-3">
						<div class="form-group">
							<label for="inputAge1" class="col-sm-2 control-label"
								style="white-space:nowrap;">年龄：</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="inputAge1"
									name="age" placeholder="年龄">
							</div>
						</div>
					</div>
					<div class="col-xs-12 col-sm-4 col-md-3">
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="button" class="btn btn-default"
									onclick='query();'>查询</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
		<table class="table table-bordered" id="DataGridTable2"
			data-pagination="before" data-pagesize=15
			data-url="<%=basePath%>user/getUserList.do" data-cols="userName,age"
			data-title="姓名,年龄" data-attr="align=left,align=center">
			<tr class='active'>
				<th>姓名</th>
				<th>年龄</th>
			</tr>
		</table>
	</div>
	<jsp:include page="/sframe/jsp/boot.jsp" flush="true" /><!--动态包含-->