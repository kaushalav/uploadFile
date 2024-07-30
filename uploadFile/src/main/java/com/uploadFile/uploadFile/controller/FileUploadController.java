package com.uploadFile.uploadFile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.uploadFile.uploadFile.helper.FileUploadHelper;

@RestController 
public class FileUploadController {
    @Autowired
    private FileUploadHelper fileUploadHelper;

    @PostMapping("/upload-file")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        // System.out.println(file.getOriginalFilename());
        // System.out.println(file.getSize());
        // System.out.println(file.getContentType());
        // System.out.println(file.getName());

        try{
            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must contain a file");
            }
            String contentType = file.getContentType();
            if (contentType == null || !contentType.equals("image/jpeg")) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File type should on jpg only");
            }
            boolean tmp = fileUploadHelper.uploadFile(file);
            if (!tmp) {
                return ResponseEntity.status(HttpStatus.OK).body("File upload failed");
            }
            else {
                return ResponseEntity.status(HttpStatus.OK).body(ServletUriComponentsBuilder
                        .fromCurrentContextPath().path("/images/").path(file.getOriginalFilename()).toUriString());
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        return new ResponseEntity<String>("File uploaded", HttpStatus.OK);
    }
}
