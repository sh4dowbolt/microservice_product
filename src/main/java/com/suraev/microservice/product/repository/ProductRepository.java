package com.suraev.microservice.product.repository;

import com.suraev.microservice.product.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String>, QueryByExampleExecutor<Product> {

}
