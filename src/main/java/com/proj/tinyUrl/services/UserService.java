package com.proj.tinyUrl.services;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.proj.tinyUrl.MessageConstants;
import com.proj.tinyUrl.entites.User;
import com.proj.tinyUrl.models.Response;
import com.proj.tinyUrl.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private Response response;
	
	public Response saveUser(User user) {
		HashMap<String, Object> additionalDetails = new HashMap<String, Object>();
		User createdUser = userRepository.save(user);
		createdUser.setPassword(null);
		additionalDetails.put("User", createdUser);
		response.setStatusCode(HttpStatus.OK);
		response.setAdditionalData(additionalDetails);
		if(createdUser.getId() != null) {
			response.setMessage(MessageConstants.USER_CREATED_SUCCESS);	
		} else {
			response.setMessage(MessageConstants.USER_CREATED_FAILURE);	
		}
		return response;
	}
	
}
