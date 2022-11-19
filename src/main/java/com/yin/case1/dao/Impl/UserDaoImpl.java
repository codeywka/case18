package com.yin.case1.dao.Impl;

import com.yin.case1.dao.UserDao;
import com.yin.case1.domain.User;
import com.yin.case1.utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @BelongsProject: project03
 * @BelongsPackage: com.yin.p1.dao.Impl
 * @Author: yinwenkang
 * @CreateTime: 2022-09-17  17:11
 * @Description: TODO
 * @Version: 1.0
 */
public class UserDaoImpl implements UserDao {
    //1.template全局变量
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<User> findAll() {
        //使用JDBC操作数据库....
        //2.定义sql
        String sql = "select * from client";
        //3.
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));

        return users;
    }

    @Override
    public User findUserByUsernamePassword(String username,String password) {
        try {
            //1.编写sql
            String sql = "select * from user where username = ? and password = ?";
            //2.调用query方法
            User user = template.queryForObject(sql,
                    new BeanPropertyRowMapper<User>(User.class),
                    username, password);

            return user;
        } catch (DataAccessException e) {
            System.out.println("用户名或密码错误");

            return null;

        }
    }

    @Override
    public void add(User user) {
        //1.定义sql
        String sql = "insert into client values(null,?,?,?,?,?,?,null,null)";
        //2.调用update方法执行sql
        template.update(sql,user.getName(),user.getGender(),user.getAge(),
                user.getAddress(),user.getQq(),user.getEmail());
    }

    @Override
    public void delete(int id) {
        //1.定义sql
        String sql = "delete from client where id = ?";
        //2.执行sql
        template.update(sql,id);
    }

    @Override
    public User findById(int id) {
        //1.
        String sql = "select * from client where id = ?";
        //2.
        User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);
        return user;
    }

    @Override
    public void update(User user) {
        //
        String sql = "update client set name = ? ,gender = ? ,age = ? ," +
                "address = ? ,qq = ? ,email = ? where id = ?";
        //
        template.update(sql,user.getName(),user.getGender(),user.getAge(),
                user.getAddress(),user.getQq(),user.getEmail(),user.getId());
    }

    @Override
    public void deleteSelected(int id) {
        //
        String sql = "delete from client where id = ?";
        //
        template.update(sql,id);
    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        //1.定义模板初始化sql
        String sql = "select count(*) from client where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        //2.遍历map
        Set<String> keySet = condition.keySet();
        //定义参数的集合
        ArrayList<Object> params = new ArrayList<Object>();
        for (String key : keySet) {
            //排除分页条件参数
            if("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }
            //获取value
            String value = condition.get(key)[0];
            //判断value是否有值
            if(value != null && !"".equals(value)){
                sb.append(" and "+key+" like ? ");
                params.add("%" + value + "%");
            }
        }

        return template.queryForObject(sb.toString(),Integer.class,params.toArray());
    }

    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {
        String sql = "select * from client where 1 = 1 ";

        StringBuilder sb = new StringBuilder(sql);
        //2.遍历map
        Set<String> keySet = condition.keySet();
        //定义参数的集合
        List<Object> params = new ArrayList<Object>();
        for (String key : keySet) {

            //排除分页条件参数
            if("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }

            //获取value
            String value = condition.get(key)[0];
            //判断value是否有值
            if(value != null && !"".equals(value)){
                //有值
                sb.append(" and "+key+" like ? ");
                params.add("%"+value+"%");//？条件的值
            }
        }

        //添加分页查询
        sb.append(" limit ?,? ");
        //添加分页查询参数值
        params.add(start);
        params.add(rows);
        sql = sb.toString();

        return template.query(sql,new BeanPropertyRowMapper<User>(User.class),params.toArray());
    }

}
