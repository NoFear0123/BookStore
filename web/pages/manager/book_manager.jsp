<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
	<%@ include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function () {
			// 给删除的a标签绑定单击事件，用于删除的确认提示操作
			$("a.deleteClass").click(function () {
				// 在事件的function函数中，有一个this对象。这个this对象，是当前正在响应事件的dom对象。
				/**
				 * confirm是确认提示框函数
				 * 参数是它的提示内容
				 * 它有两个按钮，一个确认，一个是取消。
				 * 返回true表示点击了，确认，返回false表示点击取消。
				 */
				return  confirm("你确定删除出【" + $(this).parent().parent().find("td:first").text() + "】?");
				// return false// 阻止元素的默认行为===不提交请求

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
<%--<%@include file="/pages/common/head.jsp"%>--%>
<div id="header">
	<img class="logo_img" alt="" src="../../static/img/logo.gif" >
	<span class="wel_word">图书管理系统</span>

	<%-- 静态包含 manager管理模块的菜单  --%>
	<%@include file="/pages/common/manager_menu.jsp"%>


</div>

<div id="main">

	<table>
		<tr>
			<td>名称</td>
			<td>价格</td>
			<td>作者</td>
			<td>销量</td>
			<td>库存</td>
			<td colspan="2">操作</td>
		</tr>

<%--		<c:forEach items="${requestScope.page.item}" var="book">--%>
		<c:forEach items="${requestScope.page.items}" var="book">
			<tr>
				<td>${book.name}</td>
				<td>${book.price}</td>
				<td>${book.author}</td>
				<td>${book.sales}</td>
				<td>${book.stock}</td>
				<td><a href="manager/bookServlet?action=getBook&id=${book.id}&pageNo=${requestScope.page.pageNo}">修改</a></td>
				<td><a class="deleteClass" class="deleteClass" href="manager/bookServlet?action=delete&id=${book.id}&pageNo=${requestScope.page.pageNo}">删除</a></td>
			</tr>
		</c:forEach>

		<tr>
			<td>.....</td>
			<td>.....</td>
			<td>.....</td>
			<td>.....</td>
			<td>.....</td>
			<td></td>
<%--			<td><a href="pages/manager/book_edit.jsp?method=add">添加图书</a></td>--%>
			<td><a href="pages/manager/book_edit.jsp?action=page&pageNo=${requestScope.page.pageTotal}">添加图书</a></td>
		</tr>
	</table>

	<%--		静态包含 分页条--%>
	<%@include file="/pages/common/page_nav.jsp"%>
	<%--<div id="page_nav">
		<c:if test="${requestScope.page.pageNo > 1}">
		<a href="${requestScope.page.url}&pageNo=1">首页</a>
		<a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>

		<a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">${requestScope.page.pageNo-1}</a>
		</c:if>
		【${requestScope.page.pageNo}】
		<c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
		<a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">${requestScope.page.pageNo+1}</a>

			<a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
		<a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
		</c:if>
		，共${requestScope.page.pageTotal}页,${requestScope.page.pageTotalCount}条记录
		到第<input value="${param.pagNo}" name="pn" id="pn_input"/>页
		<input id="searchPageBtn" type="button" value="确定">
		<script type="text/javascript">
			$(function () {
				$("#searchPageBtn").click(function () {
					//跳到指定的 页码 jsp
					var pageNo = $("#pn_input").val();
					&lt;%&ndash;alert("${pageScope.basePath}${requestScope.page.url}&pageNO=" + pageNo);&ndash;%&gt;

					&lt;%&ndash;var pageTotal = ${requestScope.page.pageTotal};&ndash;%&gt;

					//js 提供了 一个location地址栏 对象
					//它有一个属性叫herf，可以获取浏览器地址栏中的地址
					//herf属性 可读 可写
					location.href = "${pageScope.basePath}${requestScope.page.url}&pageNo="+pageNo;
				});
			});
		</script>
	</div>--%>

</div>


<%--静态包含页脚内容--%>
<%@include file="/pages/common/foot.jsp"%>


</body>
</body>
</html>