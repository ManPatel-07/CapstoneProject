package org.mm.user_service.service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;

import javax.crypto.SecretKey;

import org.mm.user_service.entity.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService 
{
	@Value("${jwt.secretKey}")
	private String jwtSecretKey;
	
	private SecretKey getSecretKey()
	{
		return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
	}
	
	
	public String generateToken(UserEntity userEntity)
	{
		return Jwts.builder()
				.subject(userEntity.getId().toString())
				.claim("email", userEntity.getEmail())
				.claim("name", userEntity.getName())
				.claim("roles", Set.of("ADMIN", "USER"))
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + 1000*60))
				.signWith(getSecretKey())
				.compact();
	}
	
	public Long getUserIdFromToken(String token)
	{
		Claims claims = Jwts.parser()
				.verifyWith(getSecretKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
		
		return Long.valueOf(claims.getSubject()); 
	}
	
}
