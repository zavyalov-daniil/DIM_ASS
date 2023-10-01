package com.example.market.user.converter;

import com.example.market.image.controller.dto.ImageDTO;
import com.example.market.image.converter.ImageConverter;
import com.example.market.image.repository.model.Image;
import com.example.market.product.converters.ProductConverter;
import com.example.market.product.controllers.model.ProductDTO;
import com.example.market.user.controller.dto.UserDTO;
import com.example.market.product.repositories.model.Product;
import com.example.market.user.repository.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@AllArgsConstructor
public class UserConverter {
    private final ImageConverter imageConverter;
    private final ProductConverter productConverter;

    public User dtoToEntity(UserDTO dto) {
        ImageDTO imageDTO = dto.getAvatar();
        Image image = null;
        if (imageDTO != null) {
            image = imageConverter.dtoToEntity(dto.getAvatar(), null);
        }
        User entity = new User(dto.getId(),
                dto.getEmail(),
                dto.getPhone(),
                dto.getName(),
                dto.isEnabled(),
                image,
                dto.getPassword(),
                dto.getRoles(),
                null,
                dto.getDateOfCreated()
        );
        ArrayList<Product> products = new ArrayList<>();
        for(ProductDTO productDTO : dto.getProductDTOS()) {
            products.add(productConverter.dtoToEntity(productDTO, entity));
        }
        entity.setProductEntities(products);
        return entity;
    }

    public UserDTO entityToDto(User entity) {
        UserDTO dto = new UserDTO(entity.getId(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getName(),
                entity.isEnabled(),
                imageConverter.entityToDto(entity.getAvatar()),
                entity.getPassword(),
                entity.getRoles(),
                null,
                entity.getDateOfCreated()
        );
        ArrayList<ProductDTO> products = new ArrayList<>();
        for(Product product : entity.getProductEntities()) {
            products.add(productConverter.entityToDto(product));
        }
        dto.setProductDTOS(products);
        return dto;
    }
}
