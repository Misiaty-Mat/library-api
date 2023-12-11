package com.wsb.libraryapi.dtos;

import com.wsb.libraryapi.authorities.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class UserDTO {
    private UUID id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String surname;

    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private Role role;
}
