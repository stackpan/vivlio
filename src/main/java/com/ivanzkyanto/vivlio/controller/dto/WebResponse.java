package com.ivanzkyanto.vivlio.controller.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebResponse<T> {

    private String message;

    private T data;

    private Pagination pagination;

    public WebResponse(String message) {
        this.message = message;
    }

    public WebResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }
}
