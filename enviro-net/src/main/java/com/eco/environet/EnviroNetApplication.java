package com.eco.environet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@EntityScan
@SpringBootApplication
public class EnviroNetApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnviroNetApplication.class, args);
		System.out.println("Hello world!");
	}

}
