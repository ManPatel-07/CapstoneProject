package org.mm.service;

import java.io.File;
import java.util.List;

import org.json.JSONObject;
import org.mm.dto.AdminDao;
import org.mm.entity.AdminEntity;
import org.mm.repository.AdminRepository;
import org.mm.utility.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdminService 
{
	private final AdminRepository adminRepo;
	
	@Autowired
	private FileUtils fileUtils;
	
	public ResponseEntity<?> addAdminDetails(String json, MultipartFile adminImage)
	{
		JSONObject requestObj = new JSONObject(json);
		AdminDao dao = new Gson().fromJson(json, AdminDao.class);
		AdminEntity adminEntity = new AdminEntity();
		
		if (adminImage != null) {
			File file = fileUtils.uploadFile(adminImage, "adminImage");

			if (file != null) {
				requestObj.put("materialImages", file.getName());
				adminEntity.setAdminImage(file.getName());
			}
		}
		
		adminEntity.setAdminNo(dao.getAdminNo());
		adminEntity.setName(dao.getName());
		
		adminRepo.save(adminEntity);
		
		return new ResponseEntity<>(adminEntity, HttpStatus.OK);
	}	

	public ResponseEntity<?> getAdminData() {
		try {
			List<AdminEntity> adminDataList = adminRepo.findAll();

			List<AdminDao> adminDao = adminDataList.stream()
			.map(adminData -> {				
				
				AdminDao dao = new AdminDao();
				
				dao.setAdminNo(adminData.getAdminNo());
				dao.setName(adminData.getName());
				
                File imageFile = fileUtils.getFile(adminData.getAdminImage(), "adminImage");
                if (imageFile != null && imageFile.exists()) {
                    String imageUrl = "http://localhost:8081/testImage/image/" + adminData.getAdminImage();
                    dao.setAdminImage(imageUrl);
                }
				
				return dao;
			})
			.toList();
			
			return new ResponseEntity<>(adminDao, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
