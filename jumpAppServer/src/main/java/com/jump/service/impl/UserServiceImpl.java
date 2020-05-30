package com.jump.service.impl;

import com.jump.dao.UserDao;
import com.jump.dao.impl.UserDaoImpl;
import com.jump.pojo.User;
import com.jump.service.UserService;

import java.util.Comparator;
import java.util.List;

/**
 * @Author Saraph1nes
 * @Date 2020/5/21 14:39
 * @Version 1.0
 */
public class UserServiceImpl implements UserService {

    UserDao userDao = new UserDaoImpl();

    @Override
    public User login(String uname, String pwd) {
        User loginUser = userDao.login(uname);
        if (loginUser!=null){
            if(pwd.equals(loginUser.getPwd())){
                return loginUser;
            }else {
                return null;
            }
        }else {
            return null;
        }
    }

    @Override
    public boolean register(String uname, String pwd) {
        if (userDao.existUser(uname)){
            return false;
        }else {
            return userDao.register(uname, pwd);
        }
    }

    @Override
    public List<User> queryAllUserScore() {
        List<User> users = userDao.queryAllUserScore();
        users.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                Integer i1 = Integer.valueOf(o1.getScore());
                Integer i2 = Integer.valueOf(o2.getScore());
                return i2.compareTo(i1);
            }
        });
        return users;
    }

    @Override
    public boolean addScore(String uname, String score) {
        boolean flag = false;
        boolean existUser = userDao.existUser(uname);
        if (existUser){
            userDao.addScore(uname, score);
            flag = true;
        }else {
            flag = false;
        }
        return flag;
    }
}
