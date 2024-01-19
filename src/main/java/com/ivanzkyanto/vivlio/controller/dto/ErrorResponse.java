package com.ivanzkyanto.vivlio.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
public class ErrorResponse {

    private String title;

    private String message;

    private Map<String, String> details;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;

    public ErrorResponse(String title, String message, Map<String, String> details) {
        this.title = title;
        this.message = message;
        this.details = details;
    }

    public ErrorResponse(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public ErrorResponse(String title) {
        this.title = title;
    }
}
