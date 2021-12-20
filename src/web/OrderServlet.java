package web;/*
 *@author 刘治成
 *@create 2021-11-04 20:09
 */

import pojo.Cart;
import pojo.Order;
import pojo.OrderItem;
import pojo.User;
import service.OrderService;
import service.impl.OrderServiceImpl;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends BaseServlet {
    private OrderService orderService=new OrderServiceImpl();
    //生成订单
    protected void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //先获取Cart购物车对象与UserId
        Cart cart= (Cart) request.getSession().getAttribute("cart");
        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
            return;
        }

//        System.out.println("OrderServlet程序在线程"+ Thread.currentThread().getName());

        Integer userId = user.getId();
        //调用orderService。createOrder(cart,userId)
        String orderId = orderService.createOrder(cart, userId);
        //保存到request域中
        request.getSession().setAttribute("orderId", orderId);
        //请求转发到checkout.jsp
//        request.getRequestDispatcher("/pages/cart/checkout.jsp").forward(request, response);
        response.sendRedirect(request.getContextPath()+"/pages/cart/checkout.jsp");
    }
    //查看所有订单
    protected void showAllOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用orderService。showAllOrders()
        List<Order> orders = orderService.showAllOrders();
        //3.把全部订单保存在session域
        request.getSession().setAttribute("orders", orders);
        //4.请求转发到/pages/manager/order_manager.jsp页面
        response.sendRedirect(request.getContextPath()+"/pages/manager/order_manager.jsp");
//        request.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(request,response);


    }
    //发货
    protected void sendOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取orderId
        Integer orderId= WebUtils.parseInt(request.getParameter("orderId"), 0);
        // 调用orderService.sendOrder();
        orderService.sendOrder(orderId);
        //4.重定向到/pages/manager/order_manager.jsp页面
//        request.getRequestDispatcher("/orderServlet?action=showAllOrder").forward(request, response);
        response.sendRedirect(request.getContextPath()+"/orderServlet?action=showAllOrder");
    }
    //查看订单详情
    protected void showOrderDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取orderId
        Integer orderId= WebUtils.parseInt(request.getParameter("orderId"), 0);
        // 调用orderService.showOrderDetail(Integer orderId);
        List<OrderItem> orderItems = orderService.showOrderDetail(orderId);
        //3.把全部订单详情保存在session域
        request.getSession().setAttribute("orderItems", orderItems);
        //请求转发到详情展示页
        response.sendRedirect(request.getContextPath()+"/pages/order/orderDetail.jsp");
//        request.getRequestDispatcher("/pages/order/orderDetail.jsp").forward(request, response);

    }
    //查看我的订单
    protected void showMyOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取userId
        Integer userId= WebUtils.parseInt(request.getParameter("userId"), 0);
        // 调用orderService.showMyOrder(Integer userId);
        List<Order> myOrder = orderService.showMyOrder(userId);
        //3.把全部订单详情保存在session域
        request.getSession().setAttribute("myOrder", myOrder);
        //请求转发到详情展示页
        response.sendRedirect(request.getContextPath()+"/pages/order/order.jsp");
    }
    //签收订单
    protected void receiverOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取orderId
        Integer orderId= WebUtils.parseInt(request.getParameter("orderId"), 0);
        // 调用orderService.receiverOrder(Integer orderId);
        orderService.receiverOrder(orderId);
        //请求转发到本页
        response.sendRedirect(request.getContextPath()+"/pages/order/order.jsp");
    }
}
