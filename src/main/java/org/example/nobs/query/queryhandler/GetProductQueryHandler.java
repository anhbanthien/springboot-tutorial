package org.example.nobs.query.queryhandler;

import org.example.nobs.dto.ProductDto;
import org.example.nobs.entity.Product;
import org.example.nobs.query.Query;
import org.example.nobs.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetProductQueryHandler implements Query<Integer, ProductDto> {

    @Autowired
    private ProductRepo productRepo;
    @Override
    @Cacheable("productCache")
    public ResponseEntity<ProductDto> execute(Integer input) {
        Optional<Product> product = productRepo.findById(input);
        ProductDto productDto = new ProductDto(product.get());
        if (product.isEmpty()){
            // throw an exception
            throw new RuntimeException("product not found");
        }
        return ResponseEntity.ok(productDto);
    }
}
