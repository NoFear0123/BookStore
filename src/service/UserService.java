package service;/*
@create 2021-10-14 17:43
*/

import pojo.User;

public interface UserService {
    //注册用户
    public void registUser(User user);
    //用户登录
    public User login(User user);
    //检查用户名是否可用
    public boolean exitUsername(String username);
}
