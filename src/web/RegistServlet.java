package web;/*
@create 2021-10-14 21:48
*/

import pojo.User;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistServlet extends HttpServlet {
    private UserService userService=new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        if ("abcd".equalsIgnoreCase(code)){
            //用户名是否可用
            if (userService.exitUsername(username)){
                //把回显信息保存到request域中
                req.setAttribute("msg", "用户名已存在！！！");
                req.setAttribute("username", username);
                //req.setAttribute("email", email);
                //用户名不可用，跳回注册页面
                System.out.println("用户名不可用");;
                req.getRequestDispatcher("pages/user/regist.jsp").forward(req,resp);
            }
            else {
                //用户名可用
                userService.registUser(new User(null,username,password,email));
                req.getRequestDispatcher("pages/user/regist_success.jsp").forward(req,resp);
            }
        }else {
            //把回显信息保存到request域中
            req.setAttribute("msg", "验证码错误！！");
            req.setAttribute("username", username);
            req.setAttribute("email", email);
            //验证码错误，跳回注册页面
            System.out.println("验证码错误"+code);
            req.getRequestDispatcher("pages/user/regist.jsp").forward(req,resp);
        }

    }
}
