package org.example.nobs.command.commandhandler;

import io.micrometer.common.util.StringUtils;
import org.example.nobs.command.Command;
import org.example.nobs.entity.Product;
import org.example.nobs.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateProductCommand implements Command<Product, ResponseEntity> {
    @Autowired
    private ProductRepo productRepo;
    @Override
    public ResponseEntity<ResponseEntity> execute(Product product) {
        validationProduct(product);
        productRepo.save(product);
        return ResponseEntity.ok().build();
    }
    private void validationProduct(Product product) {
        if (StringUtils.isBlank(product.getName())) {
            throw new RuntimeException("Name cant be empty");
        } else if (product.getPrice() <= 0.0) {
            throw new RuntimeException("Price cant be negative");
        }
    }
}
