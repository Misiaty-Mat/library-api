package com.wsb.libraryapi.services;

import com.wsb.libraryapi.dtos.BookDTO;
import com.wsb.libraryapi.entities.Book;

import java.util.List;

public interface BookService {
    List<BookDTO> listBooks();
    BookDTO saveBook(BookDTO bookDTO);
}
