package com.example.springhomework001.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    private String        message;
    private T             payload;
    private Object        status;
    private LocalDateTime time;

    public ApiResponse(String message, T payload, Object status) {
        this.message = message;
        this.payload = payload;
        this.status = status;
        this.time = LocalDateTime.now();
    }

}