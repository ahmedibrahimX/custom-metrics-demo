package com.example.custommetricsdemo.controller;

import com.example.custommetricsdemo.infrastructure.Order;

public record OrderResponse(
        Long id,
        String itemName
) {
    public OrderResponse(Order order) {
       this(order.getId(), order.getItemName());
    }
}