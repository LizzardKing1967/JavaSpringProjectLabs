package com.BuildingStore.buildingstore.model;

import com.BuildingStore.buildingstore.materialRepository.MaterialRepository;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Материал должен быть выбран")
    private Long orderMaterialId; // ID материала

    @NotNull(message = "Количество должно быть указано")
    @Positive(message = "Количество должно быть положительным")
    private int quantity;

    @NotEmpty(message = "Введите ФИО")
    private String customerName;

    @NotEmpty(message = "Введите адрес")
    private String address;
    public double calculateTotalPrice(MaterialRepository materialRepository) {
        Material material = materialRepository.findById(orderMaterialId).orElseThrow(() -> new RuntimeException("Material not found"));
        return material.getPrice() * quantity;
    }
}