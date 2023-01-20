package com.appTicketing.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appTicketing.dao.UserDao;
import com.appTicketing.service.UserService;

@RestController
@CrossOrigin(origins =  "*")
@RequestMapping("/api/user/control")
public class UserController {

	@Autowired
	private UserService userService;

	// creating user
	@PostMapping("/create")
	public ResponseEntity<UserDao> createUser(@Valid @RequestBody UserDao userDao) {
		UserDao addUser = this.userService.createUser(userDao);
		return new ResponseEntity<>(addUser, HttpStatus.CREATED);
	}

	// update user
	@PutMapping("/{userId}/update")
	public ResponseEntity<UserDao> updateUser(@PathVariable Integer userId,@Valid @RequestBody UserDao userDao) {
		UserDao updatedUserDao = this.userService.updateUser(userDao, userId);
		return ResponseEntity.ok(updatedUserDao);
	}

	// get user
	@GetMapping("/{userId}")
	public ResponseEntity<UserDao> getUserById(@PathVariable Integer userId) {
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}
	
	//get all users
	@GetMapping("/all")
	public ResponseEntity<List<UserDao>> getAllUsers(){
		List<UserDao> users = this.userService.getAllUsers();
		return ResponseEntity.ok(users);
	}
}
