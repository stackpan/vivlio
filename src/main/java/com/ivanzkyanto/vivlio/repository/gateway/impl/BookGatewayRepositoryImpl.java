package com.ivanzkyanto.vivlio.repository.gateway.impl;

import com.ivanzkyanto.vivlio.entity.BookEntity;
import com.ivanzkyanto.vivlio.model.Book;
import com.ivanzkyanto.vivlio.repository.BookRepository;
import com.ivanzkyanto.vivlio.repository.gateway.BookGatewayRepository;
import com.ivanzkyanto.vivlio.util.BookMapper;
import com.ivanzkyanto.vivlio.util.UUIDs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class BookGatewayRepositoryImpl implements BookGatewayRepository {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    @Override
    public List<Book> findAll() {
        List<BookEntity> books = bookRepository.findAll();

        return books.stream()
                .map(bookMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Book> findById(String id) {
        Optional<BookEntity> optionalEntity = bookRepository.findById(toUuid(id));

        if (optionalEntity.isEmpty())
            return Optional.empty();

        Book book = bookMapper.toModel(optionalEntity.get());
        return Optional.of(book);
    }

    @Override
    public Optional<Book> findByTitle(String title) {
        Optional<BookEntity> optionalEntity = bookRepository.findByTitle(title);

        if (optionalEntity.isEmpty())
            return Optional.empty();

        Book book = bookMapper.toModel(optionalEntity.get());
        return Optional.of(book);
    }

    @Override
    public Book save(Book book) {
        BookEntity entity = bookMapper.toEntity(book);

        BookEntity saved = bookRepository.save(entity);

        return bookMapper.toModel(saved);
    }

    @Override
    public Book updateById(String id, Book book) {
        BookEntity entityForUpdate = safeFindById(id);

        BookEntity newEntity = bookMapper.toEntity(book);

        bookMapper.copyValues(newEntity, entityForUpdate);

        BookEntity updatedEntity = bookRepository.save(entityForUpdate);
        return bookMapper.toModel(updatedEntity);
    }

    @Override
    public void deleteById(String id) {
        bookRepository.deleteById(toUuid(id));
    }

    private BookEntity safeFindById(String id) {
        Optional<BookEntity> optionalBookEntity = bookRepository.findById(toUuid(id));

        assert optionalBookEntity.isPresent();
        return optionalBookEntity.get();
    }

    private UUID toUuid(String id) {
        return UUIDs.toUuid(id, Book.class);
    }
}
