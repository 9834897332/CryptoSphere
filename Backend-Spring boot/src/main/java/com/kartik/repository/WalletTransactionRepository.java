package com.kartik.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kartik.domain.WalletTransactionType;
import com.kartik.model.Wallet;
import com.kartik.model.WalletTransaction;

import java.util.List;

public interface WalletTransactionRepository extends JpaRepository<WalletTransaction,Long> {

    List<WalletTransaction> findByWalletOrderByDateDesc(Wallet wallet);

}
