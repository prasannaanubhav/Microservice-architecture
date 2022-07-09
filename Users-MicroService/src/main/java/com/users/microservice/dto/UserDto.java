package com.users.microservice.dto;

import java.io.Serializable;
import java.util.List;

import com.users.microservice.RequestResponseModel.AlbumResponseModel;

public class UserDto implements Serializable {

	private static final long serialVersionUID = 4693834933862359761L;

	private String userId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String encryptedPassword;
	private List<AlbumResponseModel> albumResponse;

	public UserDto() {

	}

	public UserDto(String userId, String firstName, String lastName, String email, String password,
			String encryptedPassword) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.encryptedPassword = encryptedPassword;
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public List<AlbumResponseModel> getAlbumResponse() {
		return albumResponse;
	}

	public void setAlbumResponse(List<AlbumResponseModel> albumResponse) {
		this.albumResponse = albumResponse;
	}

}
