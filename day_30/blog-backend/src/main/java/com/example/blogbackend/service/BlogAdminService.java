package com.example.blogbackend.service;

import com.example.blogbackend.dto.BlogDto;
import com.example.blogbackend.entity.Blog;
import com.example.blogbackend.entity.Category;
import com.example.blogbackend.entity.User;
import com.example.blogbackend.exception.NotFoundException;
import com.example.blogbackend.mapper.BlogMapper;
import com.example.blogbackend.repository.BlogAdminRepository;
import com.example.blogbackend.repository.CategoryRepository;
import com.example.blogbackend.repository.UserRepository;
import com.example.blogbackend.request.CreateBlogRequest;
import com.example.blogbackend.request.UpdateBlogRequest;
import com.example.blogbackend.security.JwtUtils;
import com.github.slugify.Slugify;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogAdminService {
    @Autowired
    BlogAdminRepository blogAdminRepository;

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public Page<Blog> getAllBlogsByPageAndPagesize(Integer page, Integer pageSize) {
        page = page == null ? 1 : page;
        pageSize = pageSize == null ? 10 : pageSize;
        return blogAdminRepository.findAll(PageRequest.of(page - 1, pageSize));
    }

    public Page<Blog> getAllBlogsByUser(HttpServletRequest httpRequest, Integer page, Integer pageSize) {
        page = page == null ? 1 : page;
        pageSize = pageSize == null ? 10 : pageSize;
        String token = httpRequest.getHeader("Authorization").replace("Bearer ", "");
        System.out.println(token);
        String username = jwtUtils.extractUsername(token);
        System.out.println(username);
        return blogAdminRepository.findByUser_Email(username, PageRequest.of(page - 1, pageSize));
    }

    public BlogDto getBlogById(Integer id) {
        Optional<Blog> optional = blogAdminRepository.findById(id);
        return BlogMapper.toBlogDto(optional.get());
    }

    public BlogDto createBlog(HttpServletRequest httpRequest, CreateBlogRequest request) {
        Slugify slugify = Slugify.builder().build();
        String token = httpRequest.getHeader("Authorization").replace("Bearer ", "");
        String username = jwtUtils.extractUsername(token);
        User user = userRepository.findByEmail(username).orElseThrow();
        List<Category> categories = categoryRepository.findAllById(request.getCategoryIds());
        Blog blog = Blog.builder()
                .title(request.getTitle())
                .slug(slugify.slugify(request.getTitle()))
                .description(request.getDescription())
                .content(request.getContent())
                .thumbnail(request.getThumbnail())
                .status(request.getStatus())
                .user(user)
                .categories(categories)
                .build();
        return BlogMapper.toBlogDto(blogAdminRepository.save(blog));
    }

    public BlogDto updateBlog(HttpServletRequest httpRequest, Integer id, UpdateBlogRequest request) {
        Slugify slugify = Slugify.builder().build();
        Optional<Blog> optional = blogAdminRepository.findById(id);
        String token = httpRequest.getHeader("Authorization").replace("Bearer ", "");
        String username = jwtUtils.extractUsername(token);
        User user = userRepository.findByEmail(username).orElseThrow();
        List<Category> categories = categoryRepository.findAllById(request.getCategoryIds());
        if(optional.isEmpty()) {
            throw new NotFoundException("Not found blog with id = " + id);
        }
        Blog blog = optional.get();
        blog.setTitle(request.getTitle());
        blog.setSlug(slugify.slugify(request.getTitle()));
        blog.setDescription(request.getDescription());
        blog.setContent(request.getContent());
        blog.setThumbnail(request.getThumbnail());
        blog.setStatus(request.getStatus());
        blog.setUser(user);
        blog.setCategories(categories);
        return BlogMapper.toBlogDto(blogAdminRepository.save(blog));
    }

    public void deleteBlog(Integer id) {
        Optional<Blog> optional = blogAdminRepository.findById(id);
        if(optional.isEmpty()) {
            throw new NotFoundException("Not found blog with id = " + id);
        }
        blogAdminRepository.deleteById(id);
    }

    public Page<Blog> getAllBlogsByKeyword(String keyword, Integer page, Integer pageSize) {
        page = page == null ? 1 : page;
        pageSize = pageSize == null ? 10 : pageSize;
        return blogAdminRepository.findByContentContainsIgnoreCase(keyword, PageRequest.of(page - 1, pageSize));
    }
}
