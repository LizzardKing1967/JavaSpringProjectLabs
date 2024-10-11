package com.BuildingStore.buildingstore.model;

import lombok.Data;

@Data
public class MetalSheet extends Material {

    private double thickness;
    public MetalSheet(long id,String name, String description, double price, double weight, double thickness) {
        super(id ,name, description, price, weight);
    }
    public double getThickness() {
        return thickness;
    }
}
