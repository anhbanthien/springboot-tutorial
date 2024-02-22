package org.example.nobs.queryhandler;

import org.example.nobs.entity.Product;
import org.example.nobs.query.Query;
import org.example.nobs.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetProductQueryHandler implements Query<Integer, Product> {

    @Autowired
    private ProductRepo productRepo;
    @Override
    public ResponseEntity<Product> execute(Integer input) {
        Optional<Product> product = productRepo.findById(input);
        if (product.isEmpty()){
            // throw an exception
            throw new RuntimeException("product not found");
        }
        return ResponseEntity.ok(product.get());
    }
}
