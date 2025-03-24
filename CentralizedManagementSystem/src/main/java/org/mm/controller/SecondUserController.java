package org.mm.controller;

import org.mm.entity.UserProfileDetailsEntity;
import org.mm.service.SecondUserService;
import org.mm.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/secondUser")
@RestController
public class SecondUserController 
{
//	@Autowired
//	private SecondUserService secondUserService;
	
//	@GetMapping(path = "/getalluser")
//	public ResponseEntity<?> getAllUser()
//	{
//		return new ResponseEntity<>(secondUserService.getAllUsers(), HttpStatus.OK);
//	}
	
	@Autowired
	private SessionService sessionService;
	
	
	@GetMapping(path = "/test")
	public ResponseEntity<?> getTestAPInormal()
	{
		return new ResponseEntity<>("test normal", HttpStatus.OK);
	}
	
	@GetMapping(path = "/testadmin")
	public ResponseEntity<?> getTestAPI()
	{
		return new ResponseEntity<>("test admin Get Api", HttpStatus.OK);
	}
	
	@PostMapping(path = "/testadmin")
	public ResponseEntity<?> postTestApiAdmin()
	{
		return new ResponseEntity<>("test admin Post api", HttpStatus.OK);
	}
	
	@GetMapping(path = "/testUser")
	public ResponseEntity<?> getTestUserApi()
	{
		return new ResponseEntity<>("userApi call", HttpStatus.OK);
	}
	
	@PostMapping(path = "/getUserFromSession")
	public ResponseEntity<?> getUserFromSession(@RequestBody UserProfileDetailsEntity secondUser)
	{
		return new ResponseEntity<>(sessionService.getUserDataFromSession(secondUser), HttpStatus.OK);
	}
}
