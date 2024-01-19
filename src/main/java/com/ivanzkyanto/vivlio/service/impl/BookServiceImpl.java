package com.ivanzkyanto.vivlio.service.impl;

import com.ivanzkyanto.vivlio.exception.ResourceAlreadyExistsException;
import com.ivanzkyanto.vivlio.exception.ResourceNotFoundException;
import com.ivanzkyanto.vivlio.model.Book;
import com.ivanzkyanto.vivlio.repository.gateway.BookGatewayRepository;
import com.ivanzkyanto.vivlio.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookGatewayRepository bookRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Book findById(Integer id) {
        return findByIdOrAbort(id);
    }

    @Transactional
    @Override
    public Book create(Book book) {
        Optional<Book> optionalAvailableBook = bookRepository.findByTitle(book.getTitle());

        if (optionalAvailableBook.isPresent())
            throw new ResourceAlreadyExistsException(Book.class.getSimpleName(), optionalAvailableBook.get().getId().toString());

        return bookRepository.save(book);
    }

    @Transactional
    @Override
    public Book update(Book book) {
        Integer id = book.getId();
        findByIdOrAbort(id);
        return bookRepository.updateById(id, book);
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        findByIdOrAbort(id);
        bookRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<String> findReviewsById(Integer id) {
        findByIdOrAbort(id);
        return bookRepository.findReviewsById(id);
    }

    @Transactional
    @Override
    public String addReviewById(Integer id, String review) {
        findByIdOrAbort(id);
        return bookRepository.addReviewById(id, review);
    }

    private Book findByIdOrAbort(Integer id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Book.class.getSimpleName(), id.toString()));
    }
}
