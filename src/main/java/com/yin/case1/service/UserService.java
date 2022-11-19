package com.yin.case1.service;

import com.yin.case1.domain.PageBean;
import com.yin.case1.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户管理的用户接口
 */
public interface UserService {
    /**
     * 查询所有用户信息
     * @return
     */
    public List<User> findAll();

    User loginUser(User user);

    /**
     *添加User对象
     */
    public void addUser(User user);

    /**
     * 删除User对象
     * @param id
     */
    void deleteUser(String id);

    /**
     * 根据id查出对应的对象
     * @param id
     * @return
     */
    User findUserById(String id);

    /**
     * 根据id修改用户信息
     * @param user
     */
    public void updateUser(User user) ;

    /**
     * 根据id批量删除选定的用户
     * @param ids
     */
    void delSelectedUser(String[] ids);

    /**
     * 分页条件查询
     * @param _currentPage
     * @param _rows
     * @param condition
     * @return
     */
    PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition);

}