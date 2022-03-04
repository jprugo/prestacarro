package com.gwtsas.prestacarro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
//@ComponentScan(basePackages = {"com.gwtsas.prestacarro.entities", "com.gwtsas.prestacarro.services", "com.gwtsas.prestacarro.repositories"})
@EnableAutoConfiguration
public class PrestacarroApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrestacarroApplication.class, args);
	}
	
}
