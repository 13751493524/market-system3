package com.cn.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.cn.entity.User;

@Repository
public class UserDao {
	@Resource
	JdbcTemplate jdbcTemplate;
	
	public User getUserByUserName(User user){
        StringBuffer sql = new StringBuffer("select * from user where 1 = 1 and user_name = ? ");
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
        List list =  jdbcTemplate.query(sql.toString(), new Object[] {user.getUserName()}, rowMapper);
        return (User)((list != null && list.size() > 0)? list.get(0):null);
	}

	public void addUser(User user) {
		String sql = "insert into user(user_name,password) values(?,?)";
        jdbcTemplate.update(sql, new Object[] {user.getUserName(),user.getPassword()});
	}
	
	public User login(User user){
		String sql = "select * from user where 1 = 1 and user_name = ? and password = ? ";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
        List list =  jdbcTemplate.query(sql.toString(), new Object[] {user.getUserName(),user.getPassword()}, rowMapper);
        return (User)((list != null && list.size() > 0)? list.get(0):null);
	}
}
