package com.practice.services.impl;

import com.practice.dto.CreateOrderDto;
import com.practice.dto.OrderDto;
import com.practice.entities.Order;
import com.practice.entities.Product;
import com.practice.exceptions.ResourceNotFoundException;
import com.practice.repositories.OrderRepository;
import com.practice.repositories.ProductRepository;
import com.practice.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    @Override
    public OrderDto createNewOrder(CreateOrderDto dto, Long productId) {
        Product product = productRepository.findById(productId).
                orElseThrow(() -> new ResourceNotFoundException("Product", "productId", String.valueOf(productId)));
        Order order = modelMapper.map(dto, Order.class);
        order.setOrderDate(LocalDateTime.now());
        order.setOrderStatus("PLACED");
        Double orderTotalAmount = order.getOrderItemQuantity() * product.getProductPrice();
        order.setOrderTotalAmount(orderTotalAmount);
        order.setProduct(product);
        Order savedOrder = orderRepository.save(order);
        return modelMapper.map(savedOrder, OrderDto.class);
    }
}
