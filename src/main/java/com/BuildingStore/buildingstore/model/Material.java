package com.BuildingStore.buildingstore.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "material_type", discriminatorType = DiscriminatorType.STRING)
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Автоматическая генерация ID
    private String name;
    private String description;
    private double price;
    private double weight;

    public Material() { // Конструктор по умолчанию
    }

    public Material(Long id, String name, String description, double price, double weight) {
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