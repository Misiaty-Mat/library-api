package com.wsb.libraryapi.repositories;

import com.wsb.libraryapi.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
}