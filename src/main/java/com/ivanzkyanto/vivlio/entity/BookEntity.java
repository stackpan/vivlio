package com.ivanzkyanto.vivlio.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<BookReviewEntity> reviews;

    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Dimension {

        private Double width;

        private Double height;

    }

}
