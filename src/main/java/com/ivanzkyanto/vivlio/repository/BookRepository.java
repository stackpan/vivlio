package com.ivanzkyanto.vivlio.repository;

import com.ivanzkyanto.vivlio.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {

    Optional<BookEntity> findByTitle(String title);

}
