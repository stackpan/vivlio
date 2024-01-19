package com.ivanzkyanto.vivlio.controller;

import com.ivanzkyanto.vivlio.controller.dto.*;
import com.ivanzkyanto.vivlio.model.Book;
import com.ivanzkyanto.vivlio.service.BookService;
import com.ivanzkyanto.vivlio.util.BookMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    private final BookMapper bookMapper;

    @GetMapping
    @Operation(summary = "Get all books", description = "Get all books")
    public ResponseEntity<WebResponse<List<BookResponse>>> getAll(
            @RequestParam(value = "size", required = false, defaultValue = "3") Integer size,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "sort", required = false, defaultValue = "title") String sort,
            @RequestParam(value = "direction", required = false, defaultValue = "asc") String direction
    ) {
        Page<Book> pagedBook = bookService.findAll(size, page, sort, direction);

        List<BookResponse> data = pagedBook.stream()
                .map(bookMapper::toResponse)
                .map(item -> item
                        .add(linkTo(methodOn(this.getClass()).getById(item.getId())).withSelfRel()
                        ))
                .toList();

        Pagination pagination = Pagination.builder()
                .currentPage(pagedBook.getPageable().getPageNumber())
                .totalPages(pagedBook.getTotalPages())
                .totalItems(pagedBook.getTotalElements())
                .pagePerItem(pagedBook.getPageable().getPageSize())
                .totalCurrentPageItems(data.size())
                .build();

        if (pagedBook.hasPrevious()) {
            pagination.add(linkTo(methodOn(this.getClass()).getAll(size, page - 1, sort, direction)).withRel("previous"));
        }

        if (pagedBook.hasNext()) {
            pagination.add(linkTo(methodOn(this.getClass()).getAll(size, page + 1, sort, direction)).withRel("next"));
        }

        WebResponse<List<BookResponse>> response = new WebResponse<>("Success.", data, pagination);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<WebResponse<List<BookResponse>>> getAll() {
        return getAll(null, null, null, null);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get a book",
            description = "Get book by id",
            responses = {
                    @ApiResponse(
                            responseCode = "404",
                            description = "Book not found",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    )
            }
    )
    public ResponseEntity<WebResponse<BookResponse>> getById(@PathVariable("id") String id) {
        Book book = bookService.findById(id);
        BookResponse data = bookMapper.toResponse(book)
                .add(linkTo(methodOn(this.getClass()).getAll()).withRel("books"))
                .add(linkTo(methodOn(this.getClass()).getReviews(id)).withRel("reviews"));

        WebResponse<BookResponse> response = new WebResponse<>("Success.", data);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Operation(
            summary = "Create book",
            description = "Create book",
            responses = {
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid response body",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    )
            }
    )
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<WebResponse<BookResponse>> create(@RequestBody @Valid BookRequest request) {
        Book model = bookMapper.toModel(request);
        Book book = bookService.create(model);

        BookResponse data = bookMapper.toResponse(book);

        WebResponse<BookResponse> response = new WebResponse<>("Book created successfully.", data);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update book",
            description = "Update book info",
            responses = {
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid response body",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Book not found",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    )
            }
    )
    public ResponseEntity<WebResponse<BookResponse>> update(@PathVariable("id") String id, @RequestBody @Valid BookRequest request) {
        Book model = bookMapper.toModel(request);
        model.setId(id);

        Book updated = bookService.update(model);
        BookResponse data = bookMapper.toResponse(updated);

        WebResponse<BookResponse> response = new WebResponse<>("Book updated successfully.", data);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete book",
            description = "Delete book",
            responses = {
                    @ApiResponse(
                            responseCode = "404",
                            description = "Book not found",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    )
            }
    )
    public ResponseEntity<WebResponse<Void>> delete(@PathVariable("id") String id) {
        bookService.deleteById(id);
        return ResponseEntity.ok(new WebResponse<>("Book deleted successfully."));
    }

    @GetMapping("/{id}/reviews")
    @Operation(
            summary = "Get book reviews",
            description = "Get book reviews by id",
            responses = {
                    @ApiResponse(
                            responseCode = "404",
                            description = "Book not found",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    )
            }
    )
    public ResponseEntity<WebResponse<List<ReviewResponse>>> getReviews(@PathVariable("id") String id) {
        List<String> reviews = bookService.findReviewsById(id);
        List<ReviewResponse> data = reviews.stream()
                .map(ReviewResponse::new)
                .map(item -> item
                        .add(linkTo(methodOn(this.getClass()).getById(id)).withRel("book"))
                )
                .toList();

        WebResponse<List<ReviewResponse>> response = new WebResponse<>("Success.", data);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/reviews")
    @Operation(
            summary = "Add book reviews",
            description = "Add book reviews by id",
            responses = {
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid response body",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Book not found",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    )
            }
    )
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<WebResponse<ReviewResponse>> addReview(@PathVariable("id") String id, @RequestBody @Valid ReviewRequest request) {
        String review = bookService.addReviewById(id, request.getReview());
        ReviewResponse data = new ReviewResponse(review);

        WebResponse<ReviewResponse> response = new WebResponse<>("Successfully add review.", data);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
