package com.wsb.libraryapi.controllers;

import com.wsb.libraryapi.dtos.BookDTO;
import com.wsb.libraryapi.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<BookDTO> getBooks() {
        return bookService.listBooks();
    }

    @PostMapping
    public ResponseEntity<BookDTO> postBook(@RequestBody BookDTO bookDTO) {
        BookDTO savedBook = bookService.saveBook(bookDTO);

        return new ResponseEntity(savedBook, HttpStatus.CREATED);
    }
}
