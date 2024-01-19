package com.ivanzkyanto.vivlio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private String id;

    private String title;

    private String description;

    private String language;

    private Integer year;

    private String author;

    private Integer totalPages;

    private Dimension dimension;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Dimension {

        private Double width;

        private Double height;

    }

}
