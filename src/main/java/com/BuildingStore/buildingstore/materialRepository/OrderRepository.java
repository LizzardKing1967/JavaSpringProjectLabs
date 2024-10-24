package com.BuildingStore.buildingstore.materialRepository;

import com.BuildingStore.buildingstore.model.CustomerOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<CustomerOrder, String> {

}