package com.proj.tinyUrl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proj.tinyUrl.entites.User;
import com.proj.tinyUrl.models.Response;
import com.proj.tinyUrl.services.UserService;
import com.proj.tinyUrl.utils.SHA512;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;

	@Autowired
	SHA512 sha512;
	
	@RequestMapping(value = "/signUpUser", method = RequestMethod.POST)
	public Response createUser(@RequestBody User user) {
		user.setPassword(sha512.get_SHA_512_SecurePassword(user.getPassword(), user.getUserName()));
		return userService.saveUser(user);
	}
	
}
