package com.services.pm.product.service;

import com.services.pm.product.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();
    Product findById(Long id);
    void deleteById(Long id);
    Product save(Product product);
}
