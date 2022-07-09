package com.users.microservice.RequestResponseModel;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UserRequestModel {

	@NotNull(message = "Firstname cannot be null")
	private String firstName;
	
	@NotNull(message = "Lastname cannot be null")
	private String lastName;
	
	@NotNull(message = "email cannot be null")
	@Email
	private String email;
	
	@NotNull(message = "password cannot be null")
	private String password;

	public UserRequestModel() {

	}

	public UserRequestModel(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
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

}
