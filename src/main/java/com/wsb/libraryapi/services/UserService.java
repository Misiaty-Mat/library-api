package com.wsb.libraryapi.services;

import com.wsb.libraryapi.dtos.UserDTO;
import com.wsb.libraryapi.entities.User;

public interface UserService {

    public UserDTO findUserById(String id);

    User getUser(String name);
}
