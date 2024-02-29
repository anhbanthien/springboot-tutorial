package org.example.nobs.repo;

import org.example.nobs.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order,UUID> {
}
