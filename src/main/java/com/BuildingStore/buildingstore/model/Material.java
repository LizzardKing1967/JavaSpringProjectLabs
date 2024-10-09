package com.BuildingStore.buildingstore.model;

public class Material {
    private String name;
    private String description;
    private double price;

    public Material(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    // Геттеры
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Material{name='" + name + "', description=" + description + ", price" + price + "}";
    }
}
