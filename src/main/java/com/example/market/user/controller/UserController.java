package com.example.market.controllers;

import com.example.market.converters.UserConverter;
import com.example.market.model.dto.UserDTO;
import com.example.market.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserConverter userConverter;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(UserDTO userDTO, Model model) {
        if (!userService.createUser(userConverter.dtoToEntity(userDTO))) {
            model.addAttribute(
                    "errorMessage",
                    "Пользователь с email: " + userDTO.getEmail() + "уже существует"
            );
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/user/{user}")
    public String userInfo(@PathVariable("user") UserDTO userDTO, Model model) {
        model.addAttribute("user", userDTO);
        model.addAttribute("products", userDTO.getProductDTOS());
        return "user-info";
    }


}