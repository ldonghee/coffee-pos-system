package com.dhlee.coffeepossystem.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dhlee.coffeepossystem.order.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
