package com.ivanzkyanto.vivlio.repository.gateway.impl;

import com.ivanzkyanto.vivlio.entity.BookEntity;
import com.ivanzkyanto.vivlio.model.Book;
import com.ivanzkyanto.vivlio.repository.BookRepository;
import com.ivanzkyanto.vivlio.repository.gateway.BookGatewayRepository;
import com.ivanzkyanto.vivlio.util.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
    public Optional<Book> findById(Integer id) {
        Optional<BookEntity> optionalEntity = bookRepository.findById(id);

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
    public Book updateById(Integer id, Book book) {
        BookEntity entityForUpdate = new BookEntity();
        entityForUpdate.setId(id);

        BookEntity newEntity = bookMapper.toEntity(book);

        bookMapper.copyValues(newEntity, entityForUpdate);

        BookEntity updatedEntity = bookRepository.save(entityForUpdate);
        return bookMapper.toModel(updatedEntity);
    }

    @Override
    public void deleteById(Integer id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<String> findReviewsById(Integer id) {
        Optional<BookEntity> optionalBookEntity = bookRepository.findById(id);

        assert optionalBookEntity.isPresent();
        BookEntity entity = optionalBookEntity.get();

        List<String> reviews = entity.getReviews();

        if (Objects.isNull(reviews))
            return new ArrayList<>();

        return reviews;
    }

    @Override
    public String addReviewById(Integer id, String review) {
        Optional<BookEntity> optionalBookEntity = bookRepository.findById(id);

        assert optionalBookEntity.isPresent();
        BookEntity entity = optionalBookEntity.get();

        entity.getReviews().add(review);

        bookRepository.save(entity);
        return review;
    }
}
