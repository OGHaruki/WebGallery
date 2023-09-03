package com.myprojects.webgallery.repository;

import com.myprojects.webgallery.entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ImageDataRepository extends JpaRepository<ImageData, UUID> {
    Optional<ImageData> findById(UUID uuid);
}
