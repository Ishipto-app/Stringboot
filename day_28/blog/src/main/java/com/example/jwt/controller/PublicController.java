package com.example.jwt.controller;

import com.example.jwt.dto.BlogDto;
import com.example.jwt.dto.CategoryDto;
import com.example.jwt.entity.Blog;
import com.example.jwt.security.BlogService;
import com.example.jwt.security.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/public")
public class PublicController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private CategoryService categoryService;

    //    GET : api/v1/public/blogs?page={pageValue}&pageSize=${pageSizeValue}
    @GetMapping("/blogs")
    public Page<Blog> getBlogPagination(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize){
        return blogService.getBlogPagination(page, pageSize);
    }

    //    GET : api/v1/public/search?term={termValue}
    @GetMapping("/search")
    public List<Blog> getBlogByTitle(@RequestParam String term){
        return blogService.getBlogByTitle(term);
    }

    //    GET : api/v1/public/categories
    @GetMapping("/categories")
    public List<CategoryDto> getAllCategoriesWithUsed(){
        return categoryService.getAllCategoriesWithUsed();
    }

//    GET : api/v1/public/category/top5
    @GetMapping("/category/top5")
    public List<CategoryDto> getAllCategoriesWithUsedTop5(){
        return categoryService.getAllCategoriesWithUsedTop5();
    }

//    GET : api/v1/public/category/{categoryName}
    @GetMapping("/category/{categoryName}")
    public List<Blog> getBlogByCategory(@PathVariable String categoryName){
        return blogService.getBlogByCategory(categoryName);
    }

//    GET : api/v1/public/blogs/{blogId}/{blogSlug}
    @GetMapping("/blogs/{blogId}/{blogSlug}")
    public BlogDto getBlogById(@PathVariable Integer blogId, @PathVariable String blogSlug){
        return blogService.getBlogById(blogId, blogSlug);
    }
}
