package org.mm.service;

import org.mm.entity.EducationEntity;
import org.mm.repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.status.Status;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EducationService 
{
	private final EducationRepository eduRepo;
	
	private final JwtService jwtService;
	
	public ResponseEntity<?> createEducationData(EducationEntity educationEntity, HttpServletRequest request)
	{
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("Missing or invalid Authorization header");
        }

        String token = authHeader.substring(7); // Remove "Bearer " prefix
        
        Long profileId = jwtService.getUserIdFromToken(token);
        
        educationEntity.setProfileId(profileId);
        
        EducationEntity savedEducation = eduRepo.save(educationEntity);
        
        return new ResponseEntity<>(savedEducation, HttpStatus.OK);
        
	}
	
	public ResponseEntity<?> getEducationByProfileId(HttpServletRequest request)
	{
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("Missing or invalid Authorization header");
        }

        String token = authHeader.substring(7); // Remove "Bearer " prefix
        
        Long profileId = jwtService.getUserIdFromToken(token);
        
        EducationEntity educationEntity = eduRepo.findByProfileId(profileId);
        
        return new ResponseEntity<>(educationEntity, HttpStatus.OK);
        
	}
}
