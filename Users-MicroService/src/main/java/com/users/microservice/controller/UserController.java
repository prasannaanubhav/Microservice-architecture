package com.users.microservice.controller;

import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.users.microservice.RequestResponseModel.CreatedUserResponseModel;
import com.users.microservice.RequestResponseModel.UserRequestModel;
import com.users.microservice.RequestResponseModel.UserResponseModel;
import com.users.microservice.dto.UserDto;
import com.users.microservice.service.IService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private Environment environment;

	@Autowired
	private IService service;

	@GetMapping(path = "/getstatus")
	public String getStatus() {
		return "get status called on port : " + environment.getProperty("local.server.port") + " and tokensecret= "
				+ environment.getProperty("token.secret");
	}

	@PostMapping(path = "/saveuser")
	public ResponseEntity<CreatedUserResponseModel> createUser(@Valid @RequestBody UserRequestModel userRequestModel) {
		ModelMapper model = new ModelMapper();
		model.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDto userDto = model.map(userRequestModel, UserDto.class);
		UserDto createdUser = service.createUser(userDto);
		CreatedUserResponseModel returnedValue = model.map(createdUser, CreatedUserResponseModel.class);
		return new ResponseEntity<CreatedUserResponseModel>(returnedValue, HttpStatus.OK);

	}

	@GetMapping(path = "/{userId}")
	public ResponseEntity<UserResponseModel> getUser(@PathVariable("userId") String userId) {
		if (userId == null) {
			throw new UsernameNotFoundException("UserId cannot be null");
		}
		UserDto userDto = service.getUser(userId);
		UserResponseModel userResponseModel = new ModelMapper().map(userDto, UserResponseModel.class);
		return new ResponseEntity<UserResponseModel>(userResponseModel, HttpStatus.OK);
	}

}
