package com.BuildingStore.buildingstore.interfaces;

import com.BuildingStore.buildingstore.materialRepository.UserRepository;
import com.BuildingStore.buildingstore.model.UserEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

    @Controller
    public class RegistrationController {
        @Autowired
        private UserRepository userRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @GetMapping("/register")
        public String showRegistrationForm() {
            return "register";
        }

        @PostMapping("/register")
        public String registerUser(@Valid @ModelAttribute("user") UserEntity user, BindingResult result) {
            if (result.hasErrors()) {
                return "register"; // Если есть ошибки, возвращаем на форму
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user); // Сохраняем пользователя в базе данных
            return "redirect:/login"; // После успешной регистрации перенаправляем на страницу логина
        }
    }

