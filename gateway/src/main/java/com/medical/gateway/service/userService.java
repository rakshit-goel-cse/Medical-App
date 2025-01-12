package com.medical.gateway.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.medical.gateway.dto.UserDto;

import ch.qos.logback.core.util.StringUtil;

@Service
public class userService implements UserDetailsService{

	@Autowired
	private RestTemplate restTemp;
	
	//@Autowired
	private PasswordEncoder passEnc= new BCryptPasswordEncoder();
	
	Logger logger= LoggerFactory.getLogger("userService");
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// Logic to get user from database

		UserDto dto = restTemp.getForObject("http://userService/user/getPassword/" + username, UserDto.class);

		if (null != dto && dto.getId() > 0 && !StringUtil.isNullOrEmpty(dto.getUserName())
				&& StringUtil.notNullNorEmpty(dto.getPassword())) {
			String password = passEnc.encode(dto.getPassword());
			//logger.info("**********************/n" + password + "/n***********************************");
			return new User(username, password, new ArrayList<>());
		}
		return new User(username, "noop", new ArrayList<>());
	}
}
