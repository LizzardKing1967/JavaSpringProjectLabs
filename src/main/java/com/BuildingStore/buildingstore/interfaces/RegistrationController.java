package com.BuildingStore.buildingstore.interfaces;

import com.BuildingStore.buildingstore.materialRepository.UserRepository;
import com.BuildingStore.buildingstore.model.UserEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        public String registerUser(@Valid @ModelAttribute("user") UserEntity user, BindingResult result, Model model) {
            // Проверяем, есть ли уже пользователь с таким же email или именем
            if (userRepository.existsByUsername(user.getUsername())) {
                result.rejectValue("username", "error.user", "This username is already taken");
            }

            // Если есть ошибки, возвращаем на форму регистрации
            if (result.hasErrors()) {
                model.addAttribute("errorMessage", "Пользователь с таким логином уже существует!");
                return "register";
            }

            // Кодирование пароля и сохранение нового пользователя
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);

            // Перенаправляем на страницу логина после успешной регистрации
            return "redirect:/login";
        }
    }

