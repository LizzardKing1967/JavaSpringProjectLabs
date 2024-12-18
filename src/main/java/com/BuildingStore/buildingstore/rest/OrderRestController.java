package com.BuildingStore.buildingstore.rest;

import com.BuildingStore.buildingstore.materialRepository.MaterialRepository;
import com.BuildingStore.buildingstore.materialRepository.OrderRepository;
import com.BuildingStore.buildingstore.model.Material;
import com.BuildingStore.buildingstore.model.CustomerOrder;
import com.BuildingStore.buildingstore.model.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderRestController {

    private final MaterialRepository materialRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderRestController(MaterialRepository materialRepository, OrderRepository orderRepository) {
        this.materialRepository = materialRepository;
        this.orderRepository = orderRepository;
    }

    // Получить список всех заказов в формате JSON
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<OrderDTO>> getOrders(@RequestParam String username) {
        List<CustomerOrder> orders = orderRepository.findByUsername(username);
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
        return ResponseEntity.ok(orderDTOs);
    }

    // Получить конкретный заказ по ID
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        CustomerOrder order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        Material material = materialRepository.findById(order.getOrderMaterialId()).orElse(null);
        if (material == null) {
            return ResponseEntity.badRequest().build();
        }
        OrderDTO orderDTO = new OrderDTO(
                order.getId(),
                material.getName(),
                material.getDescription(),
                order.getCustomerName(),
                order.getAddress(),
                order.getQuantity()
        );
        return ResponseEntity.ok(orderDTO);
    }


    // Создать новый заказ (принимает JSON, возвращает JSON)
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<CustomerOrder> createOrder(@RequestBody CustomerOrder order) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        order.setUsername(currentUsername);
        Material material = materialRepository.findById(order.getOrderMaterialId()).orElse(null);
        if (material == null) {
            return ResponseEntity.badRequest().build();
        }
        CustomerOrder savedOrder = orderRepository.save(order);

        return ResponseEntity.status(201).body(savedOrder);
    }

    // Обновить существующий заказ
    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CustomerOrder> updateOrder(@PathVariable Long id, @RequestBody CustomerOrder updatedOrder) {
        if (!orderRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        updatedOrder.setId(id);
        CustomerOrder savedOrder = orderRepository.save(updatedOrder);
        return ResponseEntity.ok(savedOrder);
    }

    // Удалить заказ
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        if (!orderRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        orderRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

