package com.practice.services;

import com.practice.dto.CategoryDto;
import com.practice.dto.CreateCategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CreateCategoryDto dto);

    CategoryDto getCategoryByCategoryId(Long categoryId);

    List<CategoryDto> getAllCategories();
}
