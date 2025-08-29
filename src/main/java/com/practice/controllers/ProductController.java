package com.practice.controllers;

import com.practice.dto.CreateProductDto;
import com.practice.dto.OrderDto;
import com.practice.dto.ProductDto;
import com.practice.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping("categories/products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> productDtoList = productService.getAllProducts();
        return ResponseEntity.ok(productDtoList);
    }

    @GetMapping("categories/products/{productTitle}")
    public ResponseEntity<ProductDto> getProductByProductTitle(@PathVariable String productTitle) {
        ProductDto productDto = productService.getProductByProductTitle(productTitle);
        return ResponseEntity.ok(productDto);
    }

    @GetMapping("categories/{categoryId}/products")
    public ResponseEntity<List<ProductDto>> getProductsByCategoryId(@PathVariable Long categoryId) {
        List<ProductDto> productDtoList = productService.getProductsByCategoryId(categoryId);
        return ResponseEntity.ok(productDtoList);
    }

    @PatchMapping("categories/{categoryId}/products/{productId}")
    public ResponseEntity<ProductDto> updatePartialProductByProductId(@PathVariable Long categoryId, @PathVariable Long productId, @RequestBody Map<String, Object> updates) {
        ProductDto productDto = productService.updatePartialProductByProductId(categoryId, productId, updates);
        return ResponseEntity.ok(productDto);
    }


}
