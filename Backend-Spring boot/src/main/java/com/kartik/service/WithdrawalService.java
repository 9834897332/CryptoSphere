package com.kartik.service;

import lombok.With;

import java.util.List;

import com.kartik.model.User;
import com.kartik.model.Withdrawal;

public interface WithdrawalService {

    Withdrawal requestWithdrawal(Long amount,User user);
    Withdrawal procedWithdrawal(Long withdrawalId,boolean accept) throws Exception;
    List<Withdrawal> getUsersWithdrawalHistory(User user);
    List<Withdrawal> getAllWithdrawalRequest();
}
