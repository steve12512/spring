package com.example.demo.repository.order;

import com.example.demo.domain.Order;
import com.example.demo.repository.order.OrderRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("jpa")
public interface OrderJpaRepository extends JpaRepository<Order,Long>, OrderRepository {
}