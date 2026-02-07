package com.example.demo.dto.requests.order_requests;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@Valid
public record ModifyOrderRequest(@NotBlank Long orderId, @NotBlank Long userId) {}
