package com.ivanzkyanto.vivlio.controller;

import com.ivanzkyanto.vivlio.controller.dto.BookRequest;
import com.ivanzkyanto.vivlio.controller.dto.BookResponse;
import com.ivanzkyanto.vivlio.controller.dto.ReviewRequest;
import com.ivanzkyanto.vivlio.controller.dto.ReviewResponse;
import com.ivanzkyanto.vivlio.model.Book;
import com.ivanzkyanto.vivlio.service.BookService;
import com.ivanzkyanto.vivlio.util.BookMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    private final BookMapper bookMapper;

    @GetMapping
    public ResponseEntity<List<BookResponse>> getAll() {
        List<Book> books = bookService.findAll();
        List<BookResponse> response = books.stream()
                .map(bookMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getById(@PathVariable("id") String id) {
        Book book = bookService.findById(id);
        BookResponse response = bookMapper.toResponse(book);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<BookResponse> create(@RequestBody @Valid BookRequest request) {
        Book model = bookMapper.toModel(request);
        Book book = bookService.create(model);
        BookResponse response = bookMapper.toResponse(book);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> update(@PathVariable("id") String id, @RequestBody @Valid BookRequest request) {
        Book model = bookMapper.toModel(request);
        model.setId(id);

        Book updated = bookService.update(model);
        BookResponse response = bookMapper.toResponse(updated);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        bookService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<List<ReviewResponse>> getReviews(@PathVariable("id") String id) {
        List<String> reviews = bookService.findReviewsById(id);
        List<ReviewResponse> response = reviews.stream()
                .map(ReviewResponse::new)
                .toList();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/reviews")
    public ResponseEntity<ReviewResponse> addReview(@PathVariable("id") String id, @RequestBody @Valid ReviewRequest request) {
        String review = bookService.addReviewById(id, request.getReview());
        ReviewResponse response = new ReviewResponse(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
