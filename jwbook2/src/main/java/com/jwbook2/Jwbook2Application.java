package com.jwbook2;

import com.jwbook2.config.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SecurityConfig.class)
public class Jwbook2Application {

	public static void main(String[] args) {
		SpringApplication.run(Jwbook2Application.class, args);
	}

}
