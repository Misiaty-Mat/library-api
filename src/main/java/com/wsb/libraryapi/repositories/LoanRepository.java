package com.wsb.libraryapi.repositories;

import com.wsb.libraryapi.entities.Loan;
import com.wsb.libraryapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface LoanRepository extends JpaRepository<Loan, UUID> {
    @Query("select l from Loan l where l.user = ?1")
    List<Loan> findByUser(User user);
}