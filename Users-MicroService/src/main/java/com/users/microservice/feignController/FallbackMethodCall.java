package com.users.microservice.feignController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.users.microservice.RequestResponseModel.AlbumResponseModel;

@Component
public class FallbackMethodCall implements FeignClient{

	@Override
	public List<AlbumResponseModel> getAlbums(String userId) {
		
		return new ArrayList<>();
	}

}
