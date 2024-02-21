package net.javaguides.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import net.javaguides.springboot.module.ImageEntity;

@RestController
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/images")
    public ResponseEntity<String> saveImages(@RequestParam("image1") MultipartFile image1,
                                             @RequestParam("image2") MultipartFile image2) {
        imageService.saveImages(image1, image2);
        return ResponseEntity.status(HttpStatus.CREATED).body("Images saved successfully");
    }
    
    @GetMapping("/images")
    public ResponseEntity<List<ImageEntity>> getAllImages() {
        List<ImageEntity> images = imageService.getAllImages();
        return ResponseEntity.status(HttpStatus.OK).body(images);
    }
}
