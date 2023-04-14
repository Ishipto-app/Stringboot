package com.example.jwt.repository;

import com.example.jwt.dto.CategoryDto;
import com.example.jwt.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query(nativeQuery = true, value="select count(blog.id) FROM blog\n" +
            "inner join blog_category on blog.id = blog_category.blog_id\n" +
            "where blog_category.category_id = ?1")
    Long countByCategoryId(Integer categoryId);

//    @Query("SELECT NEW com.example.jwt.dto.CategoryDto(c.id, c.name, COUNT(b.id)) " +
//            "FROM Blog b " +
//            "JOIN b.categories c " +
//            "GROUP BY c " +
//            "ORDER BY COUNT(b.id) DESC")

    @Query(nativeQuery = true, name = "findLimitCategories")
    List<CategoryDto> findLimitCategories(Integer limit);

    Optional findByName(String categoryName);
}