package com.jump.serlvet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jump.pojo.User;
import com.jump.service.UserService;
import com.jump.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @Author Saraph1nes
 * @Date 2020/5/27 15:07
 * @Version 1.0
 */
public class queryScoreServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        List<User> users = userService.queryAllUserScore();
        if(users!=null){
            System.out.println("获取成功");
        }else {
            System.out.println("获取失败");
        }
        JSONArray jsonArray = JSONArray.parseArray(JSONObject.toJSONString(users));
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.print(jsonArray.toString());
        out.flush();
        out.close();
    }
}
