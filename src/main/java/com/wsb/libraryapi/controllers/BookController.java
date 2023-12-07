package com.wsb.libraryapi.controllers;

import com.wsb.libraryapi.dtos.BookDTO;
import com.wsb.libraryapi.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {
    public static final String BOOK_PATH = "/api/v1/books";

    private final BookService bookService;

    @GetMapping(BOOK_PATH)
    public List<BookDTO> getBooks() {
        return bookService.listBooks();
    }

    @PostMapping(BOOK_PATH)
    public ResponseEntity<BookDTO> postBook(@RequestBody BookDTO bookDTO) {
        BookDTO savedBook = bookService.saveBook(bookDTO);

        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }
}
