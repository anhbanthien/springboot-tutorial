package org.example.nobs.command;

import org.example.nobs.command.productupdate.ProductUpdate;
import org.example.nobs.entity.Product;
import org.springframework.http.ResponseEntity;

public interface Command <E,T>{
    ResponseEntity<T> execute(E entity);

    ResponseEntity<ResponseEntity> execute(ProductUpdate productUpdate);
}
