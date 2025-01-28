package com.cryolytix.backend;

import org.springframework.boot.SpringApplication;

public class TestCryolytixBackendApplication {

	public static void main(String[] args) {
		SpringApplication.from(CryolytixBackendApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
