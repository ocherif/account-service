package com.services.pm.product.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
public class Product extends Object {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    private Integer rating;

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || this.getClass() != obj.getClass()) return false;

        Product product = (Product) obj;

        return /*(id == product.id) && */
                (name.equals(product.name))
                        && (price.compareTo((product.price)) == 0);
    }

    @Override
    public int hashCode() {
        return /*id.hashCode() * */ name.hashCode() * price.hashCode();
    }

    public static class ProductBuilder {

        private long id;

        private String name;

        private double price;

        private int rating;

        public ProductBuilder() {
        }

        public ProductBuilder(long id) {
            this.id = id;
        }

        public ProductBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder withPrice(double price) {
            this.price = price;
            return this;
        }

        public ProductBuilder withRating(int rating) {
            this.rating = rating;
            return this;
        }

        public Product build() {
            Product product = new Product();
            product.setName(this.name);
            product.setPrice(this.price);
            product.setRating(this.rating);
            return product;
        }
    }
}
