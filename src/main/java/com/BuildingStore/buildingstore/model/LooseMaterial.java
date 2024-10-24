package com.BuildingStore.buildingstore.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "materials")
public class LooseMaterial extends Material {

    public LooseMaterial(String id, String name, String description, double price, double weight) {
        super(id, name, description, price, weight);
    }

    public LooseMaterial()
    {

    }
}