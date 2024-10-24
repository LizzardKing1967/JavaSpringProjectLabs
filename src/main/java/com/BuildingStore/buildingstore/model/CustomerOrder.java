package com.BuildingStore.buildingstore.model;

import com.BuildingStore.buildingstore.materialRepository.MaterialRepository;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Document(collection = "customer_orders")  // Аннотация для работы с коллекцией MongoDB
@Data
public class CustomerOrder {

    @Id
    private String id; // Автоматически генерируемый ID для MongoDB

    @NotNull(message = "Материал должен быть выбран")
    private String orderMaterialId; // ID материала, хранящийся как строка в MongoDB

    @NotNull(message = "Количество должно быть указано")
    @Positive(message = "Количество должно быть положительным")
    private int quantity;

    @NotEmpty(message = "Введите ФИО")
    private String customerName;

    @NotEmpty(message = "Введите адрес")
    private String address;

    // Метод для расчета общей стоимости заказа
    public double calculateTotalPrice(MaterialRepository materialRepository) {
        Material material = materialRepository.findById(orderMaterialId)
                .orElseThrow(() -> new RuntimeException("Material not found"));
        return material.getPrice() * quantity;
    }
}
