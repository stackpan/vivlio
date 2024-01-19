package com.ivanzkyanto.vivlio.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse extends RepresentationModel<BookResponse> {

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
