package com.ivanzkyanto.vivlio.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {

    private Integer id;

    private String title;

    private String language;

    private String year;

    private String author;

}
