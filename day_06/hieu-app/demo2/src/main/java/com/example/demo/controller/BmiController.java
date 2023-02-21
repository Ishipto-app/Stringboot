package com.example.demo.controller;

import com.example.demo.model.Bmi;
import com.example.demo.request.BmiRequest;
import com.example.demo.request.GetBmiByPostRequest;
import com.example.demo.service.BmiService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BmiController {
    private final BmiService bmiService;
    public BmiController(BmiService bmiService){
        this.bmiService = bmiService;
    }
    @GetMapping("/bmi")
    public Double getBmi(@RequestParam Double height, @RequestParam Double weight){
        Double bmi = bmiService.caculatorBmi(height, weight);
        return bmi;
    }
    @PostMapping("/bmi")
    public Double postBmiByData(@Valid @RequestBody BmiRequest request) {
        Double bmi = bmiService.caculatorBmi(request.getHeight(), request.getWeight());
        return bmi;
    }
//    @GetMapping("/bmi")
//    public ResponseEntity<?> getBmiByParams(@RequestParam String height, @RequestParam Float weight){
//        Bmi bmi = bmiService.getBmi(height, weight);
//        return ResponseEntity.ok(bmi);
//    }
//    @PostMapping("/bmi")
//    public ResponseEntity<?> postBmiByData(@Valid @RequestBody GetBmiByPostRequest request) {
//        Bmi bmi = bmiService.postBmiByData(request);
//        return ResponseEntity.ok(bmi);
//    }
}
