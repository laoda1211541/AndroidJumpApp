package com.jump.service;

import com.jump.pojo.User;

import java.util.List;

/**
 * @Author Saraph1nes
 * @Date 2020/5/21 14:39
 * @Version 1.0
 */
public interface UserService {
    /**
     * 登录验证
     * @param uname
     * @param pwd
     * @return
     */
    User login(String uname, String pwd);

    /**
     * 用户注册
     * @param uname
     * @param pwd
     * @return
     */
    boolean register(String uname, String pwd);

    /**
     * 查询高分榜分数,并从高到低排列
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
