package com.practice.repositories;

import com.practice.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserUserId(Long userId);

    Optional<List<Order>> findByProductProductId(Long productId);
}
