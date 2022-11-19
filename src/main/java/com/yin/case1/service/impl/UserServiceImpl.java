package com.yin.case1.service.impl;

import com.yin.case1.dao.Impl.UserDaoImpl;
import com.yin.case1.dao.UserDao;
import com.yin.case1.domain.PageBean;
import com.yin.case1.domain.User;
import com.yin.case1.service.UserService;

import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: project03
 * @BelongsPackage: com.yin.p1.service.impl
 * @Author: yinwenkang
 * @CreateTime: 2022-09-17  17:05
 * @Description: TODO
 * @Version: 1.0
 */
public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();
    @Override
    public List<User> findAll() {
        //调用dao完成查询
        return dao.findAll();
    }

    @Override
    public User loginUser(User user) {
        return dao.findUserByUsernamePassword(user.getUsername(),user.getPassword());
    }

    @Override
    public void addUser(User user) {
        dao.add(user);
    }

    @Override
    public void deleteUser(String id) {
        dao.delete(Integer.parseInt(id));
    }

    @Override
    public User findUserById(String id) {
        return dao.findById(Integer.parseInt(id));
    }

    @Override
    public void updateUser(User user) {
        dao.update(user);
    }

    @Override
    public void delSelectedUser(String[] ids) {
        if (ids != null && ids.length > 0) {
            //1.表里数组
            for (String id : ids) {
                //调用删除
                dao.deleteSelected(Integer.parseInt(id));
            }
        }
    }

    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
//        //箭头bug
//        if(currentPage <= 0){
//            currentPage = 1;
//        }

        //1.创建空的PageBean对象
        PageBean<User> upb = new PageBean<User>();

        //2.设置参数
        upb.setCurrentPage(currentPage);
        upb.setRows(rows);

        //3.调用dao查询总记录数
        int totalCount = dao.findTotalCount(condition);
        upb.setTotalCount(totalCount);

        //4.调用dao查询list集合
        //4.1计算开始的记录索引
        int start = (currentPage - 1) * rows;
        //4.2调用dao查询list集合并封装进pagebean对象中
        List<User> list = dao.findByPage(start,rows,condition);
        upb.setList(list);

        //5.计算总页码
        int totalPage = (totalCount % rows) == 0 ? (totalCount/rows) : (totalCount/rows) + 1;
        upb.setTotalPage(totalPage);

        return upb;
    }

}
