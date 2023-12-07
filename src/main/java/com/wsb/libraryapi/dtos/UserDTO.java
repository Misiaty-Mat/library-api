package com.wsb.libraryapi.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class UserDTO {
    private UUID id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String role;
}
