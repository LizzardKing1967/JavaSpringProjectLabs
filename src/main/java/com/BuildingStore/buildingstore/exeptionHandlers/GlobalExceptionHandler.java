package com.BuildingStore.buildingstore.exeptionHandlers;

import org.springframework.core.annotation.Order;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@Order(1)
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public String handleUsernameNotFoundException(UsernameNotFoundException ex, Model model) {
        // Передаём сообщение об ошибке в модель
        model.addAttribute("Пользователя с таким логином нет в системе!", ex.getMessage());
        // Возвращаем страницу логина с сообщением
        return "login";
    }
}