package com.ivanzkyanto.vivlio.service;

import com.ivanzkyanto.vivlio.model.Book;

import java.util.List;

public interface BookService {

    List<Book> findAll();

    Book findById(Integer id);

    Book create(Book book);

    Book update(Book book);

    void deleteById(Integer id);

    List<String> findReviewsById(Integer id);

    String addReviewById(Integer id, String review);
}
