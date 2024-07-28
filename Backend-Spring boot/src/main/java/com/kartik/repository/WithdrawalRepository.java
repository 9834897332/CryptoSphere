package com.kartik.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kartik.domain.WithdrawalStatus;
import com.kartik.model.Withdrawal;

import java.util.List;

public interface WithdrawalRepository extends JpaRepository<Withdrawal,Long> {
    List<Withdrawal> findByUserId(Long userId);
}
