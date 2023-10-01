package com.example.market.converters;

import com.example.market.model.dto.ImageDTO;
import com.example.market.model.entity.Image;
import com.example.market.model.entity.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ImageConverter {

    public Image dtoToEntity(ImageDTO dto, Product productEntity) {
        return new Image(
                dto.getId(),
                dto.getName(),
                dto.getOriginalFileName(),
                dto.getSize(),
                dto.getContentType(),
                dto.isPreviewImage(),
                dto.getBytes(),
                productEntity
        );
    }

    public ImageDTO entityToDto(Image entity) {
        return new ImageDTO(entity.getId(),
                entity.getName(),
                entity.getOriginalFileName(),
                entity.getSize(),
                entity.getContentType(),
                entity.isPreviewImage(),
                entity.getBytes());
    }
}