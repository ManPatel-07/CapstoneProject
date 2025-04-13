package org.mm.controller;

import org.mm.entity.EducationEntity;
import org.mm.service.EducationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1")
public class EducationController 
{

	private final EducationService educationService;
	
	@PostMapping(value = "/addEducation")
	public ResponseEntity<?> addEducationDetails(@RequestBody EducationEntity educationEntity,
			HttpServletRequest request) {
		
		return educationService.createEducationData(educationEntity, request);
		
	}
	
	@GetMapping(path = "/getEducation")
	public ResponseEntity<?> getEducationDetails(HttpServletRequest request)
	{
		return educationService.getEducationByProfileId(request);
	}
}
