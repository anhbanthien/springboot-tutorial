package org.example.nobs.repo;

import org.example.nobs.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,Integer> {

    //QUERY WRITE OUR OWN SQL STATEMENT
    @Query("SELECT p from Product p where p.name like %:name% ")
    List<Product> customQueryMethod(@Param(value = "name")String name);

    List<Product> searchProductByName (String name);

}
