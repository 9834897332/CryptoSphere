package com.kartik.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kartik.model.Coin;

public interface CoinRepository extends JpaRepository<Coin,String> {
}
