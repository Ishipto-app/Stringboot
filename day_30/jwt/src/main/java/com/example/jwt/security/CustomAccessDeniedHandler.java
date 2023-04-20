package com.example.jwt.security;

import com.example.jwt.response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

//xu ly loi 403
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // tao object
        ErrorResponse message = new ErrorResponse(HttpStatus.FORBIDDEN, "Bạn không có quyền");

        // convert sang json
        ObjectMapper objectMapper = new ObjectMapper();
        String messageJSON = objectMapper.writeValueAsString(message);

        //tra ve cho client
        response.addHeader("Content-type", "application/json");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(messageJSON);
    }
}
