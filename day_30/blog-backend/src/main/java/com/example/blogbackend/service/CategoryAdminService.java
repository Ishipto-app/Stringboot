package com.example.blogbackend.service;

import com.example.blogbackend.entity.Blog;
import com.example.blogbackend.entity.Category;
import com.example.blogbackend.entity.User;
import com.example.blogbackend.exception.BadRequestException;
import com.example.blogbackend.exception.NotFoundException;
import com.example.blogbackend.mapper.BlogMapper;
import com.example.blogbackend.repository.*;
import com.example.blogbackend.request.CreateCategoryRequest;
import com.example.blogbackend.request.UpdateBlogRequest;
import com.example.blogbackend.request.UpdateCategoryRequest;
import com.example.blogbackend.security.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryAdminService {
    @Autowired
    private CategoryAdminRepository categoryAdminRepository;
    @Autowired
    private BlogAdminRepository blogAdminRepository;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    private UserRepository userRepository;

    public Page<Category> getAllCategoriesByPageAndPagesize(Integer page, Integer pageSize) {
        return categoryAdminRepository.findAll(PageRequest.of(page - 1, pageSize));
    }

    public Category createCategory(CreateCategoryRequest request) {
        Optional<Category> optional = categoryAdminRepository.findByNameIgnoreCase(request.getName());
        if(optional.isPresent()){
            throw new BadRequestException("Category da ton tai");
        }
        Category category = new Category(null, request.getName());
        return categoryAdminRepository.save(category);
    }

    public Category updateCategory(Integer id, UpdateCategoryRequest request) {
        Optional<Category> optional = categoryAdminRepository.findById(id);
        if(optional.isPresent()){
            Category category = optional.get();
            category.setName(request.getName());
        }
        throw new NotFoundException("Not found category with id = " + id);
    }

    public void deleteCategory(Integer id) {
        Optional<Category> optional = categoryAdminRepository.findById(id);
        if(optional.isEmpty()) {
            throw new NotFoundException("Not found category with id = " + id);
        }
        blogAdminRepository.deleteById(id);
    }

    public List<Category> getListAllCategories() {
        return categoryAdminRepository.findAll();
    }
}
