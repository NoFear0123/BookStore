<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理</title>
	<%@ include file="/pages/common/head.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
	<%
		String BasePath = request.getScheme()
				+ "://"
				+ request.getServerName()
				+ ":"
				+request.getServerPort()
				+request.getContextPath()
				+ "/";

		pageContext.setAttribute("BasePath", BasePath);

	%>
</head>
<body style="background-image: url(${BasePath}/static/img/雪山.jpg); background-size: 1800px 750px;">
	
	<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo2.gif" >
			<span class="wel_word">后台管理系统</span>
		<%@ include file="/pages/common/manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<h1>欢迎管理员进入后台管理系统</h1>
	</div>

	<%@ include file="/pages/common/foot.jsp"%>

</body>
</html>