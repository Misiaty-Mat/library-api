package com.wsb.libraryapi.services;

import com.wsb.libraryapi.dtos.LoanDTO;

import java.util.List;

public interface LoanService {
    List<LoanDTO> listLoans();
    List<LoanDTO> listLoansOfCurrentUser();
    LoanDTO saveLoan(LoanDTO loanDTO);
}
