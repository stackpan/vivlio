package com.ivanzkyanto.vivlio.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    private String description;

    private String language;

    private Integer year;

    private String author;

    @Column(name = "total_pages")
    private Integer totalPages;

    @Embedded
    private Dimension dimension;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "reviews", joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "body")
    private List<String> reviews = new ArrayList<>();

    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Dimension {

        private Double width;

        private Double height;

    }

}
