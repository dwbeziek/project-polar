package com.cryolytix.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CryolytixBackendApplication {

	public static void main(String[] args) {
		System.setProperty("javax.net.debug", "ssl");
		SpringApplication.run(CryolytixBackendApplication.class, args);
	}

}
