package dao;/*
 *@author 刘治成
 *@create 2021-10-28 17:33
 */

import pojo.Book;

import java.util.List;

public interface BookDao {
    public int addBook(Book book);
    public int deleteBookById(Integer id);
    public int updateBook(Book book);
    public Book queryBookById(Integer id);
    public List<Book> queryBooks();

    Integer queryForPageTotalCount();

    List<Book> queryForPageItems(Integer begin, int pageSize);

    Integer qureyForPageTotalCountByPrice(int min, int max);

    List<Book> qureyForPageItemsByprice(int begin, int pageSize, int max, int min);
}
