package com.example.blogbackend.service;

import com.example.blogbackend.entity.Image;
import com.example.blogbackend.entity.User;
import com.example.blogbackend.exception.BadRequestException;
import com.example.blogbackend.exception.NotFoundException;
import com.example.blogbackend.repository.ImageRepository;
import com.example.blogbackend.repository.UserAdminRepository;
import com.example.blogbackend.repository.UserRepository;
import com.example.blogbackend.response.FileResponse;
import com.example.blogbackend.security.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class FileService {
    private final ImageRepository imageRepository;
    private final UserAdminRepository userAdminRepository;
    @Autowired
    JwtUtils jwtUtils;
    private final UserRepository userRepository;

    //validate file
    public FileResponse uploadFile(HttpServletRequest httpRequest, MultipartFile file) {
        validateFile(file);
        String token = httpRequest.getHeader("Authorization").replace("Bearer ", "");
        String username = jwtUtils.extractUsername(token);
        User user = userAdminRepository.findByEmail(username).orElseThrow();

        try {
            Image image = Image.builder()
                    .type(file.getContentType())
                    .data(file.getBytes())
                    .user(user)
                    .build();
            imageRepository.save(image);

            return new FileResponse("/api/v1/files/" + image.getId());

        } catch (IOException e) {
            throw new RuntimeException("Co loi xay ra");
        }

    }

    public void validateFile(MultipartFile file) {
        //ten file ko de trong
        String fileName = file.getOriginalFilename();
        if (fileName == null || fileName.isEmpty()) {
            throw new BadRequestException("ten file khong hop le");
        }
        ;

        //type file co nam trong danh sach cho phep ko
        //avatar.png image.jpg
        String fileExtention = getFileExtension(fileName);
        if (!checkFileExtention(fileExtention)) {
            throw new BadRequestException("ten file khong hop le");
        }

        //kich thuoc file trong pham vi cho phep khong
        //1,048,576B = 1MB
        double fileSize = (double) file.getSize() / 1048576;
        if (fileSize > 1) {
            throw new BadRequestException("Size file khong duoc vuot qua 1MB");
        }
    }

    public String getFileExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf(".");
        if (lastIndex == -1) {
            return "";
        }
        return fileName.substring(lastIndex + 1);
    }

    public boolean checkFileExtention(String fileExtention) {
        List<String> fileExtentions = List.of("png", "jpg", "jpeg", "pdf");
        return fileExtentions.contains(fileExtention);
    }

    public Image readFile(Integer id) {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found image");
                });
        return image;
    }

    public void fileDelete(HttpServletRequest httpRequest, Integer id) {
        Optional<Image> image = imageRepository.findById(id);
        if (image.isPresent()) {
            String token = httpRequest.getHeader("Authorization").replace("Bearer ", "");
            String username = jwtUtils.extractUsername(token);
            User user = userRepository.findByEmail(username).orElseThrow();
            if(Objects.equals(image.get().getUser(), user)){
                imageRepository.deleteById(id);
            } else {
                throw new BadRequestException("User khong dung khong xoa duoc anh");
            }
        } else {
            throw new NotFoundException("Not found image with id = " + id);
        }
    }

    public List<Image> getAllImageByUser(Integer id) {
        return imageRepository.findByUser_Id(id);
    }
}