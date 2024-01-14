package com.wsb.libraryapi.dtos;

import lombok.Value;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * DTO for {@link com.wsb.libraryapi.entities.Loan}
 */
@Value
public class LoanDetailsDto implements Serializable {
    UUID id;
    Timestamp return_date;
    Boolean returned;
    UserDTO user;
    BookDTO book;
}