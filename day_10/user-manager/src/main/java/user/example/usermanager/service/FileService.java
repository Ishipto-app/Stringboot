package user.example.usermanager.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import user.example.usermanager.exception.BadRequestException;
import user.example.usermanager.exception.NotFoundException;
import user.example.usermanager.model.response.FileResponse;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class FileService {

    //B1 dinh nghia folder luu tru file
    private final Path rootPath = Paths.get("upload");

    public FileService() {
        createFolder(rootPath.toString());
    }

    private void createFolder(String path){
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
    }

    //validate file
    public FileResponse uploadFile(MultipartFile file) {
        validateFile(file);

        String fileId = UUID.randomUUID().toString();
        Path filePath = rootPath.resolve(fileId);

        File fileNew = new File(filePath.toString());
        try (OutputStream outStream = new FileOutputStream(fileNew)) {
            outStream.write(file.getBytes());
            return new FileResponse("/api/v1/files/" + fileId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //TODO: check
//        try {
//            file.transferTo(fileNew);
//
//            return new FileResponse("/api/v1/files/" + fileId);
//        } catch (IOException e){
//            System.out.println(e.getMessage());
//            throw new RuntimeException("lôi trong qua trinh upload file");
//        }
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
        List<String> fileExtentions = List.of("png", "jpg", "jpeg");
        return fileExtentions.contains(fileExtention);
    }

    public byte[] readFile(String id) {
        Path filePath = rootPath.resolve(id);
        File file = new File(filePath.toString());
        if(!file.exists()){
            throw new NotFoundException("Not found file id = " + id);
        }
        try {
            return Files.readAllBytes(filePath);
        } catch (IOException e){
            throw new RuntimeException("loi trong qua trinh doc file");
        }
    }

    public void fileDelete(String id){
        Path filePath = rootPath.resolve(id);
        File file = new File(filePath.toString());
        if(!file.exists()){
            throw new NotFoundException("Not found file id = " + id);
        }
        file.delete();
    }
}
