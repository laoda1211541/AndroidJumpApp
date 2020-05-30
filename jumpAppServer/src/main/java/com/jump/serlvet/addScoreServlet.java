package com.jump.serlvet;

import com.jump.service.UserService;
import com.jump.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Saraph1nes
 * @Date 2020/5/27 15:35
 * @Version 1.0
 */
public class addScoreServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String uname = req.getParameter("uname");
        String score = req.getParameter("score");
        boolean addScore = userService.addScore(uname, score);
        if (addScore){
            System.out.println("新增分数成功");
        }else {
            System.out.println("新增分数失败");
        }
        resp.getWriter().write(String.valueOf(addScore));
        System.out.println("String.valueOf(addScore)===="+addScore);
    }
}
