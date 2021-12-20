package test;

import dao.BookDao;
import dao.impl.BookDaoImpl;
import org.junit.Test;
import pojo.Book;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/*
 *@author 刘治成
 *@create 2021-10-28 17:50
 */public class BookDaoTest {
private BookDao bookDao=new BookDaoImpl();
    @Test
    public void addBook() {
        bookDao.addBook(new Book(null,"完美世界","辰东",new BigDecimal(9999),111000,0,null));
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(11);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(10,"遮天","辰东",new BigDecimal(65),200,1560,null));
    }

    @Test
    public void queryBookById() {
        Book book = bookDao.queryBookById(5);
        System.out.println(book);
    }

    @Test
    public void queryBooks() {
        List<Book> books = bookDao.queryBooks();
        for (Book book :books){
            System.out.println(book);
        }
    }

    @Test
    public void queryForPageTotalCount() {
        Integer integer = bookDao.queryForPageTotalCount();
        System.out.println(integer);
    }
    @Test
    public void queryForPageItems() {
        List<Book> books = bookDao.queryForPageItems(5, 4);
        for (Book book :books){
            System.out.println(book);
        }
    }
    @Test
    public void queryForPageItemsByprice() {
        List<Book> books = bookDao.qureyForPageItemsByprice(0, 4,10,80);
        for (Book book :books){
            System.out.println(book);
        }
    }
    @Test
    public void qureyForPageTotalCountByPrice() {
        Integer integer = bookDao.qureyForPageTotalCountByPrice(10, 80);
        System.out.println(integer);
    }
}