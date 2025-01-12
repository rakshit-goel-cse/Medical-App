package com.medical.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiReactivePasswordChecker;

import static org.springframework.security.config.Customizer.withDefaults;

import com.medical.gateway.service.userService;


@Configuration
public class SecurityConfig {

//	@Autowired
//	userService userSer;
	
	
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.csrf(csrf->csrf.disable())
        .authorizeHttpRequests((req) -> req
        		.requestMatchers("/user/login","/user/validateJWT").permitAll()
        		.requestMatchers("/user/getPassword").denyAll()
        		.anyRequest().authenticated()
        	);
		http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        http.sessionManagement(t->t.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(new JWTRequestFilter(),UsernamePasswordAuthenticationFilter.class);
    return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

//	@Bean
//	public CompromisedPasswordChecker compromisedPasswordChecker() {
//		return new HaveIBeenPwnedRestApiPasswordChecker();
//	}
	
//	@Bean
//	public UserDetailsService userDetailService() {
//		return new UserDetailsService() {
//			
//			@Override
//			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//				return userSer.loadUserByUsername(username);
//			}
//		};
//	}
//
//	@Bean
//	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
//		AuthenticationManagerBuilder authenticationManagerBuilder = http
//				.getSharedObject(AuthenticationManagerBuilder.class);
//		authenticationManagerBuilder.userDetailsService(userDetailService()).passwordEncoder(passwordEncoder());
//		return authenticationManagerBuilder.build();
//	}

}
