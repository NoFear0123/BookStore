package service.impl;/*
 *@author 刘治成
 *@create 2021-11-04 20:21
 */

import dao.BookDao;
import dao.OrderDao;
import dao.OrderItemDao;
import dao.impl.BookDaoImpl;
import dao.impl.OrderDaoImpl;
import dao.impl.OrderItemDaoImpl;
import pojo.*;
import service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao=new OrderDaoImpl();
    private OrderItemDao orderItemDao =new OrderItemDaoImpl();
    private BookDao bookDao=new BookDaoImpl();
    @Override
    public String createOrder(Cart cart, Integer userId) {
//        System.out.println("OrderServiceImpl线程名："+Thread.currentThread().getName());
        Random random=new Random();
        int num=random.nextInt(100);
        int num2=random.nextInt(100);
        //时间戳唯一
        //String orderId=System.currentTimeMillis()+""+userId;
        String orderId="1"+num2+num+userId;
        //创建一个订单对象
        Order order=new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);
        //保存订单
        orderDao.saveOrder(order);
        //遍历购物车中每一个商品项为订单项
        for (Map.Entry<Integer, CartItem>entry:cart.getItems().entrySet()){
            CartItem cartItem=entry.getValue();

            OrderItem orderItem=new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId);
            //保存订单项到数据库
            orderItemDao.saveOrder(orderItem);
            //修改数据库图书库存与销量数据
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales()+cartItem.getCount());
            book.setStock(book.getStock()-cartItem.getCount());
            bookDao.updateBook(book);
        }
        //清空购物车
        cart.clear();
        //返回订单号
        return orderId;
    }

    @Override
    public List<Order> showAllOrders() {
        List<Order> orders = orderDao.queryOrders();
        return orders;
    }

    @Override
    public void sendOrder(Integer orderId) {
        orderDao.changeOrderStatus(1, orderId);
    }

    @Override
    public List<OrderItem> showOrderDetail(Integer orderId) {
        List<OrderItem> orderItems = orderItemDao.queryOrderItemByOrderId(orderId);
        return orderItems;
    }

    @Override
    public List<Order> showMyOrder(Integer userId) {
        List<Order> orders = orderDao.queryOrderByUserId(userId);
        return orders;
    }

    @Override
    public void receiverOrder(Integer orderId) {
        orderDao.changeOrderStatus(-1, orderId);
    }
}
