package test;

import dao.OrderDao;
import dao.impl.OrderDaoImpl;
import org.junit.Test;
import pojo.Cart;
import pojo.CartItem;
import pojo.Order;
import pojo.OrderItem;
import service.OrderService;
import service.impl.OrderServiceImpl;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/*
 *@author 刘治成
 *@create 2021-11-05 15:46
 */public class OrderServiceTest {
    Cart cart=new Cart();
    OrderService orderService=new OrderServiceImpl();
    @Test
    public void createOrder() {
        cart.addItem(new CartItem(1,"test1",1,new BigDecimal(50),new BigDecimal(50 )));
        cart.addItem(new CartItem(1,"test1",1,new BigDecimal(50),new BigDecimal(50 )));
        cart.addItem(new CartItem(2,"test2",1,new BigDecimal(50),new BigDecimal(50 )));
        String orderId = orderService.createOrder(cart, 1);
        System.out.println(orderId);
    }

    @Test
    public void showAllOrders() {
        List<Order> orders = (List<Order>) orderService.showAllOrders();
        System.out.println(orders);
    }

    @Test
    public void sendOrder() {
        orderService.sendOrder(123061);
    }

    @Test
    public void showOrderDetail() {
        List<OrderItem> orderItems = orderService.showOrderDetail(123061);
        System.out.println(orderItems);
    }

    @Test
    public void showMyOrder() {
        List<Order> orders = orderService.showMyOrder(1);
        System.out.println(orders);
    }

    @Test
    public void receiverOrder() {
        orderService.receiverOrder(123061);
    }
}