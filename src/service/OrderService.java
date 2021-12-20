package service;/*
 *@author 刘治成
 *@create 2021-11-04 20:14
 */

import pojo.Cart;
import pojo.Order;
import pojo.OrderItem;

import java.util.List;

public interface OrderService {
    public String  createOrder(Cart cart,Integer userId);
    public List<Order> showAllOrders();
    public void sendOrder(Integer orderId);
    public List<OrderItem> showOrderDetail(Integer orderId);
    public List<Order> showMyOrder(Integer userId);
    public void receiverOrder(Integer orderId);
}
