package org.example.nobs.controller;

import org.example.nobs.entity.Order;
import org.example.nobs.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;
    @PostMapping
    public ResponseEntity createOrder (){
        Order order = new Order();
        order.setId(UUID.randomUUID());
        order.setTotal(19.18);
        orderRepository.save(order);
        return ResponseEntity.ok(order);
    }
}
