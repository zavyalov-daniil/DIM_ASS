package com.example.market.controllers;

import com.example.market.converters.ProductConverter;
import com.example.market.model.dto.ImageDTO;
import com.example.market.model.dto.ProductDTO;
import com.example.market.model.entity.Image;
import com.example.market.model.entity.Product;
import com.example.market.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;

    @GetMapping("/")
    public String products(@RequestParam(name = "title", required = false) String title, Principal principal, Model model) {
        model.addAttribute("products", productService.listProducts(title));
        model.addAttribute("user", productService.getUserByPrincipal(principal));
        return "products";
    }

    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable Long id, Model model) {
        ProductDTO productDTO = null;
        Product product = productService.getProductById(id);
        if (product != null) {
            productDTO = productConverter.entityToDto(product);
            model.addAttribute("product", productDTO);
            model.addAttribute("images", productDTO.getImageDTOS());
        }
        return "product-info";
    }

    @PostMapping("/product/create")
    public String createProduct(@RequestParam("file1") MultipartFile file1,
                                @RequestParam("file2") MultipartFile file2,
                                @RequestParam("file3") MultipartFile file3,
                                ProductDTO productDTO, Principal principal) throws IOException {
        productService.saveProduct(
                principal,
                productConverter.dtoToEntity(productDTO, null), //пользователя ставим в сервисе
                file1,
                file2,
                file3
        );
        return "redirect:/";
    }

    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/";
    }
}
