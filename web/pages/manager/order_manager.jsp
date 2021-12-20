<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
	<%@ include file="/pages/common/head.jsp"%>
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
			<span class="wel_word">订单管理系统</span>
		<%@ include file="/pages/common/manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情</td>
				<td>发货</td>
			</tr>
			<c:forEach items="${sessionScope.orders}" var="orders">
				<tr>
					<td>${orders.createTime}</td>
					<td>${orders.price}</td>
					<td>${orders.status}</td>
					<td><a href="orderServlet?action=showOrderDetail&orderId=${orders.orderId}">查看详情</a></td>
					<td><a href="orderServlet?action=sendOrder&orderId=${orders.orderId}">点击发货</a></td>
				</tr>
			</c:forEach>

		</table>
	</div>

	<%@ include file="/pages/common/foot.jsp"%>

</body>
</html>