package com.example.demo.controller;

import com.example.demo.domain.Order;
import com.example.demo.domain.OrderItem;
import com.example.demo.dto.requests.order_requests.CreateOrderRequest;
import com.example.demo.dto.responses.order_responses.OrderResponse;
import com.example.demo.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/orders")
@Validated
public class OrderController{

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse createOrder(@RequestBody @Validated CreateOrderRequest request){
        Order order = orderService.createOrder(request);
        return new OrderResponse(order.getId(),order.getUser().getId(),order.getOrderItems().stream().map(u -> u.getId()).toList(),order.getUser().getUsername(),order.getCreated_at());
    }






}