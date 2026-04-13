package com.example.actions.controller;

import com.example.actions.model.User;
import com.example.actions.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }

    // Handle form submission with validation
    @PostMapping("/submit")
    public String submit(@Valid @ModelAttribute("user") User user,
                         BindingResult result,
                         Model model) {
        if (result.hasErrors()) {
            model.addAttribute("users", userService.getAllUsers());
            return "index";
        }
        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable UUID id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}
