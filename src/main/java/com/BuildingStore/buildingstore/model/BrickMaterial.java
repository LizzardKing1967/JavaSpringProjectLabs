package com.BuildingStore.buildingstore.model;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "materials")
public class BrickMaterial extends Material {
    public enum BrickType {
        RED, WHITE, FIREPROOF
    }
    private BrickType brickType;
    public BrickMaterial(String id, String name, String description, double price, double weight, BrickType brickType) {
        super(id, name, description, price, weight);
    }

    public BrickMaterial()
    {

    }
    public BrickType getBrickType() {
        return brickType;
    }

}
