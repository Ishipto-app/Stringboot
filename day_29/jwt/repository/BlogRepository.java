package com.example.blogbackend.repository;

import com.example.blogbackend.entity.Blog;
import com.example.blogbackend.entity.Category;
import com.example.blogbackend.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
    Page<Blog> findByStatusTrueOrderByPulishedAtDesc(Pageable pageable);

    List<Blog> findByTitleContainsIgnoreCase(String title);

    List<Blog> findByTitleContainsIgnoreCaseAndStatusTrue(String title);


    @Query(nativeQuery = true, value="select count(blog.id) FROM blog\n" +
            "inner join blog_category on blog.id = blog_category.blog_id\n" +
            "where blog_category.category_id = ?1 and blog.status = 1")
    Long countByCategoryId(Integer categoryId);


    List<Blog> findByCategories_NameAndStatusTrue(String name);



    Optional<Blog> findByIdAndSlugAndStatusTrue(Integer id, String slug);

    Optional<Blog> findFirstByIdAndSlugAndStatusTrueOrderByComments_CreatedAtDesc(Integer id, String slug);



}