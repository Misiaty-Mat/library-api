package com.wsb.libraryapi.services;

import com.wsb.libraryapi.dtos.LoanDTO;
import com.wsb.libraryapi.dtos.LoanDetailsDto;

import java.util.List;
import java.util.UUID;

public interface LoanService {
    List<LoanDetailsDto> listLoans();
    List<LoanDetailsDto> listLoansOfCurrentUser();
    void deleteLoan(UUID loanId);
    LoanDetailsDto saveLoan(LoanDTO loanDTO);
}
