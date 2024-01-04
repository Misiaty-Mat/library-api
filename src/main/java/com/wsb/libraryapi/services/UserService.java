package com.wsb.libraryapi.services;

import com.wsb.libraryapi.dtos.UserDTO;
import com.wsb.libraryapi.entities.User;

import java.util.List;

public interface UserService {
    UserDTO me();
    UserDTO findUserById(String id);
    List<UserDTO> listUsers();

    User getCurrentUser();
}
