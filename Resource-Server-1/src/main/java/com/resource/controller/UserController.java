package com.resource.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resource.response.UserResponse;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private Environment env;

	@GetMapping("/status")
	public ResponseEntity<String> getStatus() {

		return new ResponseEntity<String>("Checking Health : "+env.getProperty("local.server.port"), HttpStatus.OK);
	}

//	@Secured("ROLE_developers")
//	@PreAuthorize("hasAuthority('ROLE_developers')")
//	@PreAuthorize("hasRole('developers')")
	@PreAuthorize("hasRole('developers') or #id == #jwt.subject")
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") String uid, @AuthenticationPrincipal Jwt jwt) {

		return new ResponseEntity<String>(uid + " is Deleted and subject is " + jwt.getSubject(), HttpStatus.OK);
	}

	@PostAuthorize("returnObject.body.userId == #jwt.subject")
	@GetMapping(path = "/getusers")
	public ResponseEntity<UserResponse> getUserDetails(@AuthenticationPrincipal Jwt jwt) {

		UserResponse userResponse = new UserResponse("Anubhav", "Prasanna", "a729ed54-5723-4513-a2a7-b45d3a4caf2c");

		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK) ;
	}

}
