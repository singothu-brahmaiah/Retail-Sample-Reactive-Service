package com.reactive.webclient.api.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "microservices")
@Data
public class ServerIPProperties  {
	private String servers;
	private String name;
    private String urls;
 
}
