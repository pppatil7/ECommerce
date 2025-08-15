package com.practice.services.impl;

import com.practice.dto.CategoryDto;
import com.practice.dto.CreateCategoryDto;
import com.practice.entities.Category;
import com.practice.repositories.CategoryRepository;
import com.practice.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
