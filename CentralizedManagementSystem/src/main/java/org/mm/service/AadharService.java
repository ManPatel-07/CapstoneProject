package org.mm.service;

import java.io.File;
import java.util.List;

import org.json.JSONObject;
import org.mm.dto.AadharDao;
import org.mm.entity.AadharEntity;
import org.mm.entity.UserProfileDetailsEntity;
import org.mm.repository.AadharRepository;
import org.mm.repository.UserProfileRepository;
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
public class AadharService 
{
	private final AadharRepository aadharRepo;
	
	@Autowired
	private FileUtils fileUtils;
	
	public ResponseEntity<?> addAadharDetails2(String json, MultipartFile aadharImage)
	{
		JSONObject requestObj = new JSONObject(json);
		AadharDao dao = new Gson().fromJson(json, AadharDao.class);
		AadharEntity aadharEntity = new AadharEntity();
		
		if (aadharImage != null) {
			File file = fileUtils.uploadFile(aadharImage, "aadharImage");

			if (file != null) {
				requestObj.put("materialImages", file.getName());
				aadharEntity.setAadharImage(file.getName());
			}
		}
		
		aadharEntity.setAadharName(dao.getAadharName());
		aadharEntity.setAadharNo(dao.getAadharNo());
		aadharEntity.setProfileId(dao.getProfileId());
		
		aadharRepo.save(aadharEntity);
		
		return new ResponseEntity<>(aadharEntity, HttpStatus.OK);
	}

	public ResponseEntity<?> addAadharDetails(String json, MultipartFile aadharImage)
	{
		JSONObject requestObj = new JSONObject(json);
		AadharDao dao = new Gson().fromJson(json, AadharDao.class);
		AadharEntity aadharEntity = new AadharEntity();
		
		if (aadharImage != null) {
			File file = fileUtils.uploadFile(aadharImage, "aadharImage");

			if (file != null) {
				requestObj.put("materialImages", file.getName());
				aadharEntity.setAadharImage(file.getName());
			}
		}
		
		aadharEntity.setAadharName(dao.getAadharName());
		aadharEntity.setAadharNo(dao.getAadharNo());
		
		aadharRepo.save(aadharEntity);
		
		return new ResponseEntity<>(aadharEntity, HttpStatus.OK);
	}

	public ResponseEntity<?> getAadharData() {
		try {
			List<AadharEntity> aadharDataList = aadharRepo.findAll();

			List<AadharDao> aadharDaoList = aadharDataList.stream()
			.map(aadharData -> {				
				
				AadharDao dao = new AadharDao();
				
				dao.setAadharName(aadharData.getAadharName());
				dao.setAadharNo(aadharData.getAadharNo());
				
                File imageFile = fileUtils.getFile(aadharData.getAadharImage(), "aadharImage");
                if (imageFile != null && imageFile.exists()) {
                    String imageUrl = "http://localhost:8080/api/v1/image/" + aadharData.getAadharImage();
                    dao.setAadharImage(imageUrl);
                }
				
				return dao;
			})
			.toList();
			
			return new ResponseEntity<>(aadharDaoList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}	


}
