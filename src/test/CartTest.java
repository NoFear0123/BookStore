package test;

import org.junit.Test;
import pojo.Cart;
import pojo.CartItem;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/*
 *@author 刘治成
 *@create 2021-11-04 16:13
 */public class CartTest {
Cart cart=new Cart();
    @Test
    public void addItem() {
        cart.addItem(new CartItem(1,"test1",1,new BigDecimal(50),new BigDecimal(50 )));
        cart.addItem(new CartItem(1,"test1",1,new BigDecimal(50),new BigDecimal(50 )));
        cart.addItem(new CartItem(2,"test2",1,new BigDecimal(50),new BigDecimal(50 )));
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        cart.addItem(new CartItem(1,"test1",1,new BigDecimal(50),new BigDecimal(50 )));
        cart.addItem(new CartItem(1,"test1",1,new BigDecimal(50),new BigDecimal(50 )));
        cart.addItem(new CartItem(2,"test2",1,new BigDecimal(50),new BigDecimal(50 )));
        cart.deleteItem(1);
        System.out.println(cart);
    }

    @Test
    public void clear() {
        cart.addItem(new CartItem(1,"test1",1,new BigDecimal(50),new BigDecimal(50 )));
        cart.addItem(new CartItem(1,"test1",1,new BigDecimal(50),new BigDecimal(50 )));
        cart.addItem(new CartItem(2,"test2",1,new BigDecimal(50),new BigDecimal(50 )));
        cart.clear();
        System.out.println(cart);

    }

    @Test
    public void updateCount() {
        cart.addItem(new CartItem(1,"test1",1,new BigDecimal(50),new BigDecimal(50 )));
        cart.addItem(new CartItem(1,"test1",1,new BigDecimal(50),new BigDecimal(50 )));
        cart.addItem(new CartItem(2,"test2",1,new BigDecimal(50),new BigDecimal(50 )));
        cart.updateCount(2, 10);
        System.out.println(cart);
    }
}