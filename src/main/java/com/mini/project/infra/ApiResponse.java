package com.mini.project.infra;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
@Builder
public class ApiResponse<T> {
    private HttpStatus status;
    private LocalDateTime reqDatetime;
    private T data;
    private int count;

    public ApiResponse(HttpStatus status, T data) {
        this.status = status;
        this.reqDatetime = LocalDateTime.now();
        this.data = data;
        this.count = data instanceof List ? ((List<?>) data).size() : 1;
    }
    
//    public static <T> String convertToJson(ApiResponse<T> response) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // JSON을 보기 좋게 출력
//        try {
//            return objectMapper.writeValueAsString(response);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
}
