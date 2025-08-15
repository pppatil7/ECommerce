package com.practice.services;

import com.practice.dto.CategoryDto;
import com.practice.dto.CreateCategoryDto;

import java.util.List;
import java.util.Map;

public interface CategoryService {

    CategoryDto createCategory(CreateCategoryDto dto);

    CategoryDto updateCategoryByCategoryId(Long categoryId, CreateCategoryDto dto);

    CategoryDto getCategoryByCategoryId(Long categoryId);

    List<CategoryDto> getAllCategories();

    void deleteCategoryByCategoryId(Long categoryId);

    CategoryDto updatePartialCategoryByCategoryId(Long categoryId, Map<String, Object> updates);
}
