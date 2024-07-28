package com.kartik.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kartik.model.Wallet;

public interface WalletRepository extends JpaRepository<Wallet,Long> {

    public Wallet findByUserId(Long userId);


}
