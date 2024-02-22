package org.example.nobs.command.productupdate;

import lombok.Data;
import org.example.nobs.entity.Product;
@Data
public class ProductUpdate {
    private int id;
    private Product product;

    public ProductUpdate(int id, Product product) {
        this.id = id;
        this.product = product;
    }
}
