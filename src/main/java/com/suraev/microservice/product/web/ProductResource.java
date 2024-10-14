package com.suraev.microservice.product.web;

import com.suraev.microservice.product.domain.Product;
import com.suraev.microservice.product.exception.BadRequestAlertException;
import com.suraev.microservice.product.repository.ProductRepository;
import com.suraev.microservice.product.util.HeaderUtil;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class ProductResource {
    private final Logger log = LoggerFactory.getLogger(ProductResource.class);
    private static final String ENTITY_NAME = "product";
    @Value("${spring.application.name}")
    private String applicationName;

    private final ProductRepository productRepository;

    public ProductResource(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(@Valid Product product) throws URISyntaxException {
        log.debug("REST request to Save Product:{}", product);
        if(product.getId() != null) {
            throw new BadRequestAlertException("A new product haven't already an ID",ENTITY_NAME,"id_exist");
        }

        final Product result = productRepository.save(product);
        HttpHeaders headers = HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, product.getId());

        return  ResponseEntity.created(new URI("/api/v1/products/"+result.getId())).header(String.valueOf(headers)).body(result);
    }
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        log.debug("REST request to get Product: {}", id);
        var result = productRepository.findById(id);
        if(result.isPresent()) {
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/product")
    public List<Product> getAllProduct() {
        log.debug("REST request to get all products");
        return productRepository.findAll();

    }
    @PutMapping
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product product) {
        log.debug("REST request to Update Product: {}",product);
        if(product.getId() == null) {
            throw new BadRequestAlertException("An existing product should have an id", ENTITY_NAME,"id_non_exist");
        }

        var result = productRepository.save(product);
        HttpHeaders headers = HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, product.getId());

        return ResponseEntity.ok().headers(headers).body(result);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productRepository.deleteById(id);

        var headers = HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id);

        return ResponseEntity.noContent().headers(headers).build();
    }
}
