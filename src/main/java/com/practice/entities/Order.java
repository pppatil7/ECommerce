package com.practice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private LocalDateTime orderDate;

    private String orderStatus;

    private Integer orderItemQuantity;

    private Double orderTotalAmount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
