package com.ivanzkyanto.vivlio.repository;

import com.ivanzkyanto.vivlio.entity.BookReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BookReviewRepository extends JpaRepository<BookReviewEntity, UUID> {

    List<BookReviewEntity> findAllByBook_Id(UUID bookId);

}
