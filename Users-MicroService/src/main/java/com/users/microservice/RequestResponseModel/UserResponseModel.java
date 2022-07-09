package com.users.microservice.RequestResponseModel;

import java.util.List;

public class UserResponseModel {

	private String userId;
	private String firstName;
	private String lastName;
	private String email;
	private List<AlbumResponseModel> albumResponse;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<AlbumResponseModel> getAlbumResponse() {
		return albumResponse;
	}

	public void setAlbumResponse(List<AlbumResponseModel> albumResponse) {
		this.albumResponse = albumResponse;
	}

}
