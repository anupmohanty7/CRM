package com.crm.security;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
@Component
public class JwtUtil {
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private long jwtExpiration;
	
	private SecretKey getSigningKey() {//it takes string and gives us the SECRET KEY
	    return Keys.hmacShaKeyFor(secret.getBytes());
	}
	
	public String generateToken(String email) {//This generates the JWT token

	    return Jwts.builder()
	            .subject(email)
	            .issuedAt(new Date())
	            .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
	            .signWith(getSigningKey())
	            .compact();
	}
	private Claims extractAllClaims(String token) {//It's job is Take a JWT and return all the claims inside it.
	    return Jwts.parser()
	            .verifyWith(getSigningKey())
	            .build()
	            .parseSignedClaims(token)
	            .getPayload();
	}
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
	    Claims claims = extractAllClaims(token);
	    return claimsResolver.apply(claims);
	}
	public String extractUsername(String token) {
	    return extractClaim(token, Claims::getSubject);
	}
	public Date extractExpiration(String token) {
	    return extractClaim(token, Claims::getExpiration);
	}
	private boolean isTokenExpired(String token) {
	    return extractExpiration(token).before(new Date());
	}
	public boolean validateToken(String token, String email) {
	    String username = extractUsername(token);
	    return username.equals(email) && !isTokenExpired(token);
	}
}
