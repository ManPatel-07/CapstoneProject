package org.mm.service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;

import javax.crypto.SecretKey;

import org.mm.entity.UserProfileDetailsEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService 
{
	@Value("${jwt.secreteKey}")
	private String jwtSecreteKey;
	
	private SecretKey getSecretKey()
	{
		return Keys.hmacShaKeyFor(jwtSecreteKey.getBytes(StandardCharsets.UTF_8));
	}
	
	public String generateAccessToken(UserProfileDetailsEntity secondUserEntity)
	{
		return Jwts.builder()
				.subject(secondUserEntity.getId().toString())
				.claim("email", secondUserEntity.getEmail())
				.claim("roles", secondUserEntity.getRoles().toString())
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + 1000*60*10*10))
				.signWith(getSecretKey())
				.compact();
	}
	
	public String generateRefreshToken(UserProfileDetailsEntity secondUserEntity)
	{
		return Jwts.builder()
				.subject(secondUserEntity.getId().toString())
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + 1000L *60*60*24*30*6))
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
