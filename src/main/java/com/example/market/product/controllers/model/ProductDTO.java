package com.example.market.product.controllers.model;

import com.example.market.image.controller.dto.ImageDTO;
import com.example.market.user.controller.dto.UserDTO;
import com.example.market.user.repository.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    @NotNull(message = "Поле title не должно быть пустым")
    private String title;
    @NotNull(message = "Добавьте описание")
    private String description;
    @Min(1)
    @Max(10000)
    private int price;
    @NotBlank
    private String location;
    private List<ImageDTO> imageDTOS  = new ArrayList<>();
    private Long previewImageId;
    private LocalDateTime dateOfCreated;
    private UserDTO user;
}
