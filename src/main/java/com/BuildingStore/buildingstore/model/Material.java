package com.BuildingStore.buildingstore.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Document(collection = "materials")
@Data
public class Material {
    @Id
    private String  id; // Автоматическая генерация ID
    private String name;
    private String description;
    private double price;
    private double weight;

    public Material() { // Конструктор по умолчанию
    }

    public Material(String id, String name, String description, double price, double weight) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Material{id=" + id + ", name='" + name + "', description='" + description + "', price=" + price + "}";
    }
}