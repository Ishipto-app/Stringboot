package com.example.blogbackend.service;

import com.example.blogbackend.entity.Blog;
import com.example.blogbackend.entity.Category;
import com.example.blogbackend.entity.Comment;
import com.example.blogbackend.entity.User;
import com.example.blogbackend.exception.BadRequestException;
import com.example.blogbackend.exception.NotFoundException;
import com.example.blogbackend.repository.BlogAdminRepository;
import com.example.blogbackend.repository.CategoryAdminRepository;
import com.example.blogbackend.repository.CommentAdminRepository;
import com.example.blogbackend.repository.UserRepository;
import com.example.blogbackend.request.CreateCategoryRequest;
import com.example.blogbackend.request.CreateCommentRequest;
import com.example.blogbackend.request.UpdateCategoryRequest;
import com.example.blogbackend.request.UpdateCommentRequest;
import com.example.blogbackend.security.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentAdminService {
    @Autowired
    private CommentAdminRepository commentAdminRepository;
    @Autowired
    private BlogAdminRepository blogAdminRepository;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    private UserRepository userRepository;

    public Page<Comment> getAllCommentsByPageAndPagesize(Integer page, Integer pageSize) {
        return commentAdminRepository.findAll(PageRequest.of(page - 1, pageSize));
    }

    public Comment createComment(HttpServletRequest httpRequest, CreateCommentRequest request) {
        String token = httpRequest.getHeader("Authorization").replace("Bearer ", "");
        String username = jwtUtils.extractUsername(token);
        User user = userRepository.findByEmail(username).orElseThrow();
        Comment comment = Comment.builder()
                .content(request.getContent())
                .user(user)
                .build();
        return commentAdminRepository.save(comment);
    }

    public Comment updateComment(HttpServletRequest httpRequest, Integer id, UpdateCommentRequest request) {
        Optional<Comment> optional = commentAdminRepository.findById(id);
        if(optional.isPresent()){
            String token = httpRequest.getHeader("Authorization").replace("Bearer ", "");
            String username = jwtUtils.extractUsername(token);
            User user = userRepository.findByEmail(username).orElseThrow();
            Comment comment = optional.get();
            comment.setContent(request.getContent());
            comment.setUser(user);
            return commentAdminRepository.save(comment);
        }
        throw new NotFoundException("Not found comment with id = " + id);
    }

    public void deleteComment(Integer id) {
        Optional<Comment> optional = commentAdminRepository.findById(id);
        if(optional.isEmpty()) {
            throw new NotFoundException("Not found comment with id = " + id);
        }
        blogAdminRepository.deleteById(id);
    }

}
