package com.ivanzkyanto.vivlio.util;

import com.ivanzkyanto.vivlio.controller.dto.BookRequest;
import com.ivanzkyanto.vivlio.controller.dto.BookResponse;
import com.ivanzkyanto.vivlio.entity.BookEntity;
import com.ivanzkyanto.vivlio.model.Book;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookMapper {

    private final ModelMapper mapper;

    public Book toModel(BookEntity entity) {
        return mapper.map(entity, Book.class);
    }

    public Book toModel(BookRequest request) {
        return mapper.map(request, Book.class);
    }

    public BookEntity toEntity(Book book) {
        return mapper.map(book, BookEntity.class);
    }

    public void copyValues(BookEntity source, BookEntity destination) {
        mapper.map(source, destination);
    }

    public BookRequest toRequest(Book book) {
        return mapper.map(book, BookRequest.class);
    }

    public BookResponse toResponse(Book book) {
        return mapper.map(book, BookResponse.class);
    }

}
