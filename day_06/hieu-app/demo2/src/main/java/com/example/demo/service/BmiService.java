package com.example.demo.service;
import com.example.demo.model.Bmi;
import com.example.demo.request.GetBmiByPostRequest;
import org.springframework.stereotype.Service;

@Service
public class BmiService {
    private float bmiNumber;
    private Float covertHeightNumber(String height){
        String[] heightArr = height.split("m");
        float heightNum = Float.parseFloat(heightArr[0]) + Float.parseFloat(heightArr[1]) / 100;
        return heightNum;
    }
    public Bmi getBmi(String height, Float weight){
        float heightNum = covertHeightNumber(height);
        bmiNumber = weight/(heightNum * heightNum);
        Bmi bmi = new Bmi(height, weight, bmiNumber);
        return bmi;
    }
    public Bmi postBmiByData(GetBmiByPostRequest request){
        float heightNum = covertHeightNumber(request.getHeight());
        bmiNumber = request.getWeight() / (heightNum * heightNum);
        Bmi bmi = Bmi.builder()
                .height(request.getHeight())
                .weight(request.getWeight())
                .bmi(bmiNumber)
                .build();
        return bmi;
    }

}
