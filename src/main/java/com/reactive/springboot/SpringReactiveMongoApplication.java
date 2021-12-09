package com.reactive.springboot;

import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.reactive.springboot.dto.Order;
import com.reactive.springboot.repository.OrderRepository;

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
	
	@Bean
	CommandLineRunner employees(OrderRepository orderRespository) {
		return args -> {
			orderRespository
				.deleteAll()
				.subscribe(null, null, () -> {
					Stream.of(new Order(UUID.randomUUID().toString(),"Grocery_Marimuthu_25/11/2021", 48000),
							new Order(UUID.randomUUID().toString(),"Food_Abimanyu_25/11/2021", 38000),
							new Order(UUID.randomUUID().toString(),"Electronics_Ganapathy_25/11/2021", 28000),
							new Order(UUID.randomUUID().toString(),"Household_Pusbendra_25/11/2021", 58000)
							)
					.forEach(employee -> {
						orderRespository.save(employee)
						.subscribe(System.out::println);
					});
				});
		};
	}

}
