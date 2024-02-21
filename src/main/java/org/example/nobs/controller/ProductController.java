package org.example.nobs.controller;

import org.example.nobs.entity.Product;
import org.example.nobs.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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





}
