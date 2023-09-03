package com.myprojects.webgallery.service;

import com.myprojects.webgallery.entity.ImageData;
import com.myprojects.webgallery.entity.User;
import com.myprojects.webgallery.payload.ImageDto;
import com.myprojects.webgallery.repository.ImageDataRepository;
import com.myprojects.webgallery.repository.UserRepository;
import jakarta.persistence.EntityManager;
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
import java.util.UUID;

@Service
public class ImageService {

    @Autowired
    private ImageDataRepository imageRepository;

    @Autowired
    private UserRepository userRepository;

    public String uploadImage(MultipartFile file) throws IOException {
        String uniqueFileName = UUID.randomUUID() + "-" + file.getOriginalFilename();
        String filePath = "\\C:\\Users\\adamj\\Pictures\\web-photos\\" + uniqueFileName;

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

        user.getImages().add(imageData);
        userRepository.save(user);
        System.out.println(user.getImages().size());

        if(imageData != null) {
            return "Image uploaded successfully : " + filePath;
        } else {
            return "Image upload failed";
        }
    }

    public byte[] getImage(UUID uuid) throws IOException {
        Optional<ImageData> ImageData = imageRepository.findById(uuid);
        String filePath = ImageData.get().getPath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }

    public ImageDto getImageDto(UUID uuid) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userRepository.findByUsername(currentPrincipalName)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + currentPrincipalName));

        Optional<ImageData> imageData = imageRepository.findById(uuid);
        ImageDto imageDto = new ImageDto();
        imageDto.setName(imageData.get().getName());
        imageDto.setPath(imageData.get().getPath());
        return imageDto;
    }

    public List<ImageDto> getAllImages() throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userRepository.findByUsername(currentPrincipalName)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + currentPrincipalName));

        List<ImageDto> images = new ArrayList<>();
        for(ImageData imageData : user.getImages()) {
            ImageDto imageDto = new ImageDto();
            imageDto.setName(imageData.getName());
            imageDto.setPath(imageData.getPath());
            images.add(imageDto);
        }
        return images;
    }
}
