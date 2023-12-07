package com.wsb.libraryapi.services;

import com.wsb.libraryapi.dtos.UserDTO;

public interface UserService {

    public UserDTO findUserById(String id);
    public UserDTO saveUser(UserDTO userDTO);
}
