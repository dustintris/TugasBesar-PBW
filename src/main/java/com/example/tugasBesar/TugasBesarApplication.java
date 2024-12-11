package com.example.tugasBesar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class TugasBesarApplication {

	public static void main(String[] args) {
		SpringApplication.run(TugasBesarApplication.class, args);
	}

}
