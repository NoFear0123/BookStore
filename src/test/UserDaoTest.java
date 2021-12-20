package test;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import org.junit.Test;
import pojo.User;

import static org.junit.Assert.*;

/*
@create 2021-10-14 17:19
*/public class UserDaoTest {

    @Test
    public void queryUserByUsername() {
        UserDao userDao=new UserDaoImpl();
        System.out.println(userDao.queryUserByUsername("root"));
    }

    @Test
    public void saveUser() {
        UserDao userDao=new UserDaoImpl();
        User user=new User(null,"test123","test123","testtest@123.com");
        int i = userDao.saveUser(user);
        System.out.println(i);
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        UserDao userDao =new UserDaoImpl();
        System.out.println(userDao.queryUserByUsernameAndPassword("root2","root2"));
    }
}