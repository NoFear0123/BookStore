package dao.impl;/*
 *@author 刘治成
 *@create 2021-11-04 20:58
 */

import dao.OrderItemDao;
import pojo.Order;
import pojo.OrderItem;

import java.util.List;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrder(OrderItem orderItem) {
        String sql="insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`)values(?,?,?,?,?)";
        return update(sql, orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> queryOrderItemByOrderId(Integer orderId) {
        String sql="select `name`,`count`,`price`,`total_price` totalPrice,`order_id` OrderId from t_order_item where order_id = ?";
        return queryForList(OrderItem.class, sql, orderId);
    }
}
