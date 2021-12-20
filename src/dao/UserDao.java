package dao;/*
@create 2021-10-14 16:57
*/

import pojo.User;

public interface UserDao {
    //根据用户名查询用户对象
    public User queryUserByUsername(String username);
    //保存注册的用户信息
    public int saveUser(User user);
    //登录时，根据用户名和密码查询用户对象
    public User queryUserByUsernameAndPassword(String username,String password);
}
