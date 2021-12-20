package test;

import dao.OrderItemDao;
import dao.impl.OrderItemDaoImpl;
import org.junit.Test;
import pojo.Order;
import pojo.OrderItem;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/*
 *@author 刘治成
 *@create 2021-11-04 21:42
 */public class OrderItemDaoImplTest {
OrderItemDao orderItemDao=new OrderItemDaoImpl();
    @Test
    public void saveOrder() {
        orderItemDao.saveOrder(new OrderItem(null,"时间简史",1,new BigDecimal(100),new BigDecimal(100),"11112"));
    }

    @Test
    public void queryOrderItemByOrderId() {
        List<OrderItem> orderItems = orderItemDao.queryOrderItemByOrderId(11112);
        for (OrderItem orderItem:orderItems){
            System.out.println(orderItem);

        }
    }
}