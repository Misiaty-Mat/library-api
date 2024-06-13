package com.wsb.libraryapi.services;

import com.wsb.libraryapi.dtos.BookDTO;
import com.wsb.libraryapi.entities.Book;
import com.wsb.libraryapi.mappers.BookMapper;
import com.wsb.libraryapi.repositories.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private BookMapper bookMapper;
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

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
        Book book = Book.builder().build();
        when(bookMapper.toEntity(newBookDto)).thenReturn(book);
        when(bookRepository.save(book)).thenReturn(book);
        when(bookMapper.toDto(book)).thenReturn(newBookDto);

        bookService.saveBook(newBookDto);

        verify(bookRepository).save(book);
    }

    @Test
    void getCategoriesShouldFindAllCategories() {
        List<String> expectedCategories = List.of("Fantasy", "Adventure");
        when(bookRepository.findDistinctCategories()).thenReturn(expectedCategories);

        List<String> categories = bookService.getCategories();

        assertEquals(expectedCategories, categories);
    }
}