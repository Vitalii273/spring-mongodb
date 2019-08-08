package com.telran.springmongodb.data;

import com.telran.springmongodb.data.document.CatalogCar;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarCatalogRepository extends MongoRepository<CatalogCar, ObjectId> {
}
