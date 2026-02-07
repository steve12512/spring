package com.example.demo.dto.responses.order_responses;

import java.sql.Timestamp;
import java.util.List;

public record OrderResponse(
    Long orderId, Long userId, List<Long> createdItemIds, String userName, Timestamp createdAt) {}
