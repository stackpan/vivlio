package com.ivanzkyanto.vivlio.repository;

import com.ivanzkyanto.vivlio.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<BookEntity, UUID> {

    Optional<BookEntity> findByTitle(String title);

}
