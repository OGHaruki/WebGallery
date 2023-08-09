package com.myprojects.webgallery.repository;

import com.myprojects.webgallery.entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageDataRepository extends JpaRepository<ImageData, Long> {
}
