package com.album.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.album.entity.AlbumEntity;
import com.album.response.AlbumResponseModel;
import com.album.service.IService;

@RestController
@RequestMapping("album")
public class AlbumController {

	@Autowired
	private IService service;

	@GetMapping(path = "/{userId}")
	public ResponseEntity<List<AlbumResponseModel>> getAlbums(@PathVariable("userId") String userId) {

		List<AlbumEntity> albums = service.getAlbums(userId);

		if (albums.isEmpty()) {
			return null;
		}
		List<AlbumResponseModel> returnValue = new ArrayList<>();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		for (AlbumEntity albumEntity : albums) {
			AlbumResponseModel albumResponseModel = modelMapper.map(albumEntity, AlbumResponseModel.class);
			returnValue.add(albumResponseModel);
		}

		return new ResponseEntity<List<AlbumResponseModel>>(returnValue, HttpStatus.OK);
	}
}
