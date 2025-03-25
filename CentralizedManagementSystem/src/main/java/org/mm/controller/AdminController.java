package org.mm.controller;

import org.mm.service.AdminService;
import org.mm.utility.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;


@RestController
@RequestMapping(path = "/testImage")
public class AdminController 
{
	@Autowired
	private AdminService adminService;
	
    @Autowired
    private FileUtils fileUtils;
	
	@PostMapping(value = "/addAdminData", consumes = "multipart/form-data")
	public ResponseEntity<?> addOrUpdatepackageMaterial(@RequestParam("data") String json,
			@RequestParam(value = "adminImage", required = false) MultipartFile adminImage) {
		
		return adminService.addAdminDetails(json, adminImage);
		
	}
	
	@GetMapping(value = "/getAdminData")
	public ResponseEntity<?> getAdminData()
	{	
		return adminService.getAdminData();
		
	}
	
    @GetMapping("/image/{fileName}")
    public ResponseEntity<?> getImage(@PathVariable("fileName") String fileName) {
        try {
            File imageFile = fileUtils.getFile(fileName, "adminImage");

            if (imageFile != null && imageFile.exists()) {
                Path path = imageFile.toPath();
                byte[] fileBytes = Files.readAllBytes(path);

                String contentType = Files.probeContentType(path);

                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .body(fileBytes);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving image");
        }
    }

}
