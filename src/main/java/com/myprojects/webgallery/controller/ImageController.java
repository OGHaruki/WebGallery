package com.myprojects.webgallery.controller;

import com.myprojects.webgallery.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/image/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        String uploadImage = imageService.uploadImage(file);
        return ResponseEntity.status(200).body(uploadImage);
    }

    @GetMapping("/image/{fileName}")
    public ResponseEntity<?> getImage(@PathVariable String fileName) throws IOException{
        byte[] image = imageService.getImage(fileName);
        return ResponseEntity.status(200).contentType(MediaType.valueOf("image/png")).body(image);
    }

    @GetMapping("/images")
    public ResponseEntity<?> getImages() throws IOException{
        List<byte[]> images = imageService.getAllImages();
        return ResponseEntity.status(200).body(images);
    }
}
