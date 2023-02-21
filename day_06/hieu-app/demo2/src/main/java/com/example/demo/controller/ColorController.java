package com.example.demo.controller;

import com.example.demo.model.Color;
import com.example.demo.service.ColorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ColorController {
    private final ColorService colorService;
    public ColorController(ColorService colorService){
        this.colorService = colorService;
    }

//    @GetMapping("random-color/{type}")
//    public ResponseEntity<?> getColorByType(@PathVariable Integer type){
//        String color = colorService.getColorByType(type);
//        return ResponseEntity.ok(color);
//    }
    @GetMapping("/random-color")
    public ResponseEntity<?> getColorByParamType(@RequestParam Integer type){
        String color = colorService.randomColor(type);
        return ResponseEntity.ok(color);
    }
}
