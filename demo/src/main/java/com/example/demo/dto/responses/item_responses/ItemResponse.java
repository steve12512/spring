package com.example.demo.dto.responses.item_responses;


public record ItemResponse(
        Long id,
        String name,
        Double price,
        String info,
        String message
) {
}