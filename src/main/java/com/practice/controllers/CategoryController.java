package com.practice.controllers;


import com.practice.dto.CategoryDto;
import com.practice.dto.CreateCategoryDto;
import com.practice.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CreateCategoryDto dto) {
        CategoryDto categoryDto = categoryService.createCategory(dto);
        return new ResponseEntity<>(categoryDto, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategoryByCategoryId(@PathVariable Long categoryId, @RequestBody CreateCategoryDto dto) {
        CategoryDto categoryDto = categoryService.updateCategoryByCategoryId(categoryId, dto);
        return ResponseEntity.ok(categoryDto);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategoryByCategoryId(@PathVariable Long categoryId) {
        CategoryDto categoryDto = categoryService.getCategoryByCategoryId(categoryId);
        return ResponseEntity.ok(categoryDto);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> categoryDtoList = categoryService.getAllCategories();
        return ResponseEntity.ok(categoryDtoList);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategoryByCategoryId(@PathVariable Long categoryId) {
        categoryService.deleteCategoryByCategoryId(categoryId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updatePartialCategoryByCategoryId(@PathVariable Long categoryId, @RequestBody Map<String, Object> updates) {
        CategoryDto categoryDto = categoryService.updatePartialCategoryByCategoryId(categoryId, updates);
        return ResponseEntity.ok(categoryDto);
    }


}
