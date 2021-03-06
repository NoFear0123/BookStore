package web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 *使用反射，获取jsp页面action值对应的同名方法，并调用该方法
 * 如此，只需写具体方法，减少代码重复
 */
public class BaseServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        // 解决post请求中文乱码问题
        // 一定要在获取请求参数之前调用才有效
        request.setCharacterEncoding("UTF-8");
        // 解决响应中文乱码
        response.setContentType("text/html; charset=UTF-8");

        String action = request.getParameter("action");
        try {
            // 获取action业务鉴别字符串，获取相应的业务 方法反射对象
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            //System.out.println(method);
            // 调用目标业务 方法
            method.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);//抛给Filter过滤器
        }
    }


}
