package com.kartik.request;

import lombok.Data;

import java.math.BigDecimal;

import com.kartik.domain.OrderType;
import com.kartik.model.Coin;


@Data
public class CreateOrderRequest {
    private String coinId;
    private double quantity;
    private OrderType orderType;
}
