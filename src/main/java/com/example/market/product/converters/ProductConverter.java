package com.example.market.product.converters;

import com.example.market.image.converter.ImageConverter;
import com.example.market.image.controller.dto.ImageDTO;
import com.example.market.product.controllers.model.ProductDTO;
import com.example.market.image.repository.model.Image;
import com.example.market.product.repositories.model.Product;
import com.example.market.user.converter.UserConverter;
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
                entity.getDateOfCreated(),
                null
        );
        ArrayList<ImageDTO> images = new ArrayList<>();
        for (Image imageDto : entity.getImages()) {
            images.add(imageConverter.entityToDto(imageDto));
        }
        dto.setImageDTOS(images);
        return dto;
    }
}
