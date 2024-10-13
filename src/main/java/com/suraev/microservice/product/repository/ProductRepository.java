package com.suraev.microservice.product.repository;

import com.suraev.microservice.product.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
