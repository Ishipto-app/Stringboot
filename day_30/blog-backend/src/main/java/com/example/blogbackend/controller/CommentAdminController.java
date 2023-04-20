package com.example.blogbackend.controller;

import com.example.blogbackend.entity.Comment;
import com.example.blogbackend.request.CreateCommentRequest;
import com.example.blogbackend.request.UpdateCommentRequest;
import com.example.blogbackend.service.CommentAdminService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/admin")
public class CommentAdminController {
    @Autowired
    CommentAdminService commentAdminService;
//    Hiển thị ds comment (có phân trang, mặc định là pageSize = 10)
//    GET : api/v1/admin/comments?page={page}&pageSize={pageSize}
    @GetMapping("comments")
    public Page<Comment> getAllComments(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize){
        return commentAdminService.getAllCommentsByPageAndPagesize(page, pageSize);
    }

    @PostMapping("comments")
    public Comment createComment(HttpServletRequest httpRequest, @Valid @RequestBody CreateCommentRequest request){
        return commentAdminService.createComment(httpRequest, request);
    }
//    Cập nhật thông tin comment
//    PUT : api/v1/admin/comments/{id}
    @PutMapping("comments/{id}")
    public Comment updateComment(HttpServletRequest httpRequest, @PathVariable Integer id, @Valid @RequestBody UpdateCommentRequest request){
        return commentAdminService.updateComment(httpRequest, id, request);
    }
//    Xóa comment
//    DELETE : api/v1/admin/comments/{id}
    @DeleteMapping("comments/{id}")
    public void deleteComment(@PathVariable Integer id){
        commentAdminService.deleteComment(id);
    }
}
