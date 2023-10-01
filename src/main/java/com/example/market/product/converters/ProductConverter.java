package com.example.market.converters;

import com.example.market.image.converter.ImageConverter;
import com.example.market.image.controller.dto.ImageDTO;
import com.example.market.model.dto.ProductDTO;
import com.example.market.image.repository.model.Image;
import com.example.market.model.entity.Product;
import com.example.market.user.repository.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@AllArgsConstructor
public class ProductConverter {
    private ImageConverter imageConverter;

    public Product dtoToEntity(ProductDTO dto, User userEntity) {
        Product entity = new Product(dto.getId(),
                dto.getTitle(),
                dto.getDescription(),
                dto.getPrice(),
                dto.getLocation(),
                null,
                dto.getPreviewImageId(),
                userEntity,
                dto.getDateOfCreated()
        );
        ArrayList<Image> images = new ArrayList<>();
        for (ImageDTO imageDto : dto.getImageDTOS()) {
            images.add(imageConverter.dtoToEntity(imageDto, entity));
        }
        entity.setImages(images);
        return entity;
    }

    public ProductDTO entityToDto(Product entity) {
        ProductDTO dto = new ProductDTO(entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getLocation(),
                null,
                entity.getPreviewImageId(),
                entity.getDateOfCreated()
        );
        ArrayList<ImageDTO> images = new ArrayList<>();
        for (Image imageDto : entity.getImages()) {
            images.add(imageConverter.entityToDto(imageDto));
        }
        dto.setImageDTOS(images);
        return dto;
    }
}
