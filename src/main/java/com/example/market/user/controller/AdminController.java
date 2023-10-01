package com.example.market.user.controller;

import com.example.market.user.converter.UserConverter;
import com.example.market.user.controller.dto.UserDTO;
import com.example.market.user.repository.model.User;
import com.example.market.user.enums.Role;
import com.example.market.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    private final UserService userService;
    private final UserConverter userConverter;

    @GetMapping("/admin")
    public String admin(Model model) {
        ArrayList<UserDTO> dtos = new ArrayList<>();
        for(User user : userService.list()) {
            dtos.add(userConverter.entityToDto(user));
        }
        model.addAttribute("users", dtos);
        return "admin";
    }

    @PostMapping("/admin/user/ban/{id}")
    public String userBan(@PathVariable("id") Long id) {
        userService.banUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/user/edit/{user}")
    public String userEdit(@PathVariable("user") UserDTO userDTO, Model model) {
        model.addAttribute("user", userDTO);
        model.addAttribute("roles", Role.values());
        return "user-edit";
    }

    @PostMapping("/admin/user/edit")
    public String userEdit(@RequestParam("userDTO") UserDTO userDTO, @RequestParam Map<String, String> form) {
        User entity = userConverter.dtoToEntity(userDTO);
        userService.changeUserRoles(entity, form);
        return "redirect:/admin";
    }
}