package com.album.service;

import java.util.List;

import com.album.entity.AlbumEntity;

public interface IService {

	public List<AlbumEntity> getAlbums(String userId);
}
