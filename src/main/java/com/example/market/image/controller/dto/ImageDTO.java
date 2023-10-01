package com.example.market.image.controller.dto;
import com.example.market.product.controllers.model.ProductDTO;
import com.example.market.product.repositories.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageDTO {
    private Long id;
    private String name;
    private String originalFileName;
    private Long size;
    private String contentType;
    private boolean isPreviewImage;
    private byte[] bytes;
    private ProductDTO product;
}
