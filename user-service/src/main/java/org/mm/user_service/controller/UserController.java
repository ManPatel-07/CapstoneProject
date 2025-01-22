package org.mm.user_service.controller;

import org.mm.user_service.dao.HelperIdDao;
import org.mm.user_service.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UserController 
{
	private final UserService userService;
	
	@PostMapping(path = "/saveAadharId")
	public ResponseEntity<?> createAadharId(@RequestBody HelperIdDao helperIdDao )
	{
		Boolean aadharSave = userService.createAadharId(helperIdDao.getUserId() , helperIdDao.getAadharId());
		
		return ResponseEntity.ok("Aadhar Id Save");
	}

}
