package com.practice.controllers;

import com.practice.dto.CreateProductDto;
import com.practice.dto.ProductDto;
import com.practice.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class ProductController {


    private final ProductService productService;


    @PostMapping("categories/{categoryId}/products")
    public ResponseEntity<ProductDto> createProduct(@PathVariable Long categoryId, @RequestBody CreateProductDto dto) {
        ProductDto productDto = productService.createProduct(categoryId, dto);
        return ResponseEntity.ok(productDto);
    }

}
