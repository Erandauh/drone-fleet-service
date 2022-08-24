package com.musala.dronefleetservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {

	public static final String API_V1_URL = "/api/v1";

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
