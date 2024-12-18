package com.BuildingStore.buildingstore.interfaces;
import com.BuildingStore.buildingstore.materialRepository.MaterialRepository;
import com.BuildingStore.buildingstore.materialRepository.OrderRepository;
import com.BuildingStore.buildingstore.model.Material;
import com.BuildingStore.buildingstore.model.CustomerOrder;
import com.BuildingStore.buildingstore.model.OrderDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {

    private final MaterialRepository materialRepository;
    private final OrderRepository orderRepository;
    @Autowired
    public OrderController(MaterialRepository materialRepository, OrderRepository orderRepository) {
        this.materialRepository = materialRepository;
        this.orderRepository = orderRepository;
    }

    // Метод для отображения формы заказа с выбранным материалом
    @GetMapping("/order/new/{id}")
    public String showOrderForm(@PathVariable("id") Long materialId, Model model) {
        Material selectedMaterial = materialRepository.findById(materialId).orElse(null); // Получаем материал по ID
        CustomerOrder order = new CustomerOrder();

        if (selectedMaterial != null) {
            order.setOrderMaterialId(materialId); // Устанавливаем ID материала в заказ
            model.addAttribute("material", selectedMaterial);
        } else {
            // Обработка случая, когда материал не найден
            model.addAttribute("error", "Material not found");
            return "error"; // Предполагаем, что есть страница ошибки
        }

        model.addAttribute("order", order);
        return "order-form"; // Возвращаем страницу с формой заказа
    }
    @ModelAttribute("order")
    public CustomerOrder populateOrderWithUsername(CustomerOrder order) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        order.setUsername(currentUsername);
        return order;
    }
    @PostMapping("/order/submit")
    public String submitOrder(@Valid @ModelAttribute("order") CustomerOrder order, Errors errors, Model model) {
        Material material = materialRepository.findById(order.getOrderMaterialId()).orElse(null);
        orderRepository.save(order); // Сохраняем заказ
        if (errors.hasErrors()) {
            model.addAttribute("order", order);
            model.addAttribute("material", material);
            return "order-form"; // Возвращаем на форму с ошибками
        }
        return "redirect:/order-list"; // Перенаправляем на список заказов
    }

    @GetMapping("/order-list")
    public String showOrders(Model model) {
        // Получаем текущего пользователя
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        // Фильтруем заказы по пользователю
        List<CustomerOrder> orders = orderRepository.findByUsername(currentUsername);
        List<OrderDTO> orderDTOs = new ArrayList<>();
        for (CustomerOrder order : orders) {
            Material material = materialRepository.findById(order.getOrderMaterialId()).orElse(null);
            if (material != null) {
                orderDTOs.add(new OrderDTO(
                        order.getId(),
                        material.getName(),
                        material.getDescription(),
                        order.getCustomerName(),
                        order.getAddress(),
                        order.getQuantity()
                ));
            }
        }
        model.addAttribute("orders", orderDTOs);
        return "order-list";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, response, null);
        return "redirect:/login?logout";
    }

}

