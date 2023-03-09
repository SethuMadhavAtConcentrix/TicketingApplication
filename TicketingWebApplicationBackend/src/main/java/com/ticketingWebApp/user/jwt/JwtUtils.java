package com.ticketingWebApp.user.jwt;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.ticketingWebApp.user.service.UserDetailsImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtils {
	
	private static final Logger Logger = LoggerFactory.getLogger(JwtUtils.class);
	
	@Value("${app.secret}")
	private String jwtSecret;
	
	public String generateJwtToken(Authentication authentication) {
		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
		
		return Jwts.builder()
				.setSubject((userPrincipal.getUsername()))
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + 3600000))
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}
	
	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}
	
	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		}catch(SignatureException e) {
			Logger.error("Invalid JWT Signature: {}", e.getMessage());
		}catch(MalformedJwtException e) {
			Logger.error("Invalid JWT Token:{}",e.getMessage());
		}catch(ExpiredJwtException e) {
			Logger.error("Jwt Token is expired:{}",e.getMessage());
		}catch(UnsupportedJwtException e) {
			Logger.error("Jwt Token is unsupported:{}",e.getMessage());
		}catch(IllegalArgumentException e) {
			Logger.error("Jwt Claims string is empty:{}",e.getMessage());
		}
		
		return false;
	}

}
