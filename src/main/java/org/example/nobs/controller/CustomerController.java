package org.example.nobs.controller;

import org.example.nobs.entity.Customer;
import org.example.nobs.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerRepo customerRepo;
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerByID (@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(customerRepo.findById(id).get());
    }
    @PostMapping
    public  ResponseEntity<Customer> insertCustomer (@RequestBody Customer customer){
        customerRepo.save(customer);
        return ResponseEntity.ok().build();
    }


}
