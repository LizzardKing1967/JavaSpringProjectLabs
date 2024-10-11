package com.BuildingStore.buildingstore.model;

import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


@Data
public class Order {
    @NotNull(message = "Материал должен быть выбран")
    private Long orderMaterialId; // Объект материала
    @NotNull(message = "Количество должно быть указано")
    @Positive(message = "Количество должно быть положительным")
    private int quantity;


    @NotEmpty(message = "Введите ФИО")
    private String customerName;

    @NotEmpty(message = "Введите адрес")
    private String address;

    // Геттеры и сеттеры


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Метод для вычисления общей стоимости заказа
    public Long GetId()
    {
        return orderMaterialId;
    }

    public void setOrderMaterialId(Long materialId) {
        this.orderMaterialId = materialId;
    }
}