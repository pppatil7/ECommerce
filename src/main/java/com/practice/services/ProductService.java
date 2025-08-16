package com.practice.services;

import com.practice.dto.CreateProductDto;
import com.practice.dto.ProductDto;

import java.util.List;
import java.util.Map;

public interface ProductService {

    ProductDto createProduct(Long categoryId, CreateProductDto dto);

    List<ProductDto> getAllProduct();

    ProductDto getProductByProductTitle(String productTitle);

    ProductDto updatePartialProductByProductId(Long categoryId, Long productId, Map<String, Object> updates);


}
