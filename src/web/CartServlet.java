package web;

import com.google.gson.Gson;
import pojo.Book;
import pojo.Cart;
import pojo.CartItem;
import service.BookService;
import service.impl.BookServiceImpl;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
 *@author 刘治成
 *@create 2021-11-04 16:30
 */
public class CartServlet extends BaseServlet {
    private BookService bookService=new BookServiceImpl();
    //使用AJAX加入购物车
    protected void ajaxAddItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int id =WebUtils.parseInt(request.getParameter("id"), 0);
        //调用bookService。queryBookById（id）得到图书的信息
        Book book = bookService.queryBookById(id);
        //吧图书信息转化成为cartItem商品项
        CartItem cartItem=new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        //调用Cart.ddItem（CartItem）添加商品项
        Cart cart= (Cart) request.getSession().getAttribute("cart");
        if (cart==null){
            cart=new Cart();
            request.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        //存入到了Session中
        //System.out.println(cart);
        request.getSession().setAttribute("lastName", cartItem.getName());
        //
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("totalCount", cart.getTotalCount());
        resultMap.put("lastName", cartItem.getName());
        Gson gson=new Gson();
        String resultMapJsonString=gson.toJson(resultMap);
        response.getWriter().write(resultMapJsonString);

    }

    //加入购物车
    protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("加入购物车");
        //System.out.println("编号"+request.getParameter("id"));
        //获取请求的参数，商品编号
        int id =WebUtils.parseInt(request.getParameter("id"), 0);
        //调用bookService。queryBookById（id）得到图书的信息
        Book book = bookService.queryBookById(id);
        //吧图书信息转化成为cartItem商品项
        CartItem cartItem=new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        //调用Cart.ddItem（CartItem）添加商品项
        Cart cart= (Cart) request.getSession().getAttribute("cart");
        if (cart==null){
            cart=new Cart();
            request.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        //存入到了Session中
        //System.out.println(cart);

        //重定向回商品列表页面,通过请求头的Referer获取当前浏览器地址
        response.sendRedirect(request.getHeader("Referer"));
        request.getSession().setAttribute("lastName", cartItem.getName());
    }
    //删除购物车中项
    protected void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取商品编号
        int id =WebUtils.parseInt(request.getParameter("id"), 0);
        //
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart!=null){
            //删除商品
            cart.deleteItem(id);
            //重定向
            response.sendRedirect(request.getHeader("Referer"));
        }
    }
    //清空购物车
    protected void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取购物车对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart!=null){
            cart.clear();
            response.sendRedirect(request.getHeader("Referer"));
        }
    }
    //修改商品数量
    protected void updateCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取商品编号
        int id =WebUtils.parseInt(request.getParameter("id"), 0);
        //获取商品数量
        int count =WebUtils.parseInt(request.getParameter("count"), 0);
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        cart.updateCount(id, count);
        //System.out.println(cart);
        response.sendRedirect(request.getHeader("Referer"));
    }
}
