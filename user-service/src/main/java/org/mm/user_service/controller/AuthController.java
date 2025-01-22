package org.mm.user_service.controller;

import org.mm.user_service.entity.UserEntity;
import org.mm.user_service.service.AuthService;
import org.mm.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class AuthController
{
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/signup")
	public ResponseEntity<UserEntity> signUp(@RequestBody UserEntity userEntity)
	{
		UserEntity signUpEntity = userService.signUp(userEntity);
		
		return new ResponseEntity<UserEntity>(signUpEntity, HttpStatus.OK);
	}
	
	@PostMapping(path = "/login")
	public ResponseEntity<String> login(@RequestBody UserEntity userEntity)
	{
		String token = authService.login(userEntity);
		return new ResponseEntity<String>(token, HttpStatus.OK);
	}
	
}