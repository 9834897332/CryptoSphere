package com.kartik.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kartik.model.PaymentOrder;

public interface PaymentOrderRepository extends JpaRepository<PaymentOrder,Long> {
}
