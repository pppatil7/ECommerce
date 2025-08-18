package com.practice.controllers;

import com.practice.dto.CreateOrderDto;
import com.practice.dto.OrderDto;
import com.practice.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("users/{userId}/products/{productId}/orders")
    public ResponseEntity<OrderDto> createNewOrder(@RequestBody CreateOrderDto dto, @PathVariable Long userId, @PathVariable Long productId) {
        OrderDto orderDto = orderService.createNewOrder(dto, userId, productId);
        return new ResponseEntity<>(orderDto, HttpStatus.CREATED);
    }


}
