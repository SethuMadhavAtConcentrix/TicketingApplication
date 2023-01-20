package com.appTicketing.dao;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDao {
	
	private Integer id;
	
	@NotEmpty
	@Size(min = 5, message = "Please do provide a valid username")
	private String username;
	
	@Email(message = "Please do provide a valid Email")
	private String email;
	
	@NotEmpty
	@Size(min = 8, message = "Please do provide a valid password")
	private String password;

}
