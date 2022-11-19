package com.yin.case1.dao;

import com.yin.case1.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户操作的DAO
 */
public interface UserDao {
    /**
     *查找用户所有信息
     */
    public List<User> findAll();
    /**
     * 根据用户名密码查找用户信息
     */
    public User findUserByUsernamePassword(String username,String password);

    /**
     * 添加用户信息
     */
    public void add(User user);

    /**
     * 根据id删除用户
     * @param id
     */
    void delete(int id);

    User findById(int id);

    void update(User user);

    void deleteSelected(int id);

    /**
     * 查询总记录数
     * @return
     * @param condition
     */
    int findTotalCount(Map<String, String[]> condition);

    /**
     * 分页查询每页记录
     * @param start
     * @param rows
     * @param condition
     * @return
     */
    List<User> findByPage(int start, int rows, Map<String, String[]> condition);

}
