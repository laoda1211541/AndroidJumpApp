package com.jump.dao.impl;

import com.jump.dao.UserDao;
import com.jump.pojo.User;
import com.jump.util.DBUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Saraph1nes
 * @Date 2020/5/21 14:31
 * @Version 1.0
 */
public class UserDaoImpl implements UserDao {

    DBUtil dbUtil = new DBUtil();

    @Override
    public User login(String uname) {
        User user = new User();
        try {
            dbUtil.getConnection();
            String sql = "select * from user where uname = ?";
            Object[] param = {uname};
            ResultSet resultSet = dbUtil.executeQuery(sql, param);
            while (resultSet.next()){
                user.setUid(resultSet.getInt("uid"));
                user.setUname(resultSet.getString("uname"));
                user.setPwd(resultSet.getString("pwd"));
                user.setScore(resultSet.getString("score"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbUtil.closeAll();
        }
        return user;
    }

    @Override
    public boolean register(String uname, String pwd) {
        boolean flag = false;
        try {
            dbUtil.getConnection();
            String sql = "insert into user(uname,pwd,score) value(?,?,0)";
            Object[] param = {uname,pwd};
            dbUtil.executeUpdate(sql, param);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbUtil.closeAll();
        }
        return flag;
    }

    @Override
    public boolean existUser(String uname) {
        boolean flag = false;
        try {
            dbUtil.getConnection();
            String sql = "select * from user where uname = ?";
            Object[] param = {uname};
            ResultSet resultSet = dbUtil.executeQuery(sql, param);
            if (resultSet.next()){
                flag = true;
            }else {
                flag = false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbUtil.closeAll();
        }
        return flag;
    }

    @Override
    public List<User> queryAllUserScore() {
        User user = null;
        List<User> userList = new ArrayList<>();
        try {
            dbUtil.getConnection();
            String sql = "select * from user";
            ResultSet rs = dbUtil.executeQuery(sql, null);
            while (rs.next()){
                user = new User();
                user.setUid(rs.getInt("uid"));
                user.setUname(rs.getString("uname"));
                user.setPwd(rs.getString("pwd"));
                user.setScore(rs.getString("score"));
                userList.add(user);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbUtil.closeAll();
        }
        return userList;
    }

    @Override
    public boolean addScore(String uname, String score) {
        boolean flag = false;
        try {
            dbUtil.getConnection();
            String sql = "update user set score = ? where uname = ?";
            Object[] param = {score,uname};
            dbUtil.executeUpdate(sql, param);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbUtil.closeAll();
        }
        return flag;
    }

}
