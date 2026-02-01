package com.example.demo.dto.requests.order_requests;

import com.example.demo.dto.requests.order_item_requests.OrderItemRequest;

import java.util.List;

public record CreateOrderRequest(
        List<OrderItemRequest> orderItemRequests,
        Long userId,
        String information
) {

}