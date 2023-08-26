package com.myprojects.webgallery.service;

import com.myprojects.webgallery.entity.ImageData;
import com.myprojects.webgallery.entity.User;
import com.myprojects.webgallery.repository.ImageDataRepository;
import com.myprojects.webgallery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageDataRepository imageRepository;

    @Autowired
    private UserRepository userRepository;

    public String uploadImage(MultipartFile file) throws IOException {
        String filePath = "\\C:\\Users\\adamj\\Pictures\\web-photos\\" + file.getOriginalFilename();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String currentPrincipalName = authentication.getName();
        User user = userRepository.findByUsername(currentPrincipalName)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + currentPrincipalName));

        ImageData imageData = imageRepository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .owner(user)
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
        Optional<ImageData> ImageData = imageRepository.findByName(fileName);
        String filePath = ImageData.get().getPath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }

    /*public List<byte[]> getAllImages() throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userRepository.findByUsername(currentPrincipalName)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + currentPrincipalName));

        List<byte[]> images = new ArrayList<>();
        for(ImageData imageData : user.getImages()) {
            byte[] image = getImage(imageData.getName());
            images.add(image);
        }

        return images;
    }*/
}
