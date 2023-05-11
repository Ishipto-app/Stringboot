package com.example.blogbackend.controller;

import com.example.blogbackend.dto.CategoryDto;
import com.example.blogbackend.entity.Category;
import com.example.blogbackend.request.CreateCategoryRequest;
import com.example.blogbackend.request.UpdateCategoryRequest;
import com.example.blogbackend.service.CategoryAdminService;
import com.example.blogbackend.service.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/admin")
public class CategoryAdminController {
    @Autowired
    CategoryAdminService categoryAdminService;
    @Autowired
    CategoryService categoryService;

    @GetMapping("categories/all")
    public List<CategoryDto> getAllCategoriesWithUsed(){
        return categoryService.getAllCategoriesWithUsed();
    }

    //    Lấy ds category (có phân trang, mặc định là pageSize = 10)
    //    GET : api/v1/admin/categories?page={page}&pageSize={pageSize}
    @GetMapping("categories")
    public Page<Category> getAllCategories(@RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "10") Integer pageSize){
        return categoryAdminService.getAllCategoriesByPageAndPagesize(page, pageSize);
    }
    @GetMapping("categories/{id}")
    public Category getCategoryById(@PathVariable Integer id){
        return categoryAdminService.getCategoryById(id);
    }
    //    Thêm category (Lưu ý tên category không được trùng nhau)
    //    POST : api/v1/admin/categories
    @PostMapping("categories")
    public Category createCategory(@Valid @RequestBody CreateCategoryRequest request){
        return categoryAdminService.createCategory(request);
    }

    //    Cập nhật category (Lưu ý tên category không được trùng nhau)
    //    PUT : api/v1/admin/categories/{id}
    @PutMapping("categories/{id}")
    public Category updateCategory(@PathVariable Integer id,@Valid @RequestBody UpdateCategoryRequest request){
        return categoryAdminService.updateCategory(id, request);
    }

    // Todo: kiem tra xoa
    //    Xóa category (xóa blog áp dụng category trong bảng trung gian, không xóa blog trong bảng blog)
    //    DELETE : api/v1/admin/categories/{id}
    @DeleteMapping("categories/{id}")
    public void deleteCategory(@PathVariable Integer id){
        System.out.println(id);
        categoryAdminService.deleteCategory(id);
    }
}