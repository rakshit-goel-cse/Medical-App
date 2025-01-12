package com.medical.gateway.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@org.springframework.context.annotation.Configuration
public class Configuration {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public UriComponentsBuilder uriComponentsBuilder() {
		return UriComponentsBuilder.newInstance();
	}
}
