package service;/*
 *@author 刘治成
 *@create 2021-10-28 18:10
 */

import pojo.Book;
import pojo.Page;

import java.awt.*;
import java.util.List;

public interface BookService {
    //添加图书
    public void addBook(Book book);
    //删除
    public void deleteBookById(Integer id);
    //修改
    public void updateBook(Book book);
    //查询
    public Book queryBookById(Integer id);
    //查询所有
    public List<Book> queryBooks();

    Page<Book> page(int pageNo, int pageSize);

    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
