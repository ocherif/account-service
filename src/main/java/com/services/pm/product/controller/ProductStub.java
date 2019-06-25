package com.services.pm.product.controller;

import com.services.pm.product.model.Product;
import com.services.pm.product.model.enumeration.RatingValues;

import java.util.Arrays;
import java.util.List;

public class ProductStub {

    private static List<Product> products;

    static {
        Product product1 = new Product.ProductBuilder(1)
                .withName("Iphone 5")
                .withPrice(500)
                .withRating(RatingValues.FOUR.getValue())
                .build();

        Product product2 = new Product.ProductBuilder(1)
                .withName("Samsung S5")
                .withPrice(450)
                .withRating(RatingValues.THREE.getValue())
                .build();

        products = Arrays.asList(new Product [] {product1, product2});
    }

    protected static List<Product> getProducts(){
        return products;
    }
}
