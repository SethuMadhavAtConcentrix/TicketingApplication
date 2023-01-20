package com.appTicketing.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appTicketing.dao.UserDao;
import com.appTicketing.entity.User;
import com.appTicketing.exceptionalHandling.SourceUnAvailable;
import com.appTicketing.repository.UserRepository;
import com.appTicketing.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDao createUser(UserDao userDao) {
		User user = this.daoToUser(userDao);
		User savedUser = this.userRepository.save(user);
		return this.userToDao(savedUser);
	}

	@Override
	public UserDao getUserById(Integer userId) {
		User user = this.userRepository.findById(userId).orElseThrow(()-> new SourceUnAvailable("User", "user id", userId));
		return this.userToDao(user);
	}
	
	@Override
	public UserDao updateUser(UserDao userDao, Integer userId) {
		User user = this.userRepository.findById(userId).orElseThrow(() -> new SourceUnAvailable("User","user id",userId));
		user.setUsername(userDao.getUsername());
		user.setPassword(userDao.getPassword());
		user.setEmail(userDao.getEmail());
		User updatedUser = this.userRepository.save(user);
		UserDao updatedUserDao = this.userToDao(updatedUser);
		return updatedUserDao;
	}
	
	@Override
	public List<UserDao> getAllUsers(){
		List<User> users = this.userRepository.findAll();
		List<UserDao> userDaos = users.stream().map((user) -> this.modelMapper.map(user,UserDao.class)).collect(Collectors.toList());
		return userDaos;
	}
	
	public User daoToUser(UserDao userDao) {
		User user = this.modelMapper.map(userDao, User.class);
		return user;
		/*User user = new User();
		user.setId(userDao.getId());
		user.setUsername(userDao.getUsername());
		user.setEmail(userDao.getEmail());
		user.setPassword(userDao.getPassword());
		return user;*/
	}
	
	public UserDao userToDao(User user) {
		UserDao userDao = this.modelMapper.map(user, UserDao.class);
		return userDao;
		/*UserDao userDao = new UserDao();
		userDao.setId(user.getId());
		userDao.setUsername(user.getUsername());
		userDao.setEmail(user.getEmail());
		userDao.setPassword(user.getPassword());
		return userDao;*/
	}

	

}
