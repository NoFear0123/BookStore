package dao;/*
 *@author 刘治成
 *@create 2021-11-04 20:53
 */

import pojo.Order;
import pojo.OrderItem;

import java.util.List;

public interface OrderItemDao {
    public int saveOrder(OrderItem orderItem);
    public List<OrderItem> queryOrderItemByOrderId(Integer orderId);
}
