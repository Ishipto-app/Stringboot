package user.example.usermanager.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import user.example.usermanager.exception.BadRequestException;
import user.example.usermanager.exception.NotFoundException;
import user.example.usermanager.model.Image;
import user.example.usermanager.model.response.FileResponse;
import user.example.usermanager.repository.ImageRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileService {
    private final ImageRepository imageRepository;

    public FileService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    //validate file
    public FileResponse uploadFile(MultipartFile file) {
        validateFile(file);

        try {
            Image image = Image.builder()
                    .type(file.getContentType())
                    .data(file.getBytes())
                    .createAt(LocalDateTime.now())
                    .build();
            imageRepository.save(image);

            return new FileResponse("/api/v1/files/" + image.getId());

        } catch (IOException e){
            throw new RuntimeException("Co loi xay ra");
        }

    }

    public void validateFile(MultipartFile file){
        //ten file ko de trong
        String fileName = file.getOriginalFilename();
        if(fileName == null || fileName.isEmpty()){
            throw new BadRequestException("ten file khong hop le");
        };

        //type file co nam trong danh sach cho phep ko
        //avatar.png image.jpg
        String fileExtention = getFileExtension(fileName);
        if(!checkFileExtention(fileExtention)){
            throw new BadRequestException("ten file khong hop le");
        }

        //kich thuoc file trong pham vi cho phep khong
        //1,048,576B = 1MB
        double fileSize = (double) file.getSize()/1048576;
        if(fileSize > 1){
            throw new BadRequestException("Size file khong duoc vuot qua 1MB");
        }
    }

    public String getFileExtension(String fileName){
        int lastIndex = fileName.lastIndexOf(".");
        if(lastIndex == -1){
            return "";
        }
        return fileName.substring(lastIndex + 1);
    }
    public boolean checkFileExtention(String fileExtention){
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

    public void fileDelete(Integer id){
        Optional<Image> image = imageRepository.findById(id);
        if(image.isPresent()){
            imageRepository.deleteById(id);
            System.out.println("xoa todo " + id);
        } else {
            throw new NotFoundException("Not found image with id = " + id);
        }
    }
}
