package user.example.usermanager.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import user.example.usermanager.model.response.FileResponse;
import user.example.usermanager.service.FileService;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class FileController {
    private final FileService fileService;
    //upload file => key = file tren request
    @PostMapping("files")
    public ResponseEntity<?> uploadFile(@ModelAttribute("file") MultipartFile file){
        FileResponse fileResponse = fileService.uploadFile(file);
        return ResponseEntity.ok(fileResponse);
    }

    //xem thong tin file
    // neu ko dung ResponseEntity them , produce vao duong dan de set content type
    @GetMapping("files/{id}")
    public ResponseEntity<?> readFile(@PathVariable String id){
        byte[] bytes = fileService.readFile(id);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
    }
}
