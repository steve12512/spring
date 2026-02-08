package com.example.demo.repository.order;

import com.example.demo.domain.Order;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository {
  Order save(Order order);

  Optional<Order> findById(Long id);

  @Query(
      "SELECT o FROM Order o JOIN o.user u  WHERE u.id = :userId "
          + " AND LOWER(u.username) LIKE LOWER(CONCAT('%',:userName,'%'))")
  Page<Order> findByIdAndUsernameContaining(
      @Param(value = "userId") Long userId,
      @Param(value = "userName") String userName,
      Pageable pageable);
}
