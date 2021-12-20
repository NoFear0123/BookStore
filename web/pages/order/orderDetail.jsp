<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/11/5
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单详情</title>
    <%@ include file="/pages/common/head.jsp"%>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            //给加入按钮绑定返回事件
            $("#return_btn").click(function () {
                location.href = "http://localhost:8080/book/pages/order/order.jsp";
            })
        })
    </script>
</head>
<body style="background-color: skyblue">
<div id="header">
    <img class="logo_img" alt="" src="../../static/img/logo2.gif" >
    <span class="wel_word">订单详情</span>
</div>

<div id="main">

    <table>
        <tr>
            <td>图书</td>
            <td>数量</td>
            <td>单价</td>
            <td>总价</td>
            <td>订单号</td>
        </tr>
        <c:forEach items="${sessionScope.orderItems}" var="orderItems">
            <tr>
                <td>${orderItems.name}</td>
                <td>${orderItems.count}</td>
                <td>${orderItems.price}</td>
                <td>${orderItems.totalPrice}</td>
                <td>${orderItems.orderId}</td>
            </tr>
        </c:forEach>
    </table>
<button id="return_btn" style="background-color: #ffe57d; margin-left: 400px;margin-top: 100px; width: 400px;height: 60px">
    返回
</button>
</div>

<%@ include file="/pages/common/foot.jsp"%>
</body>
</html>
