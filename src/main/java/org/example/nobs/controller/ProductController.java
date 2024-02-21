package org.example.nobs.controller;

import org.example.nobs.entity.Product;
import org.example.nobs.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductRepo productRepo;

   // Create , Read , Update ,Delete
    // post  , get ,  put   ,delete
    @GetMapping("/products")
    public String getProducts (){
        return "get products endpoint";
    }
    @GetMapping("/getAllProducts")
    public ResponseEntity <List<Product>> getAllProducts (){
        return ResponseEntity.ok(productRepo.findAll());
    }
    @GetMapping("/product/{id}")
    public ResponseEntity <Optional<Product>> getProduct (@PathVariable Integer id){
        return ResponseEntity.ok(productRepo.findById(id));
    }
    @PostMapping ("create-product")
    public ResponseEntity<Product> createProduct (@RequestBody Product product ) {
        productRepo.save(product);
        return ResponseEntity.ok(product);
    }
    @PutMapping ("/product/{id}")
    public ResponseEntity<Product> updateProduct (@PathVariable Integer id , @RequestBody Product product){
         product.setId(id);
         productRepo.save(product);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity deleteProduct (@PathVariable Integer id){
        productRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
