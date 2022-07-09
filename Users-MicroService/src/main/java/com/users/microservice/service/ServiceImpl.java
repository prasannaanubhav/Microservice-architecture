package com.users.microservice.service;

import java.util.ArrayList;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.users.microservice.RequestResponseModel.AlbumResponseModel;
import com.users.microservice.dto.UserDto;
import com.users.microservice.entity.UserEntity;
import com.users.microservice.feignController.FeignClient;
import com.users.microservice.repository.UserRepository;

@Service
public class ServiceImpl implements IService {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

//	@Autowired
//	private RestTemplate restTemplate;
	@Autowired
	private FeignClient feignClient;

	@Autowired
	private Environment environment;

	@Override
	public UserDto createUser(UserDto userDto) {
		ModelMapper model = new ModelMapper();
		userDto.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		userDto.setUserId(UUID.randomUUID().toString());
		UserEntity userEntity = model.map(userDto, UserEntity.class);
		UserEntity savedValue = userRepository.save(userEntity);
		UserDto returnedValue = model.map(savedValue, UserDto.class);
		return returnedValue;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity != null) {
			return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), true, true, true, true,
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException(email);
		}
	}

	@Override
	public UserDto getByEmail(String userName) {
		UserEntity userEntity = userRepository.findByEmail(userName);
		if (userEntity == null) {
			throw new UsernameNotFoundException(userName);
		}
		return new ModelMapper().map(userEntity, UserDto.class);
	}

	@Override
	public UserDto getUser(String userId) {
		ModelMapper modelMapper = new ModelMapper();
		UserEntity userEntity = userRepository.findByUserId(userId);
		if (userEntity == null) {
			throw new UsernameNotFoundException("User with " + userId + " Not Found");
		}
		UserDto userDto = modelMapper.map(userEntity, UserDto.class);
//		ParameterizedTypeReference<List<AlbumResponseModel>> listOfAlbumResponseModel = new ParameterizedTypeReference<List<AlbumResponseModel>>() {
//		};
//		String album_ms_url = environment.getProperty("api.album.getalbums.url") + userId;
//		ResponseEntity<List<AlbumResponseModel>> responseEntity = restTemplate.exchange(album_ms_url, HttpMethod.GET,
//				null, listOfAlbumResponseModel);
		logger.info("Before calling album microservice");
		List<AlbumResponseModel> albums = feignClient.getAlbums(userId);
		logger.info("After calling album microservice");
//		userDto.setAlbumResponse(responseEntity.getBody());
		userDto.setAlbumResponse(albums);
		return userDto;
	}

}
