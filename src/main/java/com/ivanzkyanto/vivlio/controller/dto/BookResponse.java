package com.ivanzkyanto.vivlio.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {

    private String id;

    private String title;

    private String description;

    private String language;

    private String year;

    private String author;

    private Dimension dimension;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Dimension {

        private Double width;

        private Double height;

    }

}
