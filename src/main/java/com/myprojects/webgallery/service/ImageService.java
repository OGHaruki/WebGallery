package com.myprojects.webgallery.service;

import com.myprojects.webgallery.entity.ImageData;
import com.myprojects.webgallery.repository.ImageDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageDataRepository repository;

    public String uploadImage(MultipartFile file) throws IOException {
        String filePath = "\\C:\\Users\\adamj\\Pictures\\web-photos\\" + file.getOriginalFilename();

        ImageData imageData = repository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .path(filePath)
                .build());

        file.transferTo(new File(filePath));

        if(imageData != null) {
            return "Image uploaded successfully : " + filePath;
        } else {
            return "Image upload failed";
        }
    }

    public byte[] getImage(String fileName) throws IOException {
        Optional<ImageData> ImageData = repository.findByName(fileName);
        String filePath = ImageData.get().getPath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }
}
