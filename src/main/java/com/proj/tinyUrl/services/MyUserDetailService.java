package com.proj.tinyUrl.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.proj.tinyUrl.repository.UserRepository;
import com.proj.tinyUrl.utils.MyUserDetails;

@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<com.proj.tinyUrl.entites.User> user = userRepository.findByUserName(username);
		user.orElseThrow(() -> new UsernameNotFoundException("No user found"));
		return user.map(MyUserDetails::new).get();
	}

}
