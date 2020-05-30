package com.jump.serlvet;

import com.alibaba.fastjson.JSON;
import com.jump.pojo.User;
import com.jump.service.UserService;
import com.jump.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Saraph1nes
 * @Date 2020/5/21 14:47
 * @Version 1.0
 */
public class registerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String uname = req.getParameter("uname");
        String pwd = req.getParameter("pwd");
        UserService userService = new UserServiceImpl();
        boolean register = userService.register(uname, pwd);
        if (register){
            System.out.println("注册成功");
        }else {
            System.out.println("注册失败");
        }
        resp.getWriter().write(String.valueOf(register));
        System.out.println("String.valueOf(register)===="+register);
//        req.setAttribute("flag", register);
    }
}
