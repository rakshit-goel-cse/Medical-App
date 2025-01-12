package com.medical.gateway.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.medical.gateway.dto.UserDto;

@Service
public class JWTService {

//	@Autowired
//	RestTemplate restTemp;
	
	public String validateJWTTocken(String jwt) throws URISyntaxException {
		RestTemplate restTemp=new RestTemplate();
		UriComponentsBuilder uriBuilder=UriComponentsBuilder.newInstance();
		
		String uri=uriBuilder
			.uri(new URI("http://userService/user/validateJWT"))
			.queryParam("jwt", jwt)
			.build().toUriString();
		
		return restTemp.getForObject(uri, String.class);
	}
	
	
}
