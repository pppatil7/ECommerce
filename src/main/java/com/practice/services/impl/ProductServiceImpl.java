package com.practice.services.impl;

import com.practice.dto.CreateProductDto;
import com.practice.dto.ProductDto;
import com.practice.entities.Category;
import com.practice.entities.Product;
import com.practice.exceptions.ResourceNotFoundException;
import com.practice.repositories.CategoryRepository;
import com.practice.repositories.ProductRepository;
import com.practice.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;

    @Override
    public ProductDto createProduct(Long categoryId, CreateProductDto dto) {
        Category category = categoryRepository.findById(categoryId).
                orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", String.valueOf(categoryId)));
        Product product = modelMapper.map(dto, Product.class);
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductDto.class);
    }


}
