package org.example.nobs.query.queryhandler;

import org.example.nobs.dto.ProductDto;
import org.example.nobs.entity.Product;
import org.example.nobs.query.Query;
import org.example.nobs.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllProductsQueryHandler implements Query<Void, List<ProductDto>> {

    @Autowired
    ProductRepo productRepo;

    @Override
    public ResponseEntity<List<ProductDto>> execute(Void input) {
        List<ProductDto> productDto = productRepo.findAll().stream().map(ProductDto::new).toList();
        return ResponseEntity.ok(productDto);
    }
}
