package com.example.jwtv2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JwtV2Application {

	public static void main(String[] args) {
		SpringApplication.run(JwtV2Application.class, args);
	}

}
