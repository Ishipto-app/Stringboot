package com.example.blogbackend.service;

import com.example.blogbackend.dto.CategoryDto;
import com.example.blogbackend.entity.Category;
import com.example.blogbackend.repository.BlogRepository;
import com.example.blogbackend.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final BlogRepository blogRepository;

    public CategoryService(CategoryRepository categoryRepository,
                           BlogRepository blogRepository) {
        this.categoryRepository = categoryRepository;
        this.blogRepository = blogRepository;
    }

    public List<CategoryDto> getAllCategoriesWithUsed() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtos = new ArrayList<>();

        for (Category category : categories) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(category.getId());
            categoryDto.setName(category.getName());
            Long used = blogRepository.countByCategoryId(category.getId());
            categoryDto.setUsed(used);
            categoryDtos.add(categoryDto);
        }

        return categoryDtos;
    }

    public List<CategoryDto> getAllCategoriesWithUsedTop5() {
        return categoryRepository.findLimitCategories(5);
    }

}
