<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/sframe/jsp/head.jsp"%>
<title>添加用户</title>

<script type="text/javascript">
	function addUser() {
		var form = document.forms[0];
		form.action = "user/addUser.do";
		form.method = "post";
		form.submit();
	}
</script>
</head>

<body>
	<h1>添加用户</h1>
	<form action="" name="userForm" class="form-horizontal">
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12 col-sm-6 col-md-3">
					<div class="form-group">
						<label for="inputUserName1" class="col-sm-2 control-label" style="white-space:nowrap;">姓名：</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputUserName1"
								name="userName" placeholder="姓名">
						</div>
					</div>
				</div>
				<div class="col-xs-12 col-sm-6 col-md-3">
					<div class="form-group">
						<label for="inputAge1" class="col-sm-2 control-label" style="white-space:nowrap;">年龄：</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputAge1" name="age"
								placeholder="年龄">
						</div>
					</div>
				</div>
				<div class="col-xs-12 col-sm-4 col-md-3">
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="button" class="btn btn-default" onclick="addUser()">添加</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<jsp:include page="/sframe/jsp/boot.jsp" flush="true" /><!--动态包含-->