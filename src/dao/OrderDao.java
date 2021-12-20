package dao;/*
 *@author 刘治成
 *@create 2021-11-04 20:53
 */

import pojo.Order;

import java.util.List;

public interface OrderDao {
    public int saveOrder(Order order);
    public List<Order> queryOrders();
    public void changeOrderStatus(Integer status,Integer orderId);
    public List<Order> queryOrderByUserId(Integer userId);
}
