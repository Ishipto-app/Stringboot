package com.example.jwt.security;

import com.example.jwt.dto.CategoryDto;
import com.example.jwt.entity.Blog;
import com.example.jwt.entity.Category;
import com.example.jwt.repository.BlogRepository;
import com.example.jwt.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    private final BlogRepository blogRepository;
    private final CategoryRepository categoryRepository;

    public BlogService(BlogRepository blogRepository,
                       CategoryRepository categoryRepository) {
        this.blogRepository = blogRepository;
        this.categoryRepository = categoryRepository;
    }

    public Page<Blog> getBlogPagination(Integer page, Integer pageSize) {
        //kiem tra null param
        page = page == null ? 1 : page;
        pageSize = pageSize == null ? 5 : pageSize;
        return blogRepository.findByStatusTrueOrderByPulishedAtDesc(PageRequest.of(page - 1, pageSize));
    }

    public List<Blog> getBlogByTitle(String term) {
        return blogRepository.findByTitleContainsIgnoreCaseAndStatusTrue(term);
    }

    public List<Blog> getBlogByCategory(String categoryName) {
        return blogRepository.findByCategories_NameAndStatusTrue(categoryName);
    }

    public Blog getBlogById(Integer id, String slug) {
        Optional optional = blogRepository.findByIdAndSlugAndStatusTrue(id, slug);
        if(optional.isPresent()){
            return (Blog) optional.get();
        } else {
            return null;
        }
    }

}
