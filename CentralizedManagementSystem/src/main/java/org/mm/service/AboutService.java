package org.mm.service;

import java.io.File;

import org.mm.dto.AadharDao;
import org.mm.dto.AboutDto;
import org.mm.entity.AadharEntity;
import org.mm.entity.UserProfileDetailsEntity;
import org.mm.repository.UserProfileRepository;
import org.mm.utility.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AboutService 
{
	private final JwtService jwtService;
	
	private final UserProfileRepository profileRepo;
	
	@Autowired
	private FileUtils fileUtils;
	
	public ResponseEntity<?> getProfileData(String token) 
	{
		try {
			
			Long userId = jwtService.getUserIdFromToken(token);
			UserProfileDetailsEntity profileEntity = profileRepo.findById(userId).orElse(null);
			
			AboutDto dao = new AboutDto();
			
			dao.setAddress(profileEntity.getAddress());
			dao.setContactNo(profileEntity.getContactNo());
			dao.setEmail(profileEntity.getEmail());
			dao.setName(profileEntity.getName());
			dao.setPostalCode(profileEntity.getPostalCode());
			dao.setBio(profileEntity.getBio());
			
	          File imageFile = fileUtils.getFile(profileEntity.getProfileImage(), "adminImage");
	          if (imageFile != null && imageFile.exists()) {
	              String imageUrl = "http://localhost:8081/api/v1/image/about/" + profileEntity.getProfileImage();
	              dao.setProfileImage(imageUrl);
	          }
			
			return new ResponseEntity<>(dao, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
