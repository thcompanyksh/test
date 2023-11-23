package com.example.jwtv1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JwtV1Application {
	public static void main(String[] args) {
		SpringApplication.run(JwtV1Application.class, args);
	}

}