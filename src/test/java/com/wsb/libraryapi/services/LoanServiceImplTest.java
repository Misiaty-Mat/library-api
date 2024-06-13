package com.wsb.libraryapi.services;

import com.wsb.libraryapi.dtos.LoanDTO;
import com.wsb.libraryapi.dtos.LoanDetailsDto;
import com.wsb.libraryapi.dtos.UserDTO;
import com.wsb.libraryapi.entities.Book;
import com.wsb.libraryapi.entities.Loan;
import com.wsb.libraryapi.entities.User;
import com.wsb.libraryapi.mappers.LoanMapper;
import com.wsb.libraryapi.mappers.UserMapper;
import com.wsb.libraryapi.repositories.BookRepository;
import com.wsb.libraryapi.repositories.LoanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoanServiceImplTest {

    @Mock
    private BookRepository bookRepository;
    @Mock
    private UserMapper userMapper;
    @Mock
    private UserServiceImpl userService;
    @Mock
    private LoanMapper loanMapper;
    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanServiceImpl loanService;


    UUID BOOK_ID = UUID.randomUUID();
    UUID USER_ID = UUID.randomUUID();
    LoanDTO LOAN_DTO;

    @BeforeEach
    void setUp() {
        LOAN_DTO = LoanDTO.builder()
                .book_id(BOOK_ID)
                .user_id(USER_ID)
                .build();
    }

    @Test
    void listLoans() {
        Loan loan = Loan.builder().build();
        LoanDetailsDto detailsDto = LoanDetailsDto.builder().build();
        when(loanRepository.findAll()).thenReturn(List.of(loan));
        when(loanMapper.toDto(loan)).thenReturn(detailsDto);
        List<LoanDetailsDto> loanDetailsDtos = loanService.listLoans();

        assertFalse(loanDetailsDtos.isEmpty());
    }

    @Test
    void saveLoan() {
        Loan loan = Loan.builder().build();
        Book book = Book.builder().build();
        UserDTO userDTO = UserDTO.builder().build();
        LoanDetailsDto loanDetailsDto = LoanDetailsDto.builder().build();
        when(loanMapper.toEntity(LOAN_DTO)).thenReturn(loan);
        when(loanRepository.save(loan)).thenReturn(loan);
        when(loanMapper.toDto(loan)).thenReturn(loanDetailsDto);
        when(bookRepository.findById(BOOK_ID)).thenReturn(Optional.ofNullable(book));
        when(userService.findUserById(USER_ID)).thenReturn(userDTO);
        when(userMapper.toEntity(userDTO)).thenReturn(User.builder().build());

        loanService.saveLoan(LOAN_DTO);

        verify(bookRepository).save(book);
        verify(loanRepository).save(loan);
    }
}