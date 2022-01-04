package com.proj.tinyUrl.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.proj.tinyUrl.entites.Url;
import com.proj.tinyUrl.entites.User;
import com.proj.tinyUrl.models.AuthResponse;
import com.proj.tinyUrl.models.Response;
import com.proj.tinyUrl.repository.UserRepository;
import com.proj.tinyUrl.utils.JWTUtils;

@RestController
public class AuthController {
	
	@Autowired
	private AuthenticationManager authManger;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private JWTUtils jwtUtil;
	
	@Autowired
	Response response;
	
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public Response createAuthToken(@RequestBody User user) throws Exception {
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
		final String jwt = jwtUtil.generateToken(userDetails);
		
		Optional<User> userRes = userRepository.findByUserName(user.getUserName());
		
		HashMap<String, Object> additionalDetails = new HashMap<String, Object>();
		if(userRes.isPresent()) {
			additionalDetails.put("userDetails", userRes);
			additionalDetails.put("jwt", jwt);
		}
		response.setStatusCode(HttpStatus.OK);
		response.setMessage("");
		response.setAdditionalData(additionalDetails);

		return response;
	}

}
