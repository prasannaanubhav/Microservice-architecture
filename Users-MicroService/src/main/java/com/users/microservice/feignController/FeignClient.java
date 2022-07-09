package com.users.microservice.feignController;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.users.microservice.RequestResponseModel.AlbumResponseModel;

@org.springframework.cloud.openfeign.FeignClient(name = "album-ms", fallback = FallbackMethodCall.class)
public interface FeignClient {

	@GetMapping(path = "/album/{userId}")
	public List<AlbumResponseModel> getAlbums(@PathVariable("userId") String userId);
}
