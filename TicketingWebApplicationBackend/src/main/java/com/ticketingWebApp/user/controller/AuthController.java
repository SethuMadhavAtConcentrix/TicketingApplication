package com.ticketingWebApp.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.ticketingWebApp.user.entity.ERole;
import com.ticketingWebApp.user.entity.Role;
import com.ticketingWebApp.user.entity.User;
import com.ticketingWebApp.user.jwt.JwtUtils;
import com.ticketingWebApp.user.repository.RoleRepository;
import com.ticketingWebApp.user.repository.UserRepository;
import com.ticketingWebApp.user.request.LoginRequest;
import com.ticketingWebApp.user.request.SignupRequest;
import com.ticketingWebApp.user.response.JwtResponse;
import com.ticketingWebApp.user.response.MessageResponse;
import com.ticketingWebApp.user.service.UserDetailsImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder pwdEncoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(
				new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Validated @RequestBody SignupRequest signUpRequest) {
		if ((userRepository.existsByUsername(signUpRequest.getUsername())) || (userRepository.existsByEmail(signUpRequest.getEmail()))) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error:Details exists, provide Unique details"));
		}

		if (signUpRequest.getUsername().isEmpty() || signUpRequest.getEmail().isEmpty() || signUpRequest.getPassword().isEmpty()) {
			return ResponseEntity.badRequest().body(new MessageResponse("Please do fill all datafields"));
		}

		if ((signUpRequest.getUsername().length() < 5 || signUpRequest.getUsername().length() > 10)
				|| (signUpRequest.getEmail().length() > 20)
				|| (signUpRequest.getPassword().length() < 8 || signUpRequest.getPassword().length() > 15)) {
			return ResponseEntity.badRequest().body(new MessageResponse("Please do fill valid user details"));
		}

		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				pwdEncoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);

		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

}
