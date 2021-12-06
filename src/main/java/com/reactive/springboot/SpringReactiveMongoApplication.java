package com.reactive.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info=@Info(
		title ="Reactive Spring boot webflux with mongo",
		version="1.0",
		description="Sample swagger document"
		))
public class SpringReactiveMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringReactiveMongoApplication.class, args);
	}

}
