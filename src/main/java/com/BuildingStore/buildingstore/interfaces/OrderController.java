package com.BuildingStore.buildingstore.interfaces;
import com.BuildingStore.buildingstore.materialRepository.MaterialRepository;
import com.BuildingStore.buildingstore.materialRepository.OrderRepository;
import com.BuildingStore.buildingstore.model.Material;
import com.BuildingStore.buildingstore.model.CustomerOrder;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/order/submit")
    public String submitOrder(@Valid @ModelAttribute("order") CustomerOrder order, Errors errors, Model model) {
        Material material = materialRepository.findById(order.getOrderMaterialId()).orElse(null); // Получаем материал по ID

        if (errors.hasErrors()) {
            model.addAttribute("order", order);
            model.addAttribute("material", material);
            return "order-form"; // Возвращаем на форму, если есть ошибки
        }

        // Здесь можно добавить логику для сохранения заказа, например, в базу данных
        orderRepository.save(order); // Псевдокод для сохранения заказа

        return "redirect:/"; // Перенаправление после успешного заказа
    }
}

