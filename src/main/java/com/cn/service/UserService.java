package com.cn.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cn.common.service.BaseServiceImpl;
import com.cn.dao.UserDao;
import com.cn.entity.User;

@Service
public class UserService extends BaseServiceImpl{
	@Resource
	UserDao userDao;
	public User getUserByUserName(User user){
		return userDao.getUserByUserName(user);
	}
	
	@Transactional
	public void addUser(User user) {
		userDao.addUser(user);
	}
	
	public User login(User user){
		return userDao.login(user);
	}
}
