package com.medical.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Arrays;


@Configuration
public class SecurityConfig {

//	@Autowired
//	userService userSer;
	
	@Autowired
	private JWTRequestFilter requestFilter;
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource(){
		CorsConfiguration corConfig= new CorsConfiguration();
		corConfig.setAllowCredentials(true);
		corConfig.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
		corConfig.setAllowedMethods(Arrays.asList("GET","POST"));
		corConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Accept","user-name","password"));
		
		UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corConfig);
		return source;
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.csrf(csrf->csrf.disable())
        .authorizeHttpRequests((req) -> req
        		.requestMatchers("/user/login").permitAll()
        		.requestMatchers("/user/getPassword").denyAll()
        		.anyRequest().authenticated()
        	);
		http.cors(cors->cors.configurationSource(corsConfigurationSource()));
		http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        http.sessionManagement(t->t.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(requestFilter,UsernamePasswordAuthenticationFilter.class);
        
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
