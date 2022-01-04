package com.proj.tinyUrl.utils;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.proj.tinyUrl.entites.User;

@Component
public class MyUserDetails implements UserDetails {
	
	private String userName;
	private String password;
	private Integer id;
	private String name;
	
	public MyUserDetails(User user) {
		this.userName = user.getUserName();
		this.name = user.getName();
		this.id = user.getId();
		this.name = user.getName();
	}
	
	public MyUserDetails() {
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return "Password";
	}

	@Override
	public String getUsername() {
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
