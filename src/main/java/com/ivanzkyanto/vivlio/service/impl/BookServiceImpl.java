package com.ivanzkyanto.vivlio.service.impl;

import com.ivanzkyanto.vivlio.exception.ResourceAlreadyExistsException;
import com.ivanzkyanto.vivlio.exception.ResourceNotFoundException;
import com.ivanzkyanto.vivlio.model.Book;
import com.ivanzkyanto.vivlio.repository.gateway.BookGatewayRepository;
import com.ivanzkyanto.vivlio.repository.gateway.BookReviewGatewayRepository;
import com.ivanzkyanto.vivlio.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookGatewayRepository bookRepository;

    private final BookReviewGatewayRepository bookReviewRepository;

    @Transactional(readOnly = true)
    @Override
    public Page<Book> findAll(Integer size, Integer pageNumber, String sort, String direction) {
        return bookRepository.findAll(size, pageNumber, sort, direction);
    }

    @Transactional(readOnly = true)
    @Override
    public Book findById(String id) {
        return findByIdOrAbort(id);
    }

    @Transactional
    @Override
    public Book create(Book book) {
        Optional<Book> optionalAvailableBook = bookRepository.findByTitle(book.getTitle());

        if (optionalAvailableBook.isPresent())
            throw new ResourceAlreadyExistsException(Book.class.getSimpleName(), optionalAvailableBook.get().getId());

        return bookRepository.save(book);
    }

    @Transactional
    @Override
    public Book update(Book book) {
        String id = book.getId();
        findByIdOrAbort(id);
        return bookRepository.updateById(id, book);
    }

    @Transactional
    @Override
    public void deleteById(String id) {
        findByIdOrAbort(id);
        bookRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<String> findReviewsById(String id) {
        findByIdOrAbort(id);
        return bookReviewRepository.findReviewsById(id);
    }

    @Transactional
    @Override
    public String addReviewById(String id, String review) {
        findByIdOrAbort(id);
        return bookReviewRepository.addReviewById(id, review);
    }

    private Book findByIdOrAbort(String id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Book.class.getSimpleName(), id));
    }
}
