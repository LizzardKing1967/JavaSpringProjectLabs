package com.BuildingStore.buildingstore.interfaces;

import com.BuildingStore.buildingstore.services.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.context.SecurityContextHolder;


@Controller
public class LoginController {

    private final AuthenticationProvider authenticationProvider;

    public LoginController(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Шаблон логина
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username, password);

            // Аутентифицируем пользователя
            Authentication authentication = authenticationProvider.authenticate(authenticationToken);

            // Если аутентификация прошла успешно, сохраняем пользователя в контексте безопасности
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Перенаправляем на главную страницу
            return "redirect:/"; // Главная страница

        } catch (Exception e) {
            // Если аутентификация не удалась, возвращаем ошибку на страницу логина
            model.addAttribute("error", "Invalid username or password");
            return "login"; // Возвращаем страницу логина с ошибкой
        }
    }
}
