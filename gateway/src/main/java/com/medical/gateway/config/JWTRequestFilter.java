package com.medical.gateway.config;

import java.io.IOException;
import java.net.Authenticator;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.medical.gateway.dto.UserDto;
import com.medical.gateway.service.JWTService;

import ch.qos.logback.core.util.StringUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTRequestFilter extends OncePerRequestFilter{
	
	@Autowired
	private JWTService jwtService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String authHeader = request.getHeader("Authorization");
		
		String user = null;
		String jwt = null;
		
		if (authHeader != null && authHeader.startsWith("Bearer ") && null==SecurityContextHolder.getContext().getAuthentication()) {
			jwt = authHeader.substring(7);
			try {
				user = jwtService.validateJWTTocken(jwt);
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		
		//validates the user and create a user profile
		if(null!=user && StringUtil.notNullNorEmpty(user)) {
			
			UserDetails userDetail=new User(user,"", new ArrayList<>());
			
			UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
			
			userToken.setDetails(
							new WebAuthenticationDetailsSource().buildDetails(request)
						);
			
			SecurityContextHolder.getContext().setAuthentication(userToken);
		}
		//continue the chain
		filterChain.doFilter(request, response);
	}
}
