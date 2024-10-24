package com.BuildingStore.buildingstore.model;

import java.time.LocalDate;

    // Конструктор
public class OrderDTO {
    private Long orderId; // ID заказа
    private String materialName; // Название материала
    private String materialDescription; // Описание материала
    private String customerName;
    private String adress;
    private int quantity; // Количество

    // Конструктор
    public OrderDTO(Long orderId, String materialName, String materialDescription, String customerName, String adress, int quantity) {
        this.orderId = orderId;
        this.materialName = materialName;
        this.materialDescription = materialDescription;
        this.quantity = quantity;
        this.customerName = customerName;
        this.adress = adress;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getAdress() {
        return adress;
    }
    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialDescription() {
        return materialDescription;
    }

    public void setMaterialDescription(String materialDescription) {
        this.materialDescription = materialDescription;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}