package com.reactive.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
	
	@Autowired
	ServerIPProperties serverProperties;
	
	@Bean
	public WebClient getWebClient(WebClient.Builder webClientBuilder){
		return webClientBuilder.baseUrl(serverProperties.getUrls()).build();
	}
}
