package com.jump.dao;

import com.jump.pojo.User;

import java.util.List;

/**
 * @Author Saraph1nes
 * @Date 2020/5/21 14:30
 * @Version 1.0
 */
public interface UserDao {

    /**
     * 登录
     * @param uname
     * @return
     */
    User login(String uname);

    /**
     * 注册
     * @param uname
     * @param pwd
     * @return
     */
    boolean register(String uname,String pwd);

    /**
     * 判断是否存在用户
     * @param uname
     * @return
     */
    boolean existUser(String uname);

    /**
     * 查询高分榜分数
     * @return
     */
    List<User> queryAllUserScore();

    /**
     * 添加分数
     * @param uname
     * @param score
     * @return
     */
    boolean addScore(String uname,String score);


}
