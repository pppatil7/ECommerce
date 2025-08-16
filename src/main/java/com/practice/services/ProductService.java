package com.practice.services;

import com.practice.dto.CreateProductDto;
import com.practice.dto.ProductDto;

import java.util.Map;

public interface ProductService {

    ProductDto createProduct(Long categoryId, CreateProductDto dto);

    ProductDto updatePartialProductByProductId(Long categoryId, Long productId, Map<String, Object> updates);


}
