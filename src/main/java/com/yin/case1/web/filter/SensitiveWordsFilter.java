//package com.yin.case1.web.filter;
//
//import javax.servlet.*;
//import javax.servlet.annotation.*;
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.Method;
//import java.lang.reflect.Proxy;
//import java.util.*;
//
///**
// * 敏感词汇过滤器
// */
//@WebFilter("/*")
//public class SensitiveWordsFilter implements Filter {
//    //敏感词汇的list集合
//    private List<String> list = new ArrayList<String>();
//    public void init(FilterConfig config) throws ServletException {
//        try {
//            //1.获取文件的真实路径
//            ServletContext servletContext = config.getServletContext();
//            String realPath = servletContext.getRealPath("/WEB-INF/classes/敏感词汇.txt");
//            //2.读取文件
//            BufferedReader br = new BufferedReader(new FileReader(realPath));
//            //3.将文件的每一行数据
//            String line = null;
//            while ((line = br.readLine()) != null){
//                list.add(line);
//            }
//
//            br.close();
//            System.out.println(list);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void destroy() {
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
//        //1.创建代理对象，增强getParameter方法
//        ServletRequest proxy_rq = (ServletRequest) Proxy.newProxyInstance(request.getClass().getClassLoader(), request.getClass().getInterfaces(), new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                //增强getParameter方法
//                //判断是否是getParameter方法
//                if(method.getName().equals("getParameter")){
//                    //增强返回值
//                    //获取返回值
//                    String value = (String) method.invoke(request, args);
//                    if(value != null){
//                        for (String str : list){
//                            if(value.contains(str)){
//                                value = value.replaceAll(str,"***");
//                            }
//                        }
//                    }
//                    return value;
//                }
//                //判断是否是getParameterMap方法
//                if("getParameterMap".equals(method.getName())) {
//                    //由request得到的原数组不可改变，他是被锁住的，它是org.apache.catalina.util.ParameterMap这个类的
//                    // 并且修改新数组的脏字，返回新的数组
//                    Map<String, String[]> map = (Map<String, String[]>) method.invoke(request, args);
//                    System.out.println(map.getClass());
//                    Set<String> keySet = map.keySet();
//                    for (String key : keySet) {
//                        if ("name".equals(key) || "qq".equalsIgnoreCase(key) || "email".equals(key)) {
//                            String[] value = map.get(key);
//                            if (value != null) {
//                                for (String s : list) {
//                                    value[0] = value[0].replaceAll(s,"***");
//                                }
//                            }
//                        }
//                    }
//                    return map;
//                }
//
//                //判断是否是getParameterValue方法
//
//                return method.invoke(request,args);
//            }
//        });
//        //2.放行
//        chain.doFilter(proxy_rq, response);
//    }
//}
