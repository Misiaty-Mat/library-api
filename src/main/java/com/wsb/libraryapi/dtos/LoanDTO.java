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

    @NotNull
    @NotBlank
    private Timestamp return_date;

    @NotNull
    @NotBlank
    private String user_id;

    @NotNull
    @NotBlank
    private String book_id;
}
