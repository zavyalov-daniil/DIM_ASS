package com.example.market.converters;

import com.example.market.model.dto.ProductDTO;
import com.example.market.model.dto.UserDTO;
import com.example.market.model.entity.Image;
import com.example.market.model.entity.Product;
import com.example.market.model.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@AllArgsConstructor
public class UserConverter {
    private final ImageConverter imageConverter;
    private final ProductConverter productConverter;

    public User dtoToEntity(UserDTO dto) {
        User entity = new User(dto.getId(),
                dto.getEmail(),
                dto.getPhone(),
                dto.getName(),
                dto.isEnabled(),
                imageConverter.dtoToEntity(dto.getAvatar(), null),
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
