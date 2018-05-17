package com.mkejeiri.votingsytem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class VotingsytemApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotingsytemApplication.class, args);
	}
}
