package com.services.pm.product.repository;

import com.services.pm.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAll();
    Product getOne(Long id);
    //void delete(Product product);
    void deleteById(Long id);
    Product save(Product product);
    //Optional<Product> findById(Long id);
}
