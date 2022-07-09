package com.album.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.album.entity.AlbumEntity;

@Service
public class ServiceImpl implements IService {

	@Override
	public List<AlbumEntity> getAlbums(String userId) {

		List<AlbumEntity> returnValue = new ArrayList<>();

		AlbumEntity albumEntity1 = new AlbumEntity();
		albumEntity1.setAlbumId(UUID.randomUUID().toString());
		albumEntity1.setDescription("Rock/Metal");
		albumEntity1.setId(1);
		albumEntity1.setName("Meteora");
		albumEntity1.setUserId(userId);

		AlbumEntity albumEntity2 = new AlbumEntity();
		albumEntity2.setAlbumId(UUID.randomUUID().toString());
		albumEntity2.setDescription("Rock/Metal");
		albumEntity2.setId(2);
		albumEntity2.setName("One More Light");
		albumEntity2.setUserId(userId);

		returnValue.add(albumEntity1);
		returnValue.add(albumEntity2);
		return returnValue;
	}

}
