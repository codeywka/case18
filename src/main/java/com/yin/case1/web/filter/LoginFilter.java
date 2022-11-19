package com.yin.case1.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 完成登录验证的过滤器
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //0.强制转化
        HttpServletRequest req= (HttpServletRequest) request;
        //1.获取资源的请求路径
        String uri = req.getRequestURI();
        //2.判断是否包含登录相关资源路径
        if(uri.contains("/login.jsp") || uri.contains("/LoginServlet") || uri.contains("/CheckCodeServlet") || uri.contains("/css/") || uri.contains("/js/") || uri.contains("/fonts/")){
            //包含，用户想登录，放行
            chain.doFilter(request,response);
        } else {
            //不包含，需要验证用户是否登录
            //3.从获取session中获取user
            Object user = ((HttpServletRequest) request).getSession().getAttribute("user");
            if(user != null){
                //登录了，放行
                chain.doFilter(request,response);
            } else {
                //没有登录，跳转登录页面
                request.setAttribute("login_msg","您尚未登录，请登录！");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }
        }
//        chain.doFilter(request, response);
    }
}
