package com.example.demo.service;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Color;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ColorService {
    private Color color;
    private String[] colorNames = {"blue", "green", "black", "red"};

    private Integer randomIndex (Integer num) {
        return (int) Math.floor(Math.random() * num);
    }

    public String randomColor(Integer type){
        return switch (type) {
            case 1 -> randomColorName();
            case 2 -> randomHexColor();
            case 3 -> randomRbgColor();
            default -> throw new BadRequestException("type khong hop le");
        };
    }

    private String randomRbgColor() {
        Random rd = new Random();
        int r = rd.nextInt(256);
        int g = rd.nextInt(256);
        int b = rd.nextInt(256);
        return String.format("rgb(%d, %d, %d)", r, g, b);
    }

    private String randomHexColor() {
        Random rd = new Random();
        String color = "#";
        for (int i = 0; i < 6; i++) {
            color += Integer.toHexString(rd.nextInt(16));
        }
        return color;
    }

    private String randomColorName() {
        List<String> colors = new ArrayList<>(List.of("blue", "green", "black", "red"));
        Random rd = new Random();
        return colors.get(rd.nextInt(colors.toArray().length));
    }

    public Color getColorByType(Integer type){
        switch(type) {
            case 1:
                color = new Color(colorNames[randomIndex(colorNames.length)], type);
                return color;
            case 2:
                color = new Color(randomColorHexCode(), type);
                return color;
            case 3:
                color = new Color(randomColorRgbCode(), type);
                return color;
            default:
                throw new NotFoundException("Type không hợp lệ");
        }
    }
    private String randomColorHexCode () {
        String[] hexChar = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};
        String[] strColor = new String[7];
        for (int i = 0; i < strColor.length; i++) {
            strColor[i] = i == 0 ? "#" : hexChar[randomIndex(16)];
        }
        return String.join("", strColor);
    }
    private String randomColorRgbCode () {
        String[] strColor = new String[3];
        for (int i = 0; i < 3; i++) {
            int index = (int) Math.floor(Math.random()*256);
            strColor[i] = String.valueOf(index);
        }
        return "rgb(" + String.join(",", strColor) + ")";
    }
}
