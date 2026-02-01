package com.example.demo.repository.order;

import com.example.demo.domain.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository {
    Order save(Order order);
}