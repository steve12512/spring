package com.example.demo.domain;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "orders")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
  List<OrderItem> orderItems = new ArrayList<>();

  @ManyToOne
  @JoinColumn(name = "user_id")
  User user;

  String status = "Active";

  @CreationTimestamp
  @Column(updatable = false, nullable = false)
  private Timestamp created_at;
}
