package com.BuildingStore.buildingstore.materialRepository;

import com.BuildingStore.buildingstore.model.Material;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
}
