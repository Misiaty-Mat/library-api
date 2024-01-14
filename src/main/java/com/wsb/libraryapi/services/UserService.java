package com.wsb.libraryapi.services;

import com.wsb.libraryapi.dtos.UserDTO;
import com.wsb.libraryapi.entities.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserDTO me();
    UserDTO findUserById(UUID id);
    List<UserDTO> listUsers();

    User getCurrentUser();

    void deleteUser(UUID id);
}
