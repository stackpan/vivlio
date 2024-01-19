package com.ivanzkyanto.vivlio.repository.gateway;

import com.ivanzkyanto.vivlio.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookGatewayRepository {

    List<Book> findAll();

    Optional<Book> findById(Integer id);

    Optional<Book> findByTitle(String title);

    Book save(Book book);

    Book updateById(Integer id, Book book);

    void deleteById(Integer id);

    List<String> findReviewsById(Integer id);

    String addReviewById(Integer id, String review);

}
