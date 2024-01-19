package com.wsb.libraryapi.services;

import com.wsb.libraryapi.dtos.LoanDTO;
import com.wsb.libraryapi.dtos.LoanDetailsDto;
import com.wsb.libraryapi.entities.Book;
import com.wsb.libraryapi.entities.Loan;
import com.wsb.libraryapi.entities.User;
import com.wsb.libraryapi.mappers.LoanMapper;
import com.wsb.libraryapi.mappers.UserMapper;
import com.wsb.libraryapi.repositories.BookRepository;
import com.wsb.libraryapi.repositories.LoanRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {
    private final LoanRepository loanRepository;
    private final LoanMapper loanMapper;
    private final UserMapper userMapper;
    private final BookRepository bookRepository;
    private final UserService userService;

    @Override
    public List<LoanDetailsDto> listLoans() {
        return mapLoansToDto(loanRepository.findAll());
    }

    @Override
    public List<LoanDetailsDto> listLoansOfCurrentUser() {
        return mapLoansToDto(loanRepository.findByUser(userService.getCurrentUser()));
    }

    @Override
    public void deleteLoan(UUID loanId) {
        Loan loan = loanRepository.findById(loanId).orElseThrow(EntityNotFoundException::new);
        Book book = loan.getBook();
        book.setAvailable(true);
        loan.setReturned(true);
        bookRepository.save(book);
        loanRepository.save(loan);
    }

    @Override
    public LoanDetailsDto saveLoan(LoanDTO loanDTO) {
        if (loanDTO.getReturn_date() == null) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_YEAR, 14);
            loanDTO.setReturn_date(new Timestamp(calendar.getTime().getTime()));
        }

        Loan loan = loanMapper.toEntity(loanDTO);
        loan.setReturned(false);
        Book book = bookRepository.findById(loanDTO.getBook_id()).orElseThrow(EntityNotFoundException::new);
        User user = null;
        if (loanDTO.getUser_id() != null) {
            user = userMapper.toEntity(userService.findUserById(loanDTO.getUser_id()));
        }
        book.setAvailable(false);
        bookRepository.save(book);
        loan.setBook(book);

        loan.setUser(user == null ? userService.getCurrentUser() : user);
        return loanMapper.toDto(loanRepository.save(loan));
    }

    private List<LoanDetailsDto> mapLoansToDto(List<Loan> loans) {
        return loans.stream()
                .map(loanMapper::toDto).collect(Collectors.toList());
    }
}
