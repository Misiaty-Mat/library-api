package com.wsb.libraryapi.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Builder
@Data
public class LoanDTO {

    private UUID id;

    private Timestamp return_date;

    private UUID user_id;

    @NotNull
    @NotBlank
    private UUID book_id;
}
