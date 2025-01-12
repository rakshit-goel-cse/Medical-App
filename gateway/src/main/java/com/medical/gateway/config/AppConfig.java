package com.medical.gateway.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@org.springframework.context.annotation.Configuration
public class AppConfig {

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
		System.out.println("RestTemplate Beancreation --------------- ");
		return new RestTemplate();
	}

    @Bean
    UriComponentsBuilder uriComponentsBuilder() {
		return UriComponentsBuilder.newInstance();
	}
}
