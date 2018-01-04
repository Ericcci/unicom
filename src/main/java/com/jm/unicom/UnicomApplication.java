package com.jm.unicom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UnicomApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnicomApplication.class, args);
	}
}