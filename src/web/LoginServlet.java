package web;/*
@create 2021-10-15 14:47
*/

import pojo.User;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private UserService userService=new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取参数
        String username;
        String password;
        username=req.getParameter("username");
        password=req.getParameter("password");
        //实现登录业务（login方法中已实现判断）
        User login = userService.login(new User(null, username, password, null));
        if (login!=null){
            //登录成功进入成功页面
            System.out.println("登录成功");
            req.getRequestDispatcher("pages/user/login_success.jsp").forward(req,resp);
        }else {
            //把错误信息，和回显的表单项信息保存到request域中
            req.setAttribute("msg", "用户名或密码错误！！！");
            req.setAttribute("username", username);

            //失败返回登录页面
            System.out.println("登陆失败");
            req.getRequestDispatcher("pages/user/login.jsp").forward(req,resp);
        }



    }
}
