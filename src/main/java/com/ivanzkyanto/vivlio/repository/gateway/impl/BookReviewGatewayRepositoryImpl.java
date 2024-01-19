package com.ivanzkyanto.vivlio.repository.gateway.impl;

import com.ivanzkyanto.vivlio.entity.BookEntity;
import com.ivanzkyanto.vivlio.entity.BookReviewEntity;
import com.ivanzkyanto.vivlio.model.Book;
import com.ivanzkyanto.vivlio.repository.BookReviewRepository;
import com.ivanzkyanto.vivlio.repository.gateway.BookReviewGatewayRepository;
import com.ivanzkyanto.vivlio.util.UUIDs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class BookReviewGatewayRepositoryImpl implements BookReviewGatewayRepository {

    private final BookReviewRepository bookReviewRepository;

    @Override
    public List<String> findReviewsById(String id) {
        List<BookReviewEntity> reviews = bookReviewRepository.findAllByBook_Id(UUIDs.toUuid(id, Book.class));

        return reviews.stream()
                .map(BookReviewEntity::getBody)
                .collect(Collectors.toList());
    }

    @Override
    public String addReviewById(String id, String review) {
        BookEntity book = BookEntity.builder()
                .id(UUIDs.toUuid(id, Book.class))
                .build();

        BookReviewEntity reviewEntity = BookReviewEntity.builder()
                .book(book)
                .body(review)
                .build();

        bookReviewRepository.save(reviewEntity);

        return review;
    }

}
