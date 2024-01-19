package com.ivanzkyanto.vivlio.repository.gateway;

import com.ivanzkyanto.vivlio.model.Book;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface BookGatewayRepository {

    Page<Book> findAll(Integer size, Integer pageNumber, String sort, String direction);

    Optional<Book> findById(String id);

    Optional<Book> findByTitle(String title);

    Book save(Book book);

    Book updateById(String id, Book book);

    void deleteById(String id);

}
