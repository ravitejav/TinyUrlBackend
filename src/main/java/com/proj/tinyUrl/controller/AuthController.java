package com.proj.tinyUrl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proj.tinyUrl.entites.User;
import com.proj.tinyUrl.models.AuthResponse;
import com.proj.tinyUrl.utils.JWTUtils;

@RestController
public class AuthController {
	
	@Autowired
	private AuthenticationManager authManger;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JWTUtils jwtUtil;
	
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthToken(@RequestBody User user) throws Exception {
		
		try {
			authManger.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));			
		} catch (BadCredentialsException e) {
			throw new Exception("Invalid Username and password");
		}
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
		final String jwt = jwtUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthResponse(jwt));
	}

}
