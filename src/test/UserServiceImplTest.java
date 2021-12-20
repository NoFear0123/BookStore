package test;

import org.junit.Test;
import pojo.User;
import service.UserService;
import service.impl.UserServiceImpl;

import java.util.Scanner;

import static org.junit.Assert.*;

/*
@create 2021-10-14 21:19
*/public class UserServiceImplTest {

    @Test
    public void registUser() {
        UserService userService=new UserServiceImpl();
        userService.registUser(new User(null,"刘德华","liudehua123","ldh@123.com"));
        System.out.println("注册完成");
    }

    @Test
    public void login() {
        UserService userService=new UserServiceImpl();
        User login = userService.login(new User(null, "root", "root", null));

        if (login!=null){
            System.out.println("登录成功："+login);
        }else {
            System.out.println("账号或密码错误");
        }
    }

    @Test
    public void exitUsername() {
        UserService userService=new UserServiceImpl();
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入用户名：");
        String name=scanner.next();
        boolean b = userService.exitUsername(name);
        if (b==false){
            System.out.println("用户可用");
        }else {
            System.out.println("用户已存在");
        }

    }
}