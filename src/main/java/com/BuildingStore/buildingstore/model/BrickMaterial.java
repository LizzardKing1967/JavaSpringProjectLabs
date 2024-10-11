package com.BuildingStore.buildingstore.model;

import lombok.Data;

@Data
public class BrickMaterial extends Material {
    public enum BrickType {
        RED, WHITE, FIREPROOF
    }

    private BrickType brickType;
    public BrickMaterial(Long id, String name, String description, double price, double weight, BrickType brickType) {
        super(id, name, description, price, weight);
    }
    public BrickType getBrickType() {
        return brickType;
    }
}
