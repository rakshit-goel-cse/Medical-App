package com.medical.gateway.service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class JWTService {

	@Autowired
	private RestTemplate restTemp;
//	@Autowired
//	private UriComponentsBuilder uriBuilder;
	
	public String validateJWTTocken(String jwt) throws Exception {		
		// Create a fresh UriComponentsBuilder for each request 
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance()
				.uri(new URI("http://userService/user/validateJWT")).queryParam("jwt-token", jwt);

		if(null==restTemp) throw new Exception("Rest Template is null");
		if(null==uriBuilder) throw new Exception("URI Builder is null");

		String uri=uriBuilder
			.build().toUriString();

		System.out.println("JWT uri- "+uri);
		
		return restTemp.getForObject(uri, String.class);
	}
	
	
}
