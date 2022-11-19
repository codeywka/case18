package com.yin.case1.web.servlet;

import com.yin.case1.domain.User;
import com.yin.case1.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");

        //2.获取数据
        //2.1获取用户填写的验证码
        String user_vcode = request.getParameter("verifycode");
        //2.2用户信息
        Map<String, String[]> map = request.getParameterMap();

        //3.验证码校验
        HttpSession session = request.getSession();
        String vcode = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//确保验证码一次性

        try {
            if (!vcode.equalsIgnoreCase(user_vcode)){
                //验证码不正确
                //提示信息
                request.setAttribute("vcode_error","验证码错误！");
                //返回登录页面
                request.getRequestDispatcher("/login.jsp").forward(request,response);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("生成验证码为空");
        }

        //4.封装User对象
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //5.调用Service查询
        UserServiceImpl service = new UserServiceImpl();
        User user1 = service.loginUser(user);

        //6.判断是否登录成功
        if(user1 == null){
            //存储提示信息到request
            request.setAttribute("lg_error","用户名或密码错误");
            //转发到登录页面
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        } else {
            //登录成功
            //存储用户信息
            session.setAttribute("user",user1);//（简化）需要存储的是user对象
            //重定向到success.jsp
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
