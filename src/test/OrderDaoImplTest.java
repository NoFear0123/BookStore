package test;

import dao.OrderDao;
import dao.impl.OrderDaoImpl;
import org.junit.Test;
import pojo.Order;

import javax.xml.crypto.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/*
 *@author 刘治成
 *@create 2021-11-04 21:38
 */public class OrderDaoImplTest {
    OrderDao orderDao=new OrderDaoImpl();
    @Test
    public void saveOrder() {
        orderDao.saveOrder(new Order("11112",new Date(),new BigDecimal(100 ),0,1));
    }

    @Test
    public void queryOrders() {
        List<Order> orders = orderDao.queryOrders();
        for (Order order:orders){
            System.out.println(order);
        }
    }

    @Test
    public void changeOrderStatus() {
        orderDao.changeOrderStatus(1, 11111);
    }


}