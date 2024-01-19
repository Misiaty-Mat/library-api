package com.wsb.libraryapi.services;

import com.wsb.libraryapi.dtos.BookDTO;
import com.wsb.libraryapi.entities.Book;
import com.wsb.libraryapi.repositories.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceImplTest {

    @Autowired
    BookService bookService;

    @Autowired
    BookRepository bookRepository;

    BookDTO newBookDto;

    @BeforeEach
    void setUp() {
        newBookDto = BookDTO.builder()
                .id(null)
                .name("Harry Potter")
                .author("JK Rowling")
                .category("Fantasy")
                .tag("Wizards")
                .isbn("6412313")
                .available(true)
                .build();
    }

    @Test
    void saveBookShouldCreateNewBook() {
        BookDTO bookDTO = bookService.saveBook(newBookDto);

        assertNotNull(bookDTO.getId());
        Book book = bookRepository.findById(bookDTO.getId()).get();
        assertNotNull(book);
        bookRepository.delete(book);
    }

    @Test
    void getCategoriesShouldFindAllCategories() {
        List<String> categories = bookService.getCategories();

        assertEquals(bookRepository.findDistinctCategories(), categories);
    }
}