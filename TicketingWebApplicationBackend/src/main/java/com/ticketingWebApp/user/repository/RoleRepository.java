package com.ticketingWebApp.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ticketingWebApp.user.entity.ERole;
import com.ticketingWebApp.user.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	
	Optional<Role> findByName(ERole name);

}
