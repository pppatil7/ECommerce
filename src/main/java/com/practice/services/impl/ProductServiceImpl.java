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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        product.setCategory(category);
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductDto.class);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtoList = new ArrayList<>();
        ProductDto productDto;
        for (Product product : products) {
            productDto = modelMapper.map(product, ProductDto.class);
            productDtoList.add(productDto);
        }
        return productDtoList;
    }

    @Override
    public ProductDto getProductByProductTitle(String productTitle) {
        Product product = productRepository.findByProductTitle(productTitle).
                orElseThrow(() -> new ResourceNotFoundException("Product", "productTitle", productTitle));
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public List<ProductDto> getProductsByCategoryId(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow();
        List<Product> products = productRepository.findByCategory(category).
                orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", String.valueOf(categoryId)));
        List<ProductDto> productDtoList = new ArrayList<>();
        ProductDto productDto;
        for (Product product : products) {
            productDto = modelMapper.map(product, ProductDto.class);
            productDtoList.add(productDto);
        }
        return productDtoList;
    }

    @Override
    public ProductDto updatePartialProductByProductId(Long categoryId, Long productId, Map<String, Object> updates) {
        Category category = categoryRepository.findById(categoryId).
                orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", String.valueOf(categoryId)));
        Product product = productRepository.findById(productId).
                orElseThrow(() -> new ResourceNotFoundException("Product", "productId", String.valueOf(productId)));
        Object value;
        for (String field : updates.keySet()) {
            switch (field) {
                case "productTitle":
                    value = updates.get(field);
                    product.setProductTitle((String) value);
                    break;
                case "productDescription":
                    value = updates.get(field);
                    product.setProductDescription((String) value);
                    break;
                case "productPrice":
                    value = updates.get(field);
                    product.setProductPrice((Double) value);
                    break;
                default:
                    throw new IllegalArgumentException("Field is not Supported");
            }
        }
        Product updatedProduct = productRepository.save(product);
        return modelMapper.map(updatedProduct, ProductDto.class);
    }
}
