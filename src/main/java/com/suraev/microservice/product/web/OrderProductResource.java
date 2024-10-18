package com.suraev.microservice.product.web;

import com.suraev.microservice.product.domain.DTO.Order;
import com.suraev.microservice.product.domain.Product;
import com.suraev.microservice.product.service.OrderContainsProductService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/v1")
public class OrderProductResource {

    private final Logger log = LoggerFactory.getLogger(OrderProductResource.class);
    private final OrderContainsProductService orderContainsProductService;

    public OrderProductResource(OrderContainsProductService orderContainsProductService) {
        this.orderContainsProductService = orderContainsProductService;
    }

    @PostMapping("/checkProducts")
    public ResponseEntity<Boolean> isProductsExist(@RequestBody Order order) {
        Set<@Valid Product> products = order.getProducts();
        return ResponseEntity.ok(orderContainsProductService.containsProducts(products));
    }
}
