package com.practice.entities;

import jakarta.persistence.*;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer orderItemQuantity;

    private Double orderItemPrice;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

}
