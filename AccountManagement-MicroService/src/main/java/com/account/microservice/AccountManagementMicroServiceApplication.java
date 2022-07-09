package com.account.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AccountManagementMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountManagementMicroServiceApplication.class, args);
	}

}
