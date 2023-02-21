package com.example.demo.controller;

import com.example.demo.request.CreatePostRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

// su dung 1 trong 2 anocation @Controller or @RestController
// muc dich tao ra 1 obj webController dua vao container de quan ly doi tuong
// vi du app web
// controller: userService, productController
// anocation danh dau tao ra 1 obj duy nhat Bean dua vao 1 container tien hanh luu tru
// neu co 1 class khac can su dung controller tren => su dung cu phap cua no => de goi cac phuong thuc => tim kiem trong container tren tim toi bean sau do inject vao class ma ko phai truc tiep khoi tao doi tuong trong class
// khai niem IOC (inversion of controll) tao ra quan ly Bean => inject den noi can su dung
// cach su dung bean trong 1 class khac: dependency injection (DI) => desine button
// cach tao ra bean nhu sau
// C1 dung anocation danh dau len class
// C2 dung anocation la Bean danh dau len methol
// ban chat cua Bean la 1 object

@RestController
// RestController tra ve du lieu json cho client
// Controller tra ve giao dien (view) html neu su dung templace enzym trong spring boot + ResponseBody = RestController

public class WebController {
    //dinh nghia api
    //phan chia theo http status code
    //GET POST PUT DELETE
    //http status code 1x 2x 3x 4x 5x
    //2x respon thanh cong
    //4x respon loi client
    //5x respon loi server

    @RequestMapping(method = RequestMethod.GET, path = "/")
    // RequestMapping dinh nghia cac path api url + http methol
    // su dung cho tat ca cac truong hop GET POST PUT DELETE
    // GetMapping su dung cho GET
    // PostMapping su dung cho POST
    // PutMapping su dung cho PUT
    // DeleteMapping su dung cho DELETE
    public String getHome(){
        return "home";
    }

    @GetMapping("/blog")
    public String getBlog(){
        return "blog";
    }
    //set them loai du lieu nhan dc va loai du lieu tra ve su dung consumers va produces (2 thuoc tinh trong GetMapping)

    @GetMapping(value = "/blog2", produces = MediaType.APPLICATION_JSON_VALUE)
    // du lieu tra ve can thiet trong mot so truong hop khi vi du tra ve 1 html thi du lieu la text/html hoac pdf
    // du lieu nay duoc set trong header cua resquest de chi dinh kieu du lieu tra ve trinh duyet su dung du lieu do de hien thi tuong ung
    public String getBlog2(){
        return "blog2";
        //ktra trong header request/ content type=> application/json
        //status code mac dinh thanh cong  = 200
    }
    @GetMapping(value = "/blog3", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String getBlog3(){
        return "blog3";
        // HttpStatus.CREATED set status code 201 thanh cong tao moi doi tuong (giu cltr kick vao HttpStatus)
        // HttpStatus.NO_CONTENT set status code 204
    }
    // lay chi tiet 1 blog /blogs/1 /blogs/2
    @GetMapping(value = "/blogs/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    // de doc gia tri id su dung annocation @PathVariable
    // thiet lap moi lien ket neu ten bien khac
    // public String getBlogById(@PathVariable(name = "id") String blogId){
    public String getBlogById(@PathVariable String id){
        return "blog id = " + id;
    }

    //url queryString dung loc du lieu dung @RequestParam
    // /blogs?title=sach&year=2009
    // required / default set tuong tu name

    @GetMapping(value = "/posts", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getBlogByParam(@RequestParam(required = false) String title, @RequestParam(required = false) String year){
        return "title = " + title + " - " + "year = " + year;
    }

    // tao moi bai viet id title author
    // tao class bat du lieu CreatePostRequest
    // dung annocation RequestBody de set du lieu vao bien request
    @PostMapping(value = "/posts", produces = MediaType.APPLICATION_JSON_VALUE)
    public String createPost(@RequestBody CreatePostRequest request){
        return String.format("ID : %d - Title : %s - Author : %s", request.getId(), request.getTitle(), request.getAuthor());
    }

}
