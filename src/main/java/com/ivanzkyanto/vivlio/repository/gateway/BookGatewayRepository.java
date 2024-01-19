package com.ivanzkyanto.vivlio.repository.gateway;

import com.ivanzkyanto.vivlio.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookGatewayRepository {

    List<Book> findAll();

    Optional<Book> findById(String id);

    Optional<Book> findByTitle(String title);

    Book save(Book book);

    Book updateById(String id, Book book);

    void deleteById(String id);

}
