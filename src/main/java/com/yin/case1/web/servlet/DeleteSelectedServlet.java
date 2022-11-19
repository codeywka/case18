package com.yin.case1.web.servlet;

import com.yin.case1.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/DeleteSelectedServlet")
public class DeleteSelectedServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取所有id
        String[] ids = request.getParameterValues("uid");
        //2.调用service删除
        UserServiceImpl service = new UserServiceImpl();
        service.delSelectedUser(ids);
        //3.跳转到查询页面
        response.sendRedirect(request.getContextPath() + "/FindUserByPageServlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
