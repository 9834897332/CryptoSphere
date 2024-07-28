package com.kartik.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kartik.model.VerificationCode;

public interface VerificationRepository extends JpaRepository<VerificationCode,Long> {
    VerificationCode findByUserId(Long userId);
}
