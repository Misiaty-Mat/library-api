package com.wsb.libraryapi.controllers;

import com.wsb.libraryapi.dtos.LoanDTO;
import com.wsb.libraryapi.services.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/loans")
public class LoanController {

    private final LoanService loanService;

    @GetMapping
    public ResponseEntity<List<LoanDTO>> getLoans() {
        return ResponseEntity.ok(loanService.listLoans());
    }

    @GetMapping("/my")
    public ResponseEntity<List<LoanDTO>> getMyLoans() {
        return ResponseEntity.ok(loanService.listLoansOfCurrentUser());
    }

    @PostMapping
    public ResponseEntity<LoanDTO> postLoan(@RequestBody LoanDTO loanDTO) {
        return new ResponseEntity<LoanDTO>(loanService.saveLoan(loanDTO), HttpStatus.CREATED);
    }
}
