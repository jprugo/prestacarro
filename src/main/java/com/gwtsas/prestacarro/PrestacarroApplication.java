package com.gwtsas.prestacarro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication()
//@ComponentScan(basePackages = {"com.gwtsas.prestacarro.entities", "com.gwtsas.prestacarro.services", "com.gwtsas.prestacarro.repositories"})
@EnableAutoConfiguration
public class PrestacarroApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(PrestacarroApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(PrestacarroApplication.class);
	}
}
