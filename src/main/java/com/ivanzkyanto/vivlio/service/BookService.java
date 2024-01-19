package com.ivanzkyanto.vivlio.service;

import com.ivanzkyanto.vivlio.model.Book;

import java.util.List;

public interface BookService {

    List<Book> findAll();

    Book findById(String id);

    Book create(Book book);

    Book update(Book book);

    void deleteById(String id);

    List<String> findReviewsById(String id);

    String addReviewById(String id, String review);
}
