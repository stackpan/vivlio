package com.ivanzkyanto.vivlio.service;

import com.ivanzkyanto.vivlio.model.Book;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService {

    Page<Book> findAll(Integer size, Integer pageNumber, String sort, String direction);

    Book findById(String id);

    Book create(Book book);

    Book update(Book book);

    void deleteById(String id);

    List<String> findReviewsById(String id);

    String addReviewById(String id, String review);
}
