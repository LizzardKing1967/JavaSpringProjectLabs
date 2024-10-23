package com.BuildingStore.buildingstore.model;

import lombok.Data;

import jakarta.persistence.*;

@Entity

@DiscriminatorValue("Brick")
public class BrickMaterial extends Material {
    public enum BrickType {
        RED, WHITE, FIREPROOF
    }

    @Enumerated(EnumType.STRING)
    private BrickType brickType;
    public BrickMaterial(Long id, String name, String description, double price, double weight, BrickType brickType) {
        super(id, name, description, price, weight);
    }

    public BrickMaterial()
    {

    }
    public BrickType getBrickType() {
        return brickType;
    }

}
