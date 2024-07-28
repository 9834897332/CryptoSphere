package com.kartik.service;

import com.kartik.model.CoinDTO;
import com.kartik.response.ApiResponse;

public interface ChatBotService {
    ApiResponse getCoinDetails(String coinName);

    CoinDTO getCoinByName(String coinName);

    String simpleChat(String prompt);
}
