<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
<%--<link type="text/css" rel="stylesheet" href="static/css/style.css" >--%>
	<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function () {
			//给加入购物车按钮绑定单击时间
			$("button.addToCart").click(function () {
				/**
				 * 在事件响应的function函数 中，有一个this对象，这个this对象，是当前正在响应事件的dom对象
				 * @type {jQuery}
				 */
				/*if ($(this).attr("stock")==0){
					alert("库存不足！");
				}else {
					var bookId = $(this).attr("bookId");
					location.href = "http://localhost:8080/book/cartServlet?action=addItem&id="+bookId;
				}*/

				// 发ajax请求，添加商品到购物车
					var bookId = $(this).attr("bookId");
				$.getJSON("http://localhost:8080/book/cartServlet", "action=ajaxAddItem&id=" + bookId, function (data) {
					if ($(this).attr("stock")==0){
						alert("库存不足！");
					}else {
						$("#cartTotalCount").text("您的购物车中有" + data.totalCount + "件商品");
						$("#cartLastNamet2").text( data.lastName);
					}

				})
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
<body style="background-image: url(${BasePath}/static/img/雪山.jpg); background-size: 1800px 750px;">
<div id="header">
	<img class="logo_img" alt="" src="static/img/logo.gif" >
	<span class="wel_word">网上书城</span>
	<div>
<%--		如果还没登录显示登录--%>
		<c:if test="${empty sessionScope.user}">
			<a href="pages/user/login.jsp">登录</a> |
			<a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
		</c:if>
	<c:if test="${not empty sessionScope.user}">
		<span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临书城</span>
		<a href="orderServlet?action=showMyOrder&userId=${sessionScope.user.id}">我的订单</a>
		<a href="userServlet?action=logout">注销</a>
	</c:if>
		<a href="pages/cart/cart.jsp">购物车</a>

	<c:if test="${sessionScope.user.username.equals('root')}">
		<a href="pages/manager/manager.jsp">后台管理</a>
	</c:if>
	</div>
</div>
<div id="main">
		<div id="book">
			<%--价格查询--%>
			<div class="book_cond">
				<form action="client/bookServlet" method="get">
					<input type="hidden" name="action" value="pageByPrice">
					价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
						<input id="max" type="text" name="max" value="${param.max}"> 元
						<input type="submit" value="查询" />
				</form>
			</div>
			<%--加入购物车判断--%>
			<div style="text-align: center">
				<c:if test="${empty sessionScope.cart.items}">
					<%--购物车为空的输出--%>
					<span id="cartTotalCount"> </span>
					<div>
						<span style="color: red" id="cartLastName">当前购物车为空···请添加·</span>
					</div>
				</c:if>
				<c:if test="${not empty sessionScope.cart.items}">
					<%--购物车非空的输出--%>
					<span id="cartTotalCount">您的购物车中有${sessionScope.cart.totalCount}件商品</span>
					<div>
						您刚刚将<span style="color: red" id="cartLastName2">${sessionScope.lastName}</span>加入到了购物车中
					</div>
				</c:if>
			</div>
			<%--显示图书--%>
			<c:forEach items="${requestScope.page.items}" var="book">
			<div class="b_list">
				<div class="img_div">
					<img class="book_img" alt="" src="${book.imgPath}" />
				</div>
				<div class="book_info">
					<div class="book_name">
						<span class="sp1">书名:</span>
						<span class="sp2">${book.name}</span>
					</div>
					<div class="book_author">
						<span class="sp1">作者:</span>
						<span class="sp2">${book.author}</span>
					</div>
					<div class="book_price">
						<span class="sp1">价格:</span>
						<span class="sp2">${book.price}</span>
					</div>
					<div class="book_sales">
						<span class="sp1">销量:</span>
						<span class="sp2">${book.sales}</span>
					</div>
					<div class="book_amount">
						<span class="sp1">库存:</span>
						<span class="sp2">${book.stock}</span>
					</div>
					<div class="book_add">
						<button bookId="${book.id}" stock="${book.stock}" class="addToCart">加入购物车</button>
					</div>
				</div>
			</div>
			</c:forEach>

		</div>
	<%--静态包含 分页条--%>
	<%@include file="/pages/common/page_nav.jsp"%>
</div>
	<%@ include file="/pages/common/foot.jsp"%>
</body>
</html>