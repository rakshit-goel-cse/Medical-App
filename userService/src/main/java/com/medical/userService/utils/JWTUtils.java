package com.medical.userService.utils;

import java.security.Key;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtils {

	 private static final Logger logger = LoggerFactory.getLogger(JWTUtils.class);
	
	@Value("${com.my.security-key}")
	private String jwtSecret;
	
	private Date getExpDate() {
		long longDate=new Date().getTime()+1*60*60*1000; //hour*min*sec*millisec
		return new Date(longDate);
	}
	
	 private Key key() {
		 logger.info("sec key- "+jwtSecret);
		 logger.error("sec key- "+jwtSecret);
		    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
		  }
	
	public String generateJWTTocken(String userName) {
		return Jwts.builder()
				.setIssuer(userName)
				.setIssuedAt(new Date())
				.setExpiration(getExpDate())
				.signWith(key())
				.compact();
	}
	
	public String getUserNameFromJwtToken(String token) {
	    return Jwts.parserBuilder().setSigningKey(key()).build()
	               .parseClaimsJws(token).getBody().getIssuer();
	  }
	
	 public boolean validateJwtToken(String authToken) {
		    try {
		    	logger.info("tocken check start");
		      Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
		      logger.info("valid tocken");
		      return true;
		    } catch (MalformedJwtException e) {
		      logger.error("Invalid JWT token: {}", e.getMessage());
		    } catch (ExpiredJwtException e) {
		      logger.error("JWT token is expired: {}", e.getMessage());
		    } catch (UnsupportedJwtException e) {
		      logger.error("JWT token is unsupported: {}", e.getMessage());
		    } catch (IllegalArgumentException e) {
		      logger.error("JWT claims string is empty: {}", e.getMessage());
		    }

		    return false;
		  }
}
