package com.wsb.libraryapi.controllers;

import com.wsb.libraryapi.dtos.UserDTO;
import com.wsb.libraryapi.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private static final String USER_PATH = "/api/v1/user";

    private final UserService userService;

    @GetMapping(USER_PATH)
    public ResponseEntity<UserDTO> findUserById(@PathVariable String userId) {
        return new ResponseEntity<>(userService.findUserById(userId), HttpStatus.OK);
    }
}
