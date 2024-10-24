package com.BuildingStore.buildingstore.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "materials")

public class MetalSheetMaterial extends Material {

    private double thickness;
    public MetalSheetMaterial(String id, String name, String description, double price, double weight, double thickness) {
        super(id ,name, description, price, weight);
    }

    public MetalSheetMaterial()
    {

    }
    public double getThickness() {
        return thickness;
    }
}
