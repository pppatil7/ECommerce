package com.practice.services;

import com.practice.dto.CreateOrderDto;
import com.practice.dto.OrderDto;

import java.util.List;

public interface OrderService {

    OrderDto createNewOrder(CreateOrderDto dto, Long userId, Long productId);

    List<OrderDto> getOrdersByUserId(Long userId);

    OrderDto cancelOrder(Long userId, Long orderId);

    List<OrderDto> getOrdersByProductId(Long productId);

}
