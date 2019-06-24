package org.arj.jaxrs.springboot.auth.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SecureApplication {
	public static void main(String[] args) {
		SpringApplication.run(SecureApplication.class, args);
	}
}
