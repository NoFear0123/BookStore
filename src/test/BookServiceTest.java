package test;

import org.junit.Test;
import pojo.Book;
import pojo.Page;
import service.BookService;
import service.impl.BookServiceImpl;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/*
 *@author 刘治成
 *@create 2021-10-28 18:21
 */public class BookServiceTest {

     BookService bookService=new BookServiceImpl();
    @Test
    public void addBook() {
        bookService.addBook(new Book(null,"悟空传","今何在",new BigDecimal(99),110,0,null));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(11);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(10,"遮天","辰东",new BigDecimal(45),100,1560,null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(9));
    }

    @Test
    public void queryBooks() {
        for (Book book:bookService.queryBooks()){
            System.out.println(book);
        }
    }

    @Test
    public void page(){
        System.out.println(bookService.page(1, Page.PAGE_SIZE));
    }

    @Test
    public void pageByPrice(){
        System.out.println(bookService.pageByPrice(1, Page.PAGE_SIZE, 10, 80));
    }
}