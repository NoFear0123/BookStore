<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
	<%@ include file="/pages/common/head.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
	<script type="text/javascript">
		$(function () {
			// 给a标签绑定单击事件，用于确认提示操作
			$("a.yes").click(function () {
				return  confirm("你确定签收此订单吗?");

			})
		})
	</script>
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
<body  style="background-image: url(${BasePath}/static/img/雪山.jpg); background-size: 1800px 750px;">
	
	<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo2.gif" >
			<span class="wel_word">我的订单</span>
		<%@ include file="/pages/common/login_success_menu.jsp"%>
	</div>
	
	<div id="main">
		
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情</td>
				<td>是否签收</td>
			</tr>		
			<c:forEach items="${sessionScope.myOrder}" var="myOrder">
				<tr>
					<td>${myOrder.createTime}</td>
					<td>${myOrder.price}</td>
					<c:if test="${myOrder.status==0}">
						<td>未发货</td>
					</c:if>
					<c:if test="${myOrder.status==-1}">
						<td>已收货</td>
					</c:if>
					<c:if test="${myOrder.status==1}">
						<td>发货中</td>
					</c:if>
					<td><a href="orderServlet?action=showOrderDetail&orderId=${myOrder.orderId}">查看详情</a></td>
					<td><a class="yes" href="orderServlet?action=receiverOrder&orderId=${myOrder.orderId}">确定签收</a></td>
				</tr>
			</c:forEach>
			

		</table>
		
	
	</div>

	<%@ include file="/pages/common/foot.jsp"%>

</body>
</html>