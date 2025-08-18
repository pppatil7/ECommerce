package com.practice.services;

import com.practice.dto.CreateOrderDto;
import com.practice.dto.OrderDto;

public interface OrderService {

    OrderDto createNewOrder(CreateOrderDto dto, Long userId, Long productId);

}
