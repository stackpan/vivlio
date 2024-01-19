package com.ivanzkyanto.vivlio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private Integer id;

    private String title;

    private String language;

    private Integer year;

    private String author;

}
