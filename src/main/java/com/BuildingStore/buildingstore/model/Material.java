package com.BuildingStore.buildingstore.model;

import lombok.Data;

/*import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import javax.persistence.Id;*/

@Data
public class Material {
    private Long id; // Поле id для автоматической генерации

    private String name;
    private String description;
    private double price;

    private double weight;

    public Material(Long id,String name, String description, double price, double weight) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.weight = weight;
        this.id = id;
    }

    // Геттеры
    public Long getId() {
        return id; // Геттер для поля id
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Material{id=" + id + ", name='" + name + "', description='" + description + "', price=" + price + "}";
    }
}
