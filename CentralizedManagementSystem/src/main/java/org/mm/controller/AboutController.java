package org.mm.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import org.mm.service.AadharService;
import org.mm.service.AboutService;
import org.mm.utility.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/v1")
public class AboutController 
{
    @Autowired
    private FileUtils fileUtils;
    
    private final AboutService aboutService;

    @GetMapping("/getAboutData")
    public ResponseEntity<?> getAboutData(HttpServletRequest request) {
        // Extract token from the Authorization header
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("Missing or invalid Authorization header");
        }

        String token = authHeader.substring(7); // Remove "Bearer " prefix
        
        return aboutService.getProfileData(token);
    }
    
    @GetMapping("/image/about/{fileName}")
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
