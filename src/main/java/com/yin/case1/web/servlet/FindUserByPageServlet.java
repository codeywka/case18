package com.yin.case1.web.servlet;

import com.yin.case1.domain.PageBean;
import com.yin.case1.domain.User;
import com.yin.case1.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;

@WebServlet("/FindUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //
        request.setCharacterEncoding("utf-8");
        //1.获取参数
        String currentPage = request.getParameter("currentPage");//当前页码
        String rows = request.getParameter("rows");//每页显示的条数
        //
        if (currentPage == null || "".equals(currentPage)){
            currentPage = "1";
        }
        if (rows == null || "".equals(rows)){
            rows = "4";
        }

        //11.获取条件查询参数
        Map<String, String[]> condition = request.getParameterMap();

        //2.调用service查询
        UserServiceImpl service = new UserServiceImpl();
        //22.
        PageBean<User> pb = service.findUserByPage(currentPage,rows,condition);

        //3.将pb存入request中
        request.setAttribute("pb",pb);
        //33.
        request.setAttribute("condition",condition);

        //4.转发到list.jsp页面
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
