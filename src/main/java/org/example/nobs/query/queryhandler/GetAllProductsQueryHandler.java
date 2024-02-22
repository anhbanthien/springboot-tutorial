package org.example.nobs.query.queryhandler;

import org.example.nobs.entity.Product;
import org.example.nobs.query.Query;
import org.example.nobs.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllProductsQueryHandler implements Query<Void, List<Product>> {

    @Autowired
    ProductRepo productRepo;

    @Override
    public ResponseEntity<List<Product>> execute(Void input) {
        return ResponseEntity.ok(productRepo.findAll());
    }
}
