package org.example.nobs.command.commandhandler;

import org.example.nobs.command.Command;
import org.example.nobs.command.productupdate.ProductUpdate;
import org.example.nobs.entity.Product;
import org.example.nobs.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UpdateProductCommand implements Command<ProductUpdate,ResponseEntity> {

    @Autowired
    private ProductRepo productRepo;

    @Override
    //@CacheEvict(value = "productCache",key = "#productUpdate.getId()")
    @CachePut(value = "productCache",key = "#productUpdate.getId()")
    public ResponseEntity<ResponseEntity> execute(ProductUpdate productUpdate) {
        Product product = productUpdate.getProduct();
        product.setId(productUpdate.getId());
        productRepo.save(product);
        return ResponseEntity.ok().build();
    }
}
