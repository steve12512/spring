package com.example.demo.dto.requests.order_item_requests;

public  record OrderItemRequest(
        Long itemId,
        Integer quantity

) {
}