package org.mm.user_service.service;

import org.mm.user_service.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService
{
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;
	
	public String login(UserEntity userEntity)
	{
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(userEntity.getEmail(), userEntity.getPassword())
				);
		
		UserEntity userEntity2 = (UserEntity) authentication.getPrincipal();
		
		String token = jwtService.generateToken(userEntity2);
		
		return token;
	}
}