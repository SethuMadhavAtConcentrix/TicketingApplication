package com.appTicketing.service;

import java.util.List;

import com.appTicketing.dao.UserDao;

public interface UserService {
	
	UserDao createUser(UserDao userDao);
	UserDao getUserById(Integer userId);
	UserDao updateUser(UserDao userDao, Integer userId);
	List<UserDao> getAllUsers();
	
}
