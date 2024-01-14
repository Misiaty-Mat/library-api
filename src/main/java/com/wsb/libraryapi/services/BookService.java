package com.wsb.libraryapi.services;

import com.wsb.libraryapi.dtos.BookDTO;
import com.wsb.libraryapi.entities.Book;

import java.util.List;
import java.util.UUID;

public interface BookService {
    List<BookDTO> listBooks(String category);
    BookDTO getBookById(UUID id);
    BookDTO saveBook(BookDTO bookDTO);
    List<String> getCategories();
}
