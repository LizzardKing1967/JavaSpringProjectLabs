package com.BuildingStore.buildingstore.materialRepository;

import com.BuildingStore.buildingstore.model.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<CustomerOrder, Long> {

}