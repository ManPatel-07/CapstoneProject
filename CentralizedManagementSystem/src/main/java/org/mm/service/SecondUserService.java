package org.mm.service;

import java.util.List;
import java.util.Optional;

import org.mm.dto.ProfileDao;
import org.mm.entity.UserProfileDetailsEntity;
import org.mm.repository.UserProfileRepository;
import org.mm.utility.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;


import com.google.gson.Gson;

import lombok.RequiredArgsConstructor;


@Service
public class SecondUserService implements UserDetailsService
{
	@Autowired
	private UserProfileRepository secondUserRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private FileUtils fileUtils;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		return secondUserRepo.findByEmail(username).orElseThrow();
	}
	
	public List<UserProfileDetailsEntity> getAllUsers()
	{
		return secondUserRepo.findAll();
	}
	
	public UserProfileDetailsEntity signUp(UserProfileDetailsEntity userEntity)
	{
		Optional<UserProfileDetailsEntity> userByEmail = secondUserRepo.findByEmail(userEntity.getEmail());
		
		if(userByEmail.isPresent())
		{
			throw new BadCredentialsException("user already present ...");
		}
		
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		
		return secondUserRepo.save(userEntity);
	}
	
	public UserProfileDetailsEntity getUserByUserId(Long id)
	{
		return secondUserRepo.findById(id).orElseThrow();
	}
	
	
	public ResponseEntity<?> addAdminDetails(String json, MultipartFile adminImage)
	{
		JSONObject requestObj = new JSONObject(json);
		ProfileDao dao = new Gson().fromJson(json, ProfileDao.class);
		UserProfileDetailsEntity adminEntity = new UserProfileDetailsEntity();
		
		if (adminImage != null) {
			File file = fileUtils.uploadFile(adminImage, "adminImage");

			if (file != null) {
				requestObj.put("materialImages", file.getName());
				adminEntity.setProfileImage(file.getName());
			}
		}
		
		adminEntity.setEmail(dao.getEmail());
		adminEntity.setName(dao.getName());
		adminEntity.setPassword(dao.getPassword());
		adminEntity.setRoles(dao.getRoles());
		
		secondUserRepo.save(adminEntity);
		
		return new ResponseEntity<>(adminEntity, HttpStatus.OK);
	}	
	
	public UserProfileDetailsEntity signUp2(String json, MultipartFile adminImage)
	{
		
		JSONObject requestObj = new JSONObject(json);
		ProfileDao dao = new Gson().fromJson(json, ProfileDao.class);
		UserProfileDetailsEntity userEntity = new UserProfileDetailsEntity();
		
		Optional<UserProfileDetailsEntity> userByEmail = secondUserRepo.findByEmail(userEntity.getEmail());
		
		if(userByEmail.isPresent())
		{
			throw new BadCredentialsException("user already present ...");
		}
		
		if (adminImage != null) {
			File file = fileUtils.uploadFile(adminImage, "adminImage");

			if (file != null) {
				requestObj.put("materialImages", file.getName());
				userEntity.setProfileImage(file.getName());
			}
		}
		
		userEntity.setBio(dao.getBio());
		userEntity.setAddress(dao.getAddress());
		userEntity.setPostalCode(dao.getPostalCode());
		userEntity.setContactNo(dao.getPostalCode());
		userEntity.setEmail(dao.getEmail());
		userEntity.setName(dao.getName());
		userEntity.setRoles(dao.getRoles());
		
		userEntity.setPassword(passwordEncoder.encode(dao.getPassword()));
		
		return secondUserRepo.save(userEntity);
	}

//	public ResponseEntity<?> getAdminData() {
//		try {
//			List<UserProfileDetailsEntity> adminDataList = secondUserRepo.findAll();
//
//			adminDataList.stream()
//			.map(adminData -> {				
//				
//				ProfileDao dao = new ProfileDao();
//				
//				dao.setEmail(adminData.getEmail())
//				dao.setName(adminData.getName());
//				
//                File imageFile = fileUtils.getFile(adminData.getProfileImage(), "adminImage");
//                if (imageFile != null && imageFile.exists()) {
//                    String imageUrl = "http://localhost:8080/image/" + adminData.getProfileImage();
//                    dao.setProfileImage(imageUrl)
//                }
//				
//				return dao;
//			})
//			.toList();
//			
//			return new ResponseEntity<>(adminDao, HttpStatus.OK);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//
//	}

}
