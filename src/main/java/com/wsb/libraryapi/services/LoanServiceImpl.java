package com.wsb.libraryapi.services;

import com.wsb.libraryapi.dtos.LoanDTO;
import com.wsb.libraryapi.entities.Book;
import com.wsb.libraryapi.entities.Loan;
import com.wsb.libraryapi.mappers.LoanMapper;
import com.wsb.libraryapi.repositories.BookRepository;
import com.wsb.libraryapi.repositories.LoanRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {
    private final LoanRepository loanRepository;
    private final LoanMapper loanMapper;
    private final BookRepository bookRepository;
    private final UserService userService;

    @Override
    public List<LoanDTO> listLoans() {
        return mapLoansToDto(loanRepository.findAll());
    }

    @Override
    public List<LoanDTO> listLoansOfCurrentUser() {
        return mapLoansToDto(loanRepository.findByUser(userService.getCurrentUser()));
    }

    @Override
    public LoanDTO saveLoan(LoanDTO loanDTO) {
        if (loanDTO.getReturn_date() == null) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_YEAR, 14);
            loanDTO.setReturn_date(new Timestamp(calendar.getTime().getTime()));
        }

        Loan loan = loanMapper.toEntity(loanDTO);
        Book book = bookRepository.findById(loanDTO.getBook_id()).orElseThrow(EntityNotFoundException::new);
        book.setAvailable(false);
        bookRepository.save(book);
        loan.setBook(book);
        loan.setUser(userService.getCurrentUser());
        return loanMapper.toDto(loanRepository.save(loan));
    }

    private List<LoanDTO> mapLoansToDto(List<Loan> loans) {
        return loans.stream()
                .map(loan -> {
                    LoanDTO loanDTO = loanMapper.toDto(loan);
                    loanDTO.setBook_id(loan.getBook().getId());
                    loanDTO.setUser_id(loan.getUser().getId());
                    return loanDTO;
                }).collect(Collectors.toList());
    }
}
