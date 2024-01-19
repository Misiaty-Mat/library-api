package com.wsb.libraryapi.services;

import com.wsb.libraryapi.dtos.LoanDTO;
import com.wsb.libraryapi.dtos.LoanDetailsDto;
import com.wsb.libraryapi.entities.Book;
import com.wsb.libraryapi.entities.User;
import com.wsb.libraryapi.repositories.BookRepository;
import com.wsb.libraryapi.repositories.LoanRepository;
import com.wsb.libraryapi.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoanServiceImplTest {

    @Autowired
    LoanService loanService;

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    LoanDTO loanDTO;

    @BeforeEach
    void setUp() {
        Book book = bookRepository.findAll().get(0);
        User user = userRepository.findAll().get(0);
        loanDTO = LoanDTO.builder()
                .book_id(book.getId())
                .user_id(user.getId())
                .build();
    }

    @Test
    void listLoans() {
        List<LoanDetailsDto> loanDetailsDtos = loanService.listLoans();

        assertFalse(loanDetailsDtos.isEmpty());
    }

    @Test
    void saveLoan() {
        LoanDetailsDto detailsDto = loanService.saveLoan(loanDTO);
        assertNotNull(detailsDto.getId());
        loanRepository.deleteById(detailsDto.getId());
    }
}