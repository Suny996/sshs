<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/sframe/jsp/head.jsp"%>
<title>编辑用户</title>

<script type="text/javascript">
	function updateUser() {
		var form = document.forms[0];
		form.action = "<%=basePath%>user/updateUser.do";
		form.method = "post";
		form.submit();
	}
</script>

</head>

<body>
	<h1>添加用户</h1>
	<form action="" name="userForm">
		<input type="hidden" name="id" value="${user.id }" /> 姓名：<input
			type="text" name="userName" value="${user.userName }" /> 年龄：<input
			type="text" name="age" value="${user.age }" /> <input type="button"
			value="编辑" onclick="updateUser()" />
	</form>


	<jsp:include page="/sframe/jsp/boot.jsp" flush="true" /><!--动态包含-->