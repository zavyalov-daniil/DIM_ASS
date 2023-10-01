package com.example.market.image.controller;

import com.example.market.image.converter.ImageConverter;
import com.example.market.image.controller.dto.ImageDTO;
import com.example.market.image.repository.model.Image;
import com.example.market.image.repository.ImageRepository;
import com.example.market.product.converters.ProductConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@RequiredArgsConstructor
public class ImageController {
    private final ImageRepository imageRepository;
    private final ImageConverter imageConverter;
    private final ProductConverter productConverter;

    @GetMapping("/images/{id}")
    private ResponseEntity<?> getImageById(@PathVariable Long id) {
        ImageDTO imageDTO = null;
        Image image = imageRepository.findById(id).orElse(null);
        if (image != null) {
            imageDTO = imageConverter.entityToDto(image);
            imageDTO.setProduct(productConverter.entityToDto(image.getProduct()));
            return ResponseEntity.ok()
                    .header("fileName", imageDTO.getOriginalFileName())
                    .contentType(MediaType.valueOf(imageDTO.getContentType()))
                    .contentLength(imageDTO.getSize())
                    .body(new InputStreamResource(new ByteArrayInputStream(imageDTO.getBytes())));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
