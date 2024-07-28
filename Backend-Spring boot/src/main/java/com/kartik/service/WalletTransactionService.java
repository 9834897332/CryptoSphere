package com.kartik.service;

import java.util.List;

import com.kartik.domain.WalletTransactionType;
import com.kartik.model.Wallet;
import com.kartik.model.WalletTransaction;

public interface WalletTransactionService {
    WalletTransaction createTransaction(Wallet wallet,
                                        WalletTransactionType type,
                                        String transferId,
                                        String purpose,
                                        Long amount
    );

    List<WalletTransaction> getTransactions(Wallet wallet, WalletTransactionType type);

}
