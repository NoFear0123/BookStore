package web;

import pojo.Book;
import pojo.Page;
import service.BookService;
import service.impl.BookServiceImpl;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**

 */
public class ClientBookServlet extends BaseServlet {


    private BookService bookService = new BookServiceImpl();
    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("使用了前台的ClientBookServlet");
        //1 获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        //2 调用BookService.page(pageNo，pageSize)：Page对象
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("client/bookServlet?action=page");
        //3 保存Page对象到Request域中
        request.setAttribute("page", page);
        //4 请求转发到页面
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request, response);
    }

    protected void pageByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1 获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);

        int min = WebUtils.parseInt(request.getParameter("min"), 0);
        int max = WebUtils.parseInt(request.getParameter("max"), Integer.MAX_VALUE);

        //2 调用BookService.page(pageNo，pageSize)：Page对象
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize, min, max);
//        page.setUrl("client/bookServlet?action=pageByPrice");
        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");

        if (request.getParameter("min") != null) {
            sb.append("&min=").append(request.getParameter("min"));
        }

        if (request.getParameter("max") != null) {
            sb.append("&max=").append(request.getParameter("max"));
        }

        page.setUrl(sb.toString());

        //3 保存Page对象到Request域中
        request.setAttribute("page", page);
        //4 请求转发到pages/manager/book_manager.jsp页面
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request, response);
    }
}
