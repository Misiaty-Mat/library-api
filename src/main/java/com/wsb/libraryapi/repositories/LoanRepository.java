package com.wsb.libraryapi.repositories;

import com.wsb.libraryapi.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LoanRepository extends JpaRepository<Loan, UUID> {
}