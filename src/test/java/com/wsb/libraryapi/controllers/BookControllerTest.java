package com.wsb.libraryapi.controllers;

import com.wsb.libraryapi.entities.Book;
import com.wsb.libraryapi.repositories.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
class BookControllerTest {

    @MockBean
    BookRepository bookRepository;

    @Autowired
    WebApplicationContext wac;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .apply(springSecurity())
                .build();
    }

    @Test
    void getBooksShouldReturnCorrectNumberOfBooksAndHaveOKStatus() throws Exception {
        when(bookRepository.findAll()).thenReturn(List.of(Book.builder().build()));
        when(bookRepository.count()).thenReturn(1L);

        mockMvc.perform(get("https://localhost:8443/api/v1/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is((int) bookRepository.count())));
    }
}