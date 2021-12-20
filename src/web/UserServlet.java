package web;/*
 *@author 刘治成
 *@create 2021-10-25 22:05
 整合注册与登录于一体
 */

import com.google.gson.Gson;
import org.apache.commons.beanutils.BeanUtils;
import pojo.User;
import service.UserService;
import service.impl.UserServiceImpl;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {

    private UserService userService=new UserServiceImpl();

    //使用AJAX验证用户名是否可用
    protected void ajaxExitUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取参数
        String username = req.getParameter("username");
        boolean exitUsername = userService.exitUsername(username);
        //把返回结果封装成map
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("exitUsername", exitUsername);
        Gson gson=new Gson();
        String json = gson.toJson(resultMap);
        //回传是否可用
        resp.getWriter().write(json);
    }

    //登录操作
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取参数
        String username;
        String password;
        username=req.getParameter("username");
        password=req.getParameter("password");
        //实现登录业务（login方法中已实现判断）
        User login = userService.login(new User(null, username, password, null));
        if (login!=null){
            //登录成功进入成功页面
//            System.out.println("登录成功");
            //保存用户登录之后的信息到session中
            req.getSession().setAttribute("user", login);
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
    //注册操作
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());

        //获取Session中的验证码
        String token= (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //删除Session中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        if (token!=null&&token.equalsIgnoreCase(code)){
            //用户名是否可用
            if (userService.exitUsername(username)){
                //把回显信息保存到request域中
                req.setAttribute("msg", "用户名已存在！！！");
                req.setAttribute("username", username);
                //req.setAttribute("email", email);
                //用户名不可用，跳回注册页面
                System.out.println("用户名不可用");;
                req.getRequestDispatcher("pages/user/regist.jsp").forward(req,resp);
                //resp.sendRedirect(req.getContextPath()+"pages/user/regist.jsp");
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
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //System.out.println(request.getSession().getAttribute("user"));
        req.getSession().invalidate();
//        request.getRequestDispatcher("pages/client/index.jsp").forward(request,response);
        resp.sendRedirect(req.getContextPath());
    }

    //判断并选择操作
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String action=req.getParameter("action");
//        if ("login".equals(action)){
//            login(req,resp);
//        }else if ("regist".equals(action)){
//            regist(req,resp);
//        }
//    }
}
