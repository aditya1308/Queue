package net.javaguides.springboot.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.javaguides.springboot.module.ImageEntity;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public void saveImages(MultipartFile image1, MultipartFile image2) {
        try {
            ImageEntity entity1 = new ImageEntity();
            entity1.setImage1(image1.getBytes());
            entity1.setImage2(image2.getBytes());
            imageRepository.save(entity1);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save images");
        }
    }
    
    public List<ImageEntity> getAllImages() {
        return imageRepository.findAll();
    }
}
