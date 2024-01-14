package com.wsb.libraryapi.controllers;

import com.wsb.libraryapi.dtos.LoanDTO;
import com.wsb.libraryapi.dtos.LoanDetailsDto;
import com.wsb.libraryapi.services.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/loans")
public class LoanController {

    private final LoanService loanService;

    @GetMapping
    public ResponseEntity<List<LoanDetailsDto>> getLoans() {
        return ResponseEntity.ok(loanService.listLoans());
    }

    @GetMapping("/my")
    public ResponseEntity<List<LoanDetailsDto>> getMyLoans() {
        return ResponseEntity.ok(loanService.listLoansOfCurrentUser());
    }

    @PostMapping
    public ResponseEntity<LoanDetailsDto> postLoan(@RequestBody LoanDTO loanDTO) {
        return new ResponseEntity<>(loanService.saveLoan(loanDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{loanId}")
    public ResponseEntity<Void> deleteLoan(@PathVariable UUID loanId) {
        loanService.deleteLoan(loanId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
