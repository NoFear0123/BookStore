package service.impl;/*
@create 2021-10-14 17:47
*/

import dao.UserDao;
import dao.impl.UserDaoImpl;
import pojo.User;
import service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao =new UserDaoImpl();
    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean exitUsername(String username) {
        if (userDao.queryUserByUsername(username)==null){
                //可用
            return false;
        }else {
            return true;
        }

    }
}
