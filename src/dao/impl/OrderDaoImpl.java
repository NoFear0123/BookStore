package dao.impl;/*
 *@author 刘治成
 *@create 2021-11-04 20:58
 */

import dao.OrderDao;
import pojo.Order;
import web.BaseServlet;

import java.util.List;

public class OrderDaoImpl extends BaseDao implements OrderDao{
    @Override
    public int saveOrder(Order order) {
        String sql="insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`)values(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }

    @Override
    public List<Order> queryOrders() {
        String sql="select `order_id` orderId,`create_time` createTime,`price`,`status`,`user_id` userId from t_order";
        return queryForList(Order.class, sql);
    }

    @Override
    public void changeOrderStatus(Integer status, Integer orderId) {
        String sql="update t_order set `status`=? where order_id=?";
        update(sql, status,String.valueOf(orderId));
    }

    @Override
    public List<Order> queryOrderByUserId(Integer userId) {
        String sql="select `order_id` orderId,`create_time` createTime,`price`,`status`,`user_id` userId from t_order where user_id = ?";
        return queryForList(Order.class, sql, userId);
    }
}
