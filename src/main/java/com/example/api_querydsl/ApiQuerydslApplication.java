package com.example.api_querydsl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ApiQuerydslApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiQuerydslApplication.class, args);
	}

}
