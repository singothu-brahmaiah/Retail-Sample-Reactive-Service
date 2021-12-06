package com.reactive.webclient.api.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.reactive.webclient.api.model.Customer;
import com.reactive.webclient.api.model.User;
import com.reactive.webclient.api.service.CustomerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	
     WebClient webClient;
     
    @Autowired 
    private CustomerService service;

	@PostConstruct
	public void init() {
		webClient = WebClient.builder().baseUrl("http://localhost:9090/product")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
	}

	/*
	 * @GetMapping("/getCustomerDtls") public Flux<Customer> getAllData() {
	 * 
	 * }
	 */
	
	@GetMapping(value ="/{id}")
    public Mono<User> getAllCustomersStream(@PathVariable String id)throws Exception {
		
        return service.getUserId(id);
        
    }

}
