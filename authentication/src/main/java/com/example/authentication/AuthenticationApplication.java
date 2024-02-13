package com.example.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.authentication")
@ComponentScan("com.example")
public class AuthenticationApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationApplication.class, args);
	}

}
