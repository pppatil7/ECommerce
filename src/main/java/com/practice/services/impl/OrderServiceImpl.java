package com.practice.services.impl;

import com.practice.dto.CreateOrderDto;
import com.practice.dto.OrderDto;
import com.practice.entities.Order;
import com.practice.entities.Product;
import com.practice.entities.User;
import com.practice.exceptions.ResourceNotFoundException;
import com.practice.repositories.OrderRepository;
import com.practice.repositories.ProductRepository;
import com.practice.repositories.UserRepository;
import com.practice.services.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Override
    public OrderDto createNewOrder(CreateOrderDto dto, Long userId, Long productId) {
        User user = userRepository.findById(userId).
                orElseThrow(() -> new ResourceNotFoundException("User", "userId", String.valueOf(userId)));
        Product product = productRepository.findById(productId).
                orElseThrow(() -> new ResourceNotFoundException("Product", "productId", String.valueOf(productId)));
        Order order = modelMapper.map(dto, Order.class);
        order.setOrderDate(LocalDateTime.now());
        order.setOrderStatus("PLACED");
        Double orderTotalAmount = order.getOrderItemQuantity() * product.getProductPrice();
        order.setOrderTotalAmount(orderTotalAmount);
        order.setUser(user);
        user.getOrders().add(order); //bidirectional consistency
        order.setProduct(product);
        Order savedOrder = orderRepository.save(order);
        return modelMapper.map(savedOrder, OrderDto.class);
    }


    @Override
    public List<OrderDto> getOrdersByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userId", String.valueOf(userId)));
        List<Order> orders = orderRepository.findByUserUserId(userId);
        List<OrderDto> orderDtoList = new ArrayList<>();
        OrderDto orderDto;
        for (Order order : orders) {
            orderDto = modelMapper.map(order, OrderDto.class);
            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }

    @Transactional
    @Override
    public OrderDto cancelOrder(Long userId, Long orderId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userId", String.valueOf(userId)));
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "orderId", String.valueOf(orderId)));

        if (order.getOrderStatus().equals("CANCELLED")) {
            throw new IllegalStateException("Order CANCELLED Already So cannot Cancel now");
        }
        if (order.getOrderStatus().equals("COMPLETED")) {
            throw new IllegalStateException("COMPLETED Order cannot be CANCELLED");
        }

        order.setOrderStatus("CANCELLED");
        return modelMapper.map(order, OrderDto.class);
    }

    @Override
    public List<OrderDto> getOrdersByProductId(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow();
        List<Order> orders = orderRepository.findByProductProductId(productId).orElseThrow();
        OrderDto orderDto;
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (Order order : orders) {
            orderDto = modelMapper.map(order, OrderDto.class);
            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }



}
