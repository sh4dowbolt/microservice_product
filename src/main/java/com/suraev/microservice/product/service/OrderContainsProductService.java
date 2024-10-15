package com.suraev.microservice.product.service;

import com.suraev.microservice.product.domain.Product;
import com.suraev.microservice.product.repository.ProductRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderContainsProductService {
    private final ProductRepository productRepository;

    public OrderContainsProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Boolean containsProducts(Set<Product> list) {
        List<String> collect = list.stream().map(Product::getId).toList();

        List<Product> allById = productRepository.findAllById(collect);

        return new HashSet<>(allById).containsAll(collect);

    }
}
