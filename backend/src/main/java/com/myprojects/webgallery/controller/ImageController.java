package com.myprojects.webgallery.controller;

import com.myprojects.webgallery.payload.ImageDto;
import com.myprojects.webgallery.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

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

    @GetMapping("/image/{uuid}")
    public ResponseEntity<?> getImage(@PathVariable UUID uuid) throws IOException{
        byte[] image = imageService.getImage(uuid);
        return ResponseEntity.status(200).contentType(MediaType.valueOf("image/png")).body(image);
    }

    @GetMapping("/image/{uuid}/info")
    public ResponseEntity<?> getImageInfo(@PathVariable UUID uuid) throws IOException{
        ImageDto image = imageService.getImageDto(uuid);
        return ResponseEntity.status(200).body(image);
    }

    @GetMapping("/images")
    public ResponseEntity<?> getImages() throws IOException{
        List<ImageDto> images = imageService.getAllImages();
        return ResponseEntity.status(200).body(images);
    }
}
