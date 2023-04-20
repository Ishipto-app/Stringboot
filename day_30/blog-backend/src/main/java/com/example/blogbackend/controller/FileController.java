package com.example.blogbackend.controller;

import com.example.blogbackend.entity.Image;
import com.example.blogbackend.response.FileResponse;
import com.example.blogbackend.service.FileService;
import com.example.blogbackend.service.UserAdminService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class FileController {
    @Autowired
    UserAdminService userAdminService;
    @Autowired
    FileService fileService;
    // Upload image theo user (người thực hiện upload chính là user đang login)
    // POST : api/v1/files
    @PostMapping("files")
    public ResponseEntity<?> uploadFile(HttpServletRequest httpRequest, @ModelAttribute("file") MultipartFile file){
        FileResponse fileResponse = fileService.uploadFile(httpRequest, file);
        return ResponseEntity.ok(fileResponse);
    }

    // Xem ảnh
    // GET : api/v1/files/{id}
    @GetMapping("files/{id}")
    public ResponseEntity<?> readFile(@PathVariable Integer id){
        Image image = fileService.readFile(id);
        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(image.getType()))
                .body(image.getData());
    }

    // Todo: kiem tra xoa anh
    // Xóa ảnh (nếu không phải ảnh của user upload -> không cho xóa)
    // DELETE : api/v1/files/{id}
    @DeleteMapping("files/{id}")
    public void fileDelete(HttpServletRequest httpRequest, Integer id){
        fileService.fileDelete(httpRequest, id);
    }
    // Lấy danh sách ảnh của user đang login
    // GET : api/v1/users/files
    @GetMapping("users/{id}/files")
    public List<Image> getAllImageByUser(Integer id){
        return fileService.getAllImageByUser(id);
    }
}
