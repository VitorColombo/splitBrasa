package com.brasasplit.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class SplitBrasaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SplitBrasaApplication.class, args);
	}

}
