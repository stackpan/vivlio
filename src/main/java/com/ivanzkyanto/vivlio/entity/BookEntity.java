package com.ivanzkyanto.vivlio.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String language;

    private Integer year;

    private String author;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "reviews", joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "body")
    private List<String> reviews = new ArrayList<>();

}
