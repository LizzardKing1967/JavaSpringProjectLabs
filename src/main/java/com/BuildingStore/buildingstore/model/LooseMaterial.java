package com.BuildingStore.buildingstore.model;


import jakarta.persistence.*;

@Entity
@DiscriminatorValue("LooseMaterial")  // Значение для столбца discriminator
public class LooseMaterial extends Material {

    public LooseMaterial(Long id, String name, String description, double price, double weight) {
        super(id, name, description, price, weight);
    }

    public LooseMaterial()
    {

    }
}