package com.example.custommetricsdemo.controller;

import com.example.custommetricsdemo.infrastructure.Order;
import com.example.custommetricsdemo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders")
public class OrderController {
    private OrderService service;

    @Autowired
    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    ResponseEntity<OrderResponse> addOrder(@RequestBody OrderRequest request) {
        Order order = service.createOrder(request.itemName());
        return ResponseEntity.ok(new OrderResponse(order));
    }
}
