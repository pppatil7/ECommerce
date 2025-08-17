package com.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDto {

    private Long orderId;

    private ProductDto product;

    private LocalDateTime orderDate;

    private String orderStatus;

    private Integer orderItemQuantity;

    private Double orderTotalAmount;
}
