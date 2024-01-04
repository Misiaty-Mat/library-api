package com.wsb.libraryapi.repositories;

import com.wsb.libraryapi.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

    @Query("select b from Book b where b.available = false")
    Optional<Book> findByAvailableFalse();

    List<Book> findAllByCategoryAllIgnoreCase(String category);

}