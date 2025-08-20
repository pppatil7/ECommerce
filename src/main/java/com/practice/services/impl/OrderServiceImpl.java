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
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
}
