package web;/*
 *@author 刘治成
 *@create 2021-10-28 18:26
 */

import pojo.Book;
import pojo.Page;
import service.BookService;
import service.impl.BookServiceImpl;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet {
    BookService bookService=new BookServiceImpl();
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       int pageNo=WebUtils.parseInt(request.getParameter("pageNo"), 0);
       pageNo+=1;
        //1.获取请求的参数并封装成对象
        Book book = WebUtils.copyParamToBean(request.getParameterMap(), new Book());
        //2.调用添加方法存入数据库
        bookService.addBook(book);
        //3.跳转到图书列表页面 /manager/bookServlet?action=list
       // request.getRequestDispatcher("/manager/bookServlet?action=list").forward(request, response);
        //使用重定向  两次请求
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=page&pageNo="+pageNo);
    }
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求参数 id
        int id = WebUtils.parseInt(request.getParameter("id"),0);
        //2.调用删除方法
        bookService.deleteBookById(id);
        //重定向图书列表页面
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=page&pageNo="+request.getParameter("pageNo"));
    }
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求的参数并封装成对象
        Book book = WebUtils.copyParamToBean(request.getParameterMap(), new Book());
        //2.调用修改方法
        bookService.updateBook(book);
        //重定向图书列表页面
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=page&pageNo="+request.getParameter("pageNo"));
    }
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.通过BookService查询全部图书
        List<Book> books = bookService.queryBooks();
        //2.把全部图书保存在request域\
        request.setAttribute("books", books);
        //3.请求转发到/pages/manager/book_manager.jsp页面
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }
    //用来处理分页
    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求参数
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"),4);
        //2.调用方法
        Page<Book> page=bookService.page(pageNo,pageSize);
        page.setUrl("manager/bookServlet?action=page");
        //3.把全部图书保存在request域
        request.setAttribute("page", page);
        //4.请求转发到/pages/manager/book_manager.jsp页面
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);

    }

    protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //1.获取请求参数 id
        int id = WebUtils.parseInt(request.getParameter("id"),0);
        //2.调用查询方法
        Book book = bookService.queryBookById(id);
        //3.把数据保存入request域
        request.setAttribute("book", book);
        //4.请求转发到编辑图书页面
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);
    }
}
