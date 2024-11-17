package org.mm.aadhar_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AadharServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AadharServiceApplication.class, args);
	}

}
