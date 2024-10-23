package com.BuildingStore.buildingstore.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@DiscriminatorValue("MetalSheet")  // Значение для столбца discriminator
public class MetalSheetMaterial extends Material {

    private double thickness;
    public MetalSheetMaterial(Long id, String name, String description, double price, double weight, double thickness) {
        super(id ,name, description, price, weight);
    }

    public MetalSheetMaterial()
    {

    }
    public double getThickness() {
        return thickness;
    }
}
