package org.example.nobs.command.commandhandler;

import org.example.nobs.command.Command;

import org.example.nobs.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeleteProductCommand implements Command<Integer,ResponseEntity> {
    @Autowired
    ProductRepo productRepo;
    @Override
    public ResponseEntity<ResponseEntity> execute(Integer entity) {
        productRepo.deleteById(entity);
        return ResponseEntity.ok().build();
    }
}
