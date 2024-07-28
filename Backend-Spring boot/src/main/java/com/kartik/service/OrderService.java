package com.kartik.service;

import java.util.List;

import com.kartik.domain.OrderType;
import com.kartik.model.Coin;
import com.kartik.model.Order;
import com.kartik.model.OrderItem;
import com.kartik.model.User;
import com.kartik.request.CreateOrderRequest;

public interface OrderService {

    Order createOrder(User user, OrderItem orderItem, OrderType orderType);

    Order getOrderById(Long orderId);

    List<Order> getAllOrdersForUser(Long userId, String orderType,String assetSymbol);

    void cancelOrder(Long orderId);

//    Order buyAsset(CreateOrderRequest req, Long userId, String jwt) throws Exception;

    Order processOrder(Coin coin, double quantity, OrderType orderType, User user) throws Exception;

//    Order sellAsset(CreateOrderRequest req,Long userId,String jwt) throws Exception;


}
