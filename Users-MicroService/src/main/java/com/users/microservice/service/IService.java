package com.users.microservice.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.users.microservice.dto.UserDto;

public interface IService extends UserDetailsService{

	public UserDto createUser(UserDto userDto);
	public UserDto getByEmail(String userName);
	public UserDto getUser(String userId);
}
