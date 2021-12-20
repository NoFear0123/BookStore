package pojo;/*
 *@author 刘治成
 *@create 2021-11-04 15:57
 */

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Cart {
//private Integer totalCount;
//private BigDecimal totalPrice;
private Map<Integer,CartItem> items=new LinkedHashMap<Integer, CartItem>();
//添加商品项
public void addItem(CartItem cartItem){
//先查看购物车中是否有，有则数量累加，无则加入购物车
    CartItem item = items.get(cartItem.getId());
    if (item==null){
        //之前没有添加
        items.put(cartItem.getId(), cartItem);
    }else {
        //已经存在
        item.setCount(item.getCount()+1);//数量累加
        item.setTotalPrice(item.getTotalPrice().multiply(new BigDecimal(item.getCount())));
    }
}
    //删除商品项
    public void deleteItem(Integer id){
        items.remove(id);
    }
    //清空购物车
    public void clear(){
        items.clear();
    }
    //修改商品数量
    public void updateCount(Integer id,Integer count){
        CartItem cartItem = items.get(id);
        if (cartItem!=null){
            cartItem.setCount(count);
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }
    }
    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount()+
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }

    public Cart() {
    }



    public Integer getTotalCount() {
    Integer totalCount=0;
    for (Map.Entry<Integer, CartItem>entry:items.entrySet()){
        totalCount+=entry.getValue().getCount();
        }
        return totalCount;
    }



    public BigDecimal getTotalPrice() {
    BigDecimal totalPrice=new BigDecimal(0);

        for (Map.Entry<Integer, CartItem>entry:items.entrySet()){
           totalPrice=totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }


    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }
}
