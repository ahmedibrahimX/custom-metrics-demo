package com.example.custommetricsdemo.service;

import com.example.custommetricsdemo.infrastructure.Order;
import com.example.custommetricsdemo.infrastructure.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepo repo;

    @Autowired
    public OrderService(OrderRepo repo) {
        this.repo = repo;
    }

    public Order createOrder(String itemName) {
        return repo.save(new Order(itemName));
    }
}
