package org.example.nobs.dto;

import lombok.Data;
import org.example.nobs.entity.Product;

@Data
public class ProductDto {
    private String name;
    private Double price;
    public ProductDto(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
    }
}
