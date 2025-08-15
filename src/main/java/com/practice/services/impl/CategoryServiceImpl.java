package com.practice.services.impl;

import com.practice.dto.CategoryDto;
import com.practice.dto.CreateCategoryDto;
import com.practice.entities.Category;
import com.practice.exceptions.ResourceNotFoundException;
import com.practice.repositories.CategoryRepository;
import com.practice.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CreateCategoryDto dto) {
        Category category = modelMapper.map(dto, Category.class);
        Category savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategoryByCategoryId(Long categoryId, CreateCategoryDto dto) {
        Category category = categoryRepository.findById(categoryId).
                orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", String.valueOf(categoryId)));
        modelMapper.map(dto, category);
        category = categoryRepository.save(category);
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public CategoryDto getCategoryByCategoryId(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).
                orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", String.valueOf(categoryId)));
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        CategoryDto categoryDto;
        for (Category category : categories) {
            categoryDto = modelMapper.map(category, CategoryDto.class);
            categoryDtoList.add(categoryDto);
        }
        return categoryDtoList;
    }

    @Override
    public void deleteCategoryByCategoryId(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).
                orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", String.valueOf(categoryId)));
        categoryRepository.delete(category);
    }

    @Override
    public CategoryDto updatePartialCategoryByCategoryId(Long categoryId, Map<String, Object> updates) {
        Category category = categoryRepository.findById(categoryId).
                orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", String.valueOf(categoryId)));
        Object value;
        for (String field : updates.keySet()) {
            switch (field) {
                case "categoryName":
                    value = updates.get(field);
                    category.setCategoryName((String) value);
                    break;
                default:
                    throw new IllegalArgumentException("Field is not supported");
            }
        }
        Category savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryDto.class);
    }
}
