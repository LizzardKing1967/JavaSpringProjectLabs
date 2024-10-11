package com.BuildingStore.buildingstore.interfaces;
import com.BuildingStore.buildingstore.materialRepository.MaterialRepository;
import com.BuildingStore.buildingstore.model.Material;
import com.BuildingStore.buildingstore.model.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import jakarta.validation.Valid;

@Controller
public class OrderController {

    private final MaterialRepository materialRepository;

    public OrderController(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    // Метод для отображения формы заказа с выбранным материалом
    @GetMapping("/order/new/{id}")
    public String showOrderForm(@PathVariable("id") Long materialId, Model model) {
        Material selectedMaterial = materialRepository.findById(materialId); // Получаем материал по ID
        Order order = new Order();
        model.addAttribute("material", selectedMaterial);
        order.setOrderMaterialId(materialId);
        model.addAttribute("order", order);
        return "order-form"; // Возвращаем страницу с формой заказа
    }

    @PostMapping("/order/submit")
    public String submitOrder(@Valid @ModelAttribute("order") Order order, Errors errors, Model model) {
        Material material = materialRepository.findById(order.GetId());
        if (errors.hasErrors()) {
            model.addAttribute("order", order);
            model.addAttribute("material", material);
            return "order-form"; // Возвращаем на форму, если есть ошибки
        }

        // Загружаем материал по ID


        return "index"; // Перенаправление после успешного заказа
    }
}

