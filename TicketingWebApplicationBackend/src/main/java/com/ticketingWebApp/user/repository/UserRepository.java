package com.ticketingWebApp.user.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ticketingWebApp.user.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);


}
