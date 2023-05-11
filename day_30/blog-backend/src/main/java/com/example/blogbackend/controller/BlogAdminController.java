package com.example.blogbackend.controller;


import com.example.blogbackend.dto.BlogDto;
import com.example.blogbackend.entity.Blog;
import com.example.blogbackend.request.CreateBlogRequest;
import com.example.blogbackend.request.UpdateBlogRequest;
import com.example.blogbackend.service.BlogAdminService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/admin")
public class BlogAdminController {
    @Autowired
    BlogAdminService blogAdminService;

    //api/v1/admin/blogs?page={page}&pageSize={pageSize}
    @GetMapping("blogs")
    public Page<BlogDto> getAllBlogs(@RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "10") Integer pageSize){
        return blogAdminService.getAllBlogsByPageAndPagesize(page, pageSize);
    }

    //api/v1/admin/blogs/own-blogs?page={page}&pageSize={pageSize}
    @GetMapping("blogs/own-blogs")
    public  Page<BlogDto> getAllBlogsByUser(HttpServletRequest request, @RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "10") Integer pageSize){
        System.out.println("00000");

        return blogAdminService.getAllBlogsByUser(request, page, pageSize);
    }

    // api/v1/admin/blogs/{id}
    @GetMapping("blogs/{id}")
    public BlogDto getCourseById(@PathVariable Integer id){
        return blogAdminService.getBlogById(id);
    }

    // api/v1/admin/blogs
    @PostMapping("blogs")
    public Blog createCourse(HttpServletRequest httpRequest, @Valid @RequestBody CreateBlogRequest request){
        return blogAdminService.createBlog(httpRequest, request);
    }

    // api/v1/admin/blogs/{id}
    @PutMapping("blogs/{id}")
    public BlogDto updateCourse(HttpServletRequest httpRequest, @PathVariable Integer id,@Valid @RequestBody UpdateBlogRequest request){
        return blogAdminService.updateBlog(httpRequest, id, request);
    }

    // Todo: kiem tra xoa
    //Xóa blog (xóa blog xóa luôn comment liên quan đến blog)
    // api/v1/admin/blogs/{id}
    @DeleteMapping("blogs/{id}")
    public void deleteCourse(@PathVariable Integer id){
        blogAdminService.deleteBlog(id);
    }

    //api/v1/admin/blogs/search?keyword={keyword}&page={page}&pageSize={pageSize}
    @GetMapping("blogs/search")
    public  Page<Blog> getAllBlogsByUser(@RequestParam String keyword, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize){
        return blogAdminService.getAllBlogsByKeyword(keyword, page, pageSize);
    }
}
