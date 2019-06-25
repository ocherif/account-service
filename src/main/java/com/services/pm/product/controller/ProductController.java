package com.services.pm.product.controller;

import com.services.pm.product.model.Product;
import com.services.pm.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1")
@CrossOrigin("http://localhost:4200")
public class ProductController {

    @Autowired
    IProductService productService;

    //@RequestMapping(value = "products", method = RequestMethod.GET)
    @GetMapping(value = "/products/{id}")
    Product productList(@PathVariable Long id) {
        return productService.findById(id);
    }

    @GetMapping(value = "/products")
    List<Product> productList() {
        return productService.findAll();
    }

    @DeleteMapping(value = "/products/{id}")
    void removeProduct(@PathVariable Long id){
        productService.deleteById(id);
    }

    @PostMapping(value = "/products")
    public ResponseEntity<Object> saveProduct(@RequestBody Product product){
        Product savedProduct = productService.save(product);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedProduct.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}
