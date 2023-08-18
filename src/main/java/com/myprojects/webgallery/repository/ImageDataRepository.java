package com.myprojects.webgallery.repository;

import com.myprojects.webgallery.entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageDataRepository extends JpaRepository<ImageData, Long> {
    Optional<ImageData> findByName(String fileName);

    Boolean existsByName(String fileName);
}
