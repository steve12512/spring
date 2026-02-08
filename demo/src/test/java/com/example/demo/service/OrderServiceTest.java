package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.example.demo.domain.Order;
import com.example.demo.domain.User;
import com.example.demo.dto.responses.order_responses.OrderResponse;
import com.example.demo.exception.order.OrderNotFoundException;
import com.example.demo.repository.order.OrderRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

  @Mock private UserService userService;
  @Mock private ItemService itemService;
  @Mock private OrderRepository orderRepository;
  @InjectMocks private OrderService orderService;

  @Test
  public void testFindById() {
    Order order = new Order();
    User user = new User("cccSTAVROS", "stevekalelis@outlook.gr", 40, true);
    order.setUser(user);

    when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));

    OrderResponse mockOrderResponse = orderService.findById(order.getId());

    assertNotNull(mockOrderResponse);
    assertEquals(mockOrderResponse.orderId(), order.getId());
    assertEquals(mockOrderResponse.userId(), order.getUser().getId());
    assertEquals(
        order.getOrderItems().stream().map(item -> item.getId()).toList(),
        mockOrderResponse.createdItemIds());
    assertEquals(mockOrderResponse.createdAt(), order.getCreated_at());
  }

  @Test
  public void testFindByIdThrowsOrderNotFoundException() {
    Order order = new Order();
    when(orderRepository.findById(order.getId())).thenReturn(Optional.empty());

    assertThrows(OrderNotFoundException.class, () -> orderService.findById(order.getId()));
  }

  //    @Test
  //    public void testCreateOrder() {
  //        User user = new User("cccSTAVROS", "stevekalelis@outlook.gr", 40, true);
  //
  //
  //
  //
  //    }

}
