package com.kartik.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kartik.model.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    public List<Order>findByUserId(Long userId);
}
