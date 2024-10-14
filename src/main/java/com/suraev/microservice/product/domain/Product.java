package com.suraev.microservice.product.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Schema(description = "Сущность продукта")
@Document(collection = "product")
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    private String id;

    @Field("product_title")
    private String title;

    @Field("created_at")
    @CreatedDate
    private Instant createdAt;

    @Field("updated_at")
    @LastModifiedDate
    private Instant updatedAt;

    @Field("product_details")
    private String productDetails;

    @Field("amount")
    private int amount;

    //TODO: сделать загрузку изображения для продукта
    @Field("image")
    private String image;

}
