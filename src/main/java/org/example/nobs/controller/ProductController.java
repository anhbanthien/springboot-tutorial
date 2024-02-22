package org.example.nobs.controller;

import org.example.nobs.command.commandhandler.CreateProductCommand;
import org.example.nobs.command.commandhandler.DeleteProductCommand;
import org.example.nobs.command.commandhandler.UpdateProductCommand;
import org.example.nobs.command.productupdate.ProductUpdate;
import org.example.nobs.entity.Product;
import org.example.nobs.query.queryhandler.GetAllProductsQueryHandler;
import org.example.nobs.query.queryhandler.GetProductQueryHandler;
import org.example.nobs.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired private ProductRepo productRepo;
    @Autowired private GetAllProductsQueryHandler getAllProductsQueryHandler;
    @Autowired private GetProductQueryHandler getProductQueryHandler;
    @Autowired private CreateProductCommand createProductCommand;
    @Autowired private UpdateProductCommand updateProductCommand;
    @Autowired private DeleteProductCommand deleteProductCommand;

   // Create , Read , Update ,Delete
    // post  , get ,  put   ,delete
    @GetMapping("/products")
    public String getProducts (){
        return "get products endpoint";
    }
    @GetMapping("/getAllProducts")
    public ResponseEntity <List<Product>> getAllProducts (){
        return getAllProductsQueryHandler.execute(null);
    }
    @GetMapping("/product/{id}")
    public ResponseEntity <Product> getProduct (@PathVariable Integer id){
            return getProductQueryHandler.execute(id);
    }
    @PostMapping ("create-product")
    public ResponseEntity createProduct (@RequestBody Product product ) {
        return createProductCommand.execute(product);
    }
    @PutMapping ("/product/{id}")
    public ResponseEntity updateProduct (@PathVariable Integer id , @RequestBody Product product){
        ProductUpdate productUpdate = new ProductUpdate(id,product);
         return updateProductCommand.execute(productUpdate);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity deleteProduct (@PathVariable Integer id){
       return deleteProductCommand.execute(id);

    }


}
