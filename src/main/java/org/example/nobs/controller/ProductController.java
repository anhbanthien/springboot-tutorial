package org.example.nobs.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductController {
   // Create , Read , Update ,Delete
    // post  , get ,  put   ,delete
    @GetMapping("/products")
    public String getProducts (){
        return "get products endpoint";
    }

}
