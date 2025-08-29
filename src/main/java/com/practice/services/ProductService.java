package com.practice.services;

import com.practice.dto.CreateProductDto;
import com.practice.dto.OrderDto;
import com.practice.dto.ProductDto;

import java.util.List;
import java.util.Map;

public interface ProductService {

    ProductDto createProduct(Long categoryId, CreateProductDto dto);

    List<ProductDto> getAllProducts();

    ProductDto getProductByProductTitle(String productTitle);

    List<ProductDto> getProductsByCategoryId(Long categoryId);

    ProductDto updatePartialProductByProductId(Long categoryId, Long productId, Map<String, Object> updates);


}
