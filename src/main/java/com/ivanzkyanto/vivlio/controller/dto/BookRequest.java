package com.ivanzkyanto.vivlio.controller.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {
    
    @NotEmpty(message = "{required.field}")
    @Size(min = 1, max = 200, message = "{invalid.field}")
    private String title;

    @Size(min = 1, max = 1000, message = "{invalid.field}")
    private String description;

    @NotEmpty(message = "{required.field}")
    @Size(min = 1, max = 50, message = "{invalid.field}")
    private String language;

    @PositiveOrZero(message = "{invalid.field}")
    private String year;

    @Size(min = 1, max = 200, message = "{invalid.field}")
    private String author;

    @Positive(message = "{invalid.field}")
    private Integer totalPages;

    private Dimension dimension;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Dimension {

        @Positive(message = "{invalid.field}")
        private Double width;

        @Positive(message = "{invalid.field}")
        private Double height;

    }

}
