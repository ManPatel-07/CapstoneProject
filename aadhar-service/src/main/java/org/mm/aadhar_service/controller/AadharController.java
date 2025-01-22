package org.mm.aadhar_service.controller;

import org.mm.aadhar_service.client.UserFeignClient;
import org.mm.aadhar_service.dao.HelperIdDao;
import org.mm.aadhar_service.entity.AadharEntity;
import org.mm.aadhar_service.service.AadharService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/aadhar")
public class AadharController 
{
	private final AadharService aadharService;
	
	private final UserFeignClient userFeignClient;
	
	@PostMapping(path = "/saveAadhar")
	private ResponseEntity<?> createAadhar(@RequestBody AadharEntity aadharEntity)
	{
		AadharEntity savedAadharEntity = aadharService.createNewAadhar(aadharEntity);

		Long aadharId = savedAadharEntity.getId();
		Long userId = savedAadharEntity.getUserId();
		
		HelperIdDao helperIdDao = new HelperIdDao();
		helperIdDao.setAadharId(aadharId);
		helperIdDao.setUserId(userId);
		
		userFeignClient.createAadharId(helperIdDao);
		
		return ResponseEntity.ok("Aadhar Card Done ...");
		
	}
}
